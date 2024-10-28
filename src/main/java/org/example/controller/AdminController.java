package org.example.controller;

import org.example.dtos.response.DepositResponse;
import org.example.dtos.response.WithdrawResponse;
import org.example.exception.CustomerException;
import org.example.exception.ResourceNotFoundException;
import org.example.exception.WalletExistException;
import org.example.model.*;
import org.example.services.AdminService;
import org.example.services.CustomerService;
import org.example.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private TransactionService transactionService;

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() throws CustomerException {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.ACCEPTED);
    }
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @PostMapping("/approveFund/{userId}")
    public ResponseEntity<DepositResponse> approveFund(@PathVariable Long userId) throws CustomerException {
        DepositResponse admin = adminService.approveFund(userId);
        return new ResponseEntity<>(admin, HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @PostMapping("/approveWalletFund/{withdrawId}")
    public ResponseEntity<WithdrawResponse> approveWalletFund(@PathVariable Long withdrawId) throws CustomerException {
        WithdrawResponse admin = adminService.approveWithdrawFund(withdrawId);
        return new ResponseEntity<>(admin, HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @GetMapping("/getAllCustomerByWalletId")
    public ResponseEntity<List<Wallet>> getAllCustomerByWalletId() throws WalletExistException {
        List<Wallet> foundWallets = adminService.getAllCustomersByWalletId();
        return new ResponseEntity<>(foundWallets, HttpStatus.ACCEPTED);
    }
}
