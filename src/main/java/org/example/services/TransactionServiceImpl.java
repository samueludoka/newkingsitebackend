package org.example.services;

import org.example.dtos.request.CreateTransactionRequest;
import org.example.dtos.request.DepositRequest;
import org.example.dtos.request.InvestmentRequest;
import org.example.dtos.request.WithdrawRequest;
import org.example.dtos.response.CreateTransactionResponse;
import org.example.dtos.response.DepositResponse;
import org.example.dtos.response.TransactionResponse;
import org.example.dtos.response.WithdrawResponse;
import org.example.exception.*;
import org.example.model.*;
import org.example.repositories.AmountToDepositRepository;
import org.example.repositories.CustomerRepository;
import org.example.repositories.TransactionRepository;
import org.example.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service

public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AmountToDepositRepository amountToDepositRepository;
    @Autowired
    private WalletRepository walletRepository;


    @Override
    public List<Transaction> findByCustomer(Customer customer) throws CustomerNotFoundException, ResourceNotFoundException {
        List<Transaction> transactions=transactionRepository.findByCustomer(customer);
        if (transactions.size()==0) {
            throw new ResourceNotFoundException("No transactions found");
        }
        return transactions;    }

    @Override
    public void deleteAll(List<Transaction> transactions) throws TransactionException {
        transactionRepository.deleteAll(transactions);

    }

    @Override
    public CreateTransactionResponse createTransaction(CreateTransactionRequest createTransactionRequest) throws TransactionException {
        if(transactionExist(createTransactionRequest.getId()))
            throw new TransactionException("Transaction already exists");


        Transaction transaction = new Transaction();
        transaction.setId(createTransactionRequest.getId());
        transaction.setAmount(createTransactionRequest.getAmount());
        transaction.setTransactionType(createTransactionRequest.getTransactionType());
        transaction.setTransactionDate(createTransactionRequest.getTransactionDate());

        CreateTransactionResponse createTransactionResponse=new CreateTransactionResponse();
        Transaction savedTransaction = transactionRepository.save(transaction);
        createTransactionResponse.setId(savedTransaction.getId());
        return createTransactionResponse;

    }

    private boolean transactionExist(Long id) {
        Optional<Transaction> foundTransaction = transactionRepository.findById(id);
        return foundTransaction.isPresent();
    }

    @Override
    public TransactionResponse invest(InvestmentRequest investmentRequest) throws CustomerException, InsufficientFundsException {
        Customer foundCustomer = customerRepository.findById(investmentRequest.getCustomerId()).orElseThrow(
                () -> new CustomerNotFoundException("Customer with id " + investmentRequest.getCustomerId() + " not found"));

//        if(investmentRequest.getAmount() < foundCustomer.getWallet().getBalance()) throw new InsufficientFundsException("Insufficient funds");

        foundCustomer.setId(investmentRequest.getCustomerId());
        foundCustomer.getWallet().getAmountToDeposit().setAmount(investmentRequest.getAmount());
//        foundCustomer.getWallet().setBalance(foundCustomer.getWallet().getBalance() - investmentRequest.getAmount());
        TransactionResponse transactionResponse = new TransactionResponse();
        Customer savedCustomer = customerRepository.save(foundCustomer);
        transactionResponse.setId(savedCustomer.getId());
        return transactionResponse;



    }

//    @Override
//    public DepositResponse deposit(DepositRequest depositRequest) throws InsufficientFundsException, CustomerNotFoundException {
//        Customer foundCustomer = customerRepository.findById(depositRequest.getCustomerId()).orElseThrow(
//                () -> new CustomerNotFoundException("Customer with id " + depositRequest.getCustomerId() + " not found"));
//
//        if(depositRequest.getAmount() < 0) throw new InsufficientFundsException("deposit amount must be greater than 0");
//
//        foundCustomer.setId(depositRequest.getCustomerId());
////        if(foundCustomer.getWallet().getAmount() == null)
////            foundCustomer.getWallet().setAmount(depositRequest.getAmount());
//
//        if(foundCustomer.getWallet().getBalance() == null)
//            foundCustomer.getWallet().setBalance(foundCustomer.getWallet().getBalance() + depositRequest.getAmount());
//        Wallet.AmountToDeposit amountToDeposit = amountToDepositRepository.save(new Wallet.AmountToDeposit(
//                depositRequest.getAmount(), Wallet.PaymentStatus.PENDING));
//        foundCustomer.getWallet().setAmountToDeposit(amountToDeposit);
//        DepositResponse depositResponse = new DepositResponse();
//        Customer savedCustomer = customerRepository.save(foundCustomer);
//        depositResponse.setId(savedCustomer.getId());
//        return depositResponse;
//
//
//    }
    @Override
    public DepositResponse fundWallet(DepositRequest depositRequest) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(depositRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        Wallet wallet = customer.getWallet();
        if (wallet == null) {
            wallet = new Wallet();
            customer.setWallet(wallet);
            walletRepository.save(wallet);
        }

        AmountToDeposit amountToDeposit = new AmountToDeposit();
        amountToDeposit.setAmount(depositRequest.getAmount());
        amountToDeposit.setCoinType(depositRequest.getCoinType());
//        amountToDeposit.setBalance(BigDecimal.ZERO);  // Initialize balance to zero
        amountToDeposit.setStatus(PaymentStatus.PENDING);
        amountToDepositRepository.save(amountToDeposit);


        wallet.setAmountToDeposit(amountToDeposit);
        walletRepository.save(wallet);

        DepositResponse depositResponse = new DepositResponse();
        depositResponse.setWalletId(wallet.getId());
        depositResponse.setId(wallet.getId());
        depositResponse.setId(amountToDeposit.getId());
        depositResponse.setPaymentStatus(amountToDeposit.getStatus());
        return depositResponse;
    }

    @Override
    public WithdrawResponse withdraw(WithdrawRequest withdrawRequest) throws InsufficientFundsException, CustomerNotFoundException {
        Customer foundCustomer = customerRepository.findById(withdrawRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + withdrawRequest.getCustomerId() + " not found"));

        Wallet wallet = foundCustomer.getWallet();
        BigDecimal retrievedBalance =  foundCustomer.getWallet().getBalance();
        if (wallet.getBalance() == null) {
            wallet.setBalance(BigDecimal.ZERO);  // Initialize balance to zero if null
            walletRepository.save(wallet);  // Save the updated wallet
        }

        if (wallet.getBalance().equals(BigDecimal.ZERO)) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }

        AmountToDeposit amountToDeposit = new AmountToDeposit();
        amountToDeposit.setCustomerWalletAddress(withdrawRequest.getCustomerWalletAddress());
        amountToDeposit.setAmount(withdrawRequest.getAmount());
        amountToDeposit.setCoinType(withdrawRequest.getCoinType());
        amountToDeposit.setStatus(PaymentStatus.PENDING);
        wallet.setBalance(retrievedBalance);
        amountToDepositRepository.save(amountToDeposit);

        wallet.setAmountToDeposit(amountToDeposit);
        walletRepository.save(wallet);

        WithdrawResponse withdrawResponse = new WithdrawResponse();
        withdrawResponse.setWalletId(wallet.getId());
        withdrawResponse.setId(amountToDeposit.getId());  // Set transaction ID
        withdrawResponse.setPaymentStatus(amountToDeposit.getStatus());
        withdrawResponse.setCustomerWalletAddress(amountToDeposit.getCustomerWalletAddress());
        withdrawResponse.setBalance(wallet.getBalance());

        return withdrawResponse;
    }
}
