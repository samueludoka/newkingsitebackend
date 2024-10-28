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
import org.example.model.Customer;
import org.example.model.Transaction;

import java.util.List;

public interface TransactionService {
//    Integer getTotalSoldOutQuantityByStockId(Long stockId) throws ResourceNotFoundException;
    List<Transaction> findByCustomer(Customer customer) throws CustomerNotFoundException, ResourceNotFoundException;
    void deleteAll(List<Transaction> transactions) throws TransactionException;

    CreateTransactionResponse createTransaction(CreateTransactionRequest createTransactionRequest) throws TransactionException;

    TransactionResponse invest(InvestmentRequest investmentRequest)
            throws CustomerException, InsufficientFundsException;

    DepositResponse fundWallet(DepositRequest depositRequest) throws CustomerNotFoundException;

    WithdrawResponse withdraw(WithdrawRequest withdrawRequest) throws InsufficientFundsException, CustomerNotFoundException;

}

