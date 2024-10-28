package org.example.services;

import org.example.dtos.request.AdminRegisterRequest;
import org.example.dtos.request.CustomerBoardRequest;
import org.example.dtos.response.AdminLoginRequest;
import org.example.dtos.response.AdminRegisterResponse;
import org.example.dtos.response.DepositResponse;
import org.example.dtos.response.WithdrawResponse;
import org.example.exception.*;
import org.example.model.Admin;
import org.example.model.Wallet;

import javax.security.auth.login.LoginException;
import java.util.List;

public interface AdminService {

    String editCustomerBoard(CustomerBoardRequest customerBoardRequest);

    AdminRegisterResponse registerAdmin(AdminRegisterRequest adminRegisterRequest);

    String adminLogin(AdminLoginRequest adminLoginRequest) throws LoginException;

    DepositResponse approveFund(Long depositId) throws DepositNotFoundException, InvalidOperationException;

    WithdrawResponse approveWithdrawFund(Long withdrawId) throws InsufficientFundsException, WithdrawNotFoundException;

    List<Wallet> getAllCustomersByWalletId() throws WalletExistException;
}
