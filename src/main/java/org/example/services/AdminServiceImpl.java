package org.example.services;

import lombok.extern.slf4j.Slf4j;
import org.example.dtos.request.AdminRegisterRequest;
import org.example.dtos.request.CustomerBoardRequest;
import org.example.dtos.response.AdminLoginRequest;
import org.example.dtos.response.AdminRegisterResponse;
import org.example.dtos.response.DepositResponse;
import org.example.dtos.response.WithdrawResponse;
import org.example.exception.*;
import org.example.model.Admin;
import org.example.model.AmountToDeposit;
import org.example.model.PaymentStatus;
import org.example.model.Wallet;
import org.example.repositories.AdminRepository;
import org.example.repositories.AmountToDepositRepository;
import org.example.repositories.CustomerRepository;
import org.example.repositories.WalletRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private AmountToDepositRepository amountToDepositRepository;


    @Override
    public String editCustomerBoard(CustomerBoardRequest customerBoardRequest) {
        return "Customer board details updated successfully.";
    }

    @Override
    public AdminRegisterResponse registerAdmin(AdminRegisterRequest adminRegisterRequest) {
        if(adminExist(adminRegisterRequest.getEmail())) throw new InvalidDetailsException("Admin already exists.");
        Admin admin = mapper.map(adminRegisterRequest, Admin.class);
        adminRepository.save(admin);
        return mapper.map(admin, AdminRegisterResponse.class);
    }

    private boolean adminExist(String email) {
        Admin foundAdmin = adminRepository.findByEmail(email);
        return foundAdmin != null;
    }

    @Override
    public String adminLogin(AdminLoginRequest adminLoginRequest) throws InvalidDetailsException {
        Admin foundAdmin = adminRepository.findByEmail(adminLoginRequest.getEmail());
        if(!adminExist (adminLoginRequest.getEmail())) throw new InvalidDetailsException("Admin already exists.");
        if(!foundAdmin.getPassword().equals(adminLoginRequest.getPassword())) throw new InvalidDetailsException("Admin password does not match.");
        adminRepository.save(foundAdmin);
        return ("Admin successfully logged in.");
    }

    @Override
    public DepositResponse approveFund(Long depositId) throws DepositNotFoundException, InvalidOperationException {
        AmountToDeposit amountToDeposit = amountToDepositRepository.findById(depositId)
                .orElseThrow(() -> new DepositNotFoundException("Deposit not found"));

        Wallet wallet = walletRepository.findByAmountToDeposit(amountToDeposit);
        if (wallet == null) {
            throw new InvalidOperationException("No wallet found for the deposit.");
        }

        if (wallet.getBalance() == null) {
            wallet.setBalance(BigDecimal.ZERO);
        }

        wallet.setBalance(wallet.getBalance().add(amountToDeposit.getAmount()));

        amountToDeposit.setStatus(PaymentStatus.APPROVED);
        amountToDepositRepository.save(amountToDeposit);  // Save updated deposit

        walletRepository.save(wallet);

        DepositResponse depositResponse = new DepositResponse();
        depositResponse.setId(amountToDeposit.getId());
        depositResponse.setPaymentStatus(amountToDeposit.getStatus());
        depositResponse.setBalance(wallet.getBalance());  // Include updated balance in response
        return depositResponse;
    }

    @Override
    public WithdrawResponse approveWithdrawFund(Long withdrawId) throws InsufficientFundsException, WithdrawNotFoundException {
        AmountToDeposit amountToWithdraw = amountToDepositRepository.findById(withdrawId)
                .orElseThrow(() -> new WithdrawNotFoundException("Withdrawal request not found"));

//        if (!amountToWithdraw.getStatus().equals(PaymentStatus.APPROVED)) {
//            throw new IllegalStateException("Withdrawal has already been approved or is in a different state");
//        }

        Wallet foundWallet = walletRepository.findByAmountToDeposit(amountToWithdraw);

        BigDecimal currentBalance = foundWallet.getBalance();

        if (currentBalance.compareTo(amountToWithdraw.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }

        BigDecimal newBalance = currentBalance.subtract(amountToWithdraw.getAmount());
        foundWallet.setBalance(newBalance);

        amountToWithdraw.setStatus(PaymentStatus.APPROVED);

        walletRepository.save(foundWallet);
        amountToDepositRepository.save(amountToWithdraw);

        WithdrawResponse withdrawResponse = new WithdrawResponse();
        withdrawResponse.setWalletId(foundWallet.getId());
        withdrawResponse.setId(amountToWithdraw.getId());
        withdrawResponse.setPaymentStatus(PaymentStatus.APPROVED);
        withdrawResponse.setBalance(foundWallet.getBalance());

        return withdrawResponse;
    }

    @Override
    public List<Wallet> getAllCustomersByWalletId() throws WalletExistException {
        List<Wallet> foundCustomers = walletRepository.findAll();
        if(foundCustomers.isEmpty()) throw new WalletExistException("Customer not found.");
        return foundCustomers;

    }
}
