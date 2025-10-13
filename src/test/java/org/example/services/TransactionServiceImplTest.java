// package org.example.services;

// import org.example.dtos.request.CreateTransactionRequest;
// import org.example.dtos.request.DepositRequest;
// import org.example.dtos.request.InvestmentRequest;
// import org.example.dtos.request.WithdrawRequest;
// import org.example.dtos.response.CreateTransactionResponse;
// import org.example.dtos.response.DepositResponse;
// import org.example.dtos.response.TransactionResponse;
// import org.example.dtos.response.WithdrawResponse;
// import org.example.model.CoinType;
// import org.example.model.TransactionType;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;

// import static org.junit.jupiter.api.Assertions.*;
// @SpringBootTest
// public class TransactionServiceImplTest {
//     @Autowired
//     private TransactionService transactionService;

//     @Test
//     public void createTransactionTest(){
//         CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
//         createTransactionRequest.setId(1L);
//         createTransactionRequest.setTransactionType(TransactionType.INVEST);
//         createTransactionRequest.setAmount(10.00);
//         createTransactionRequest.setTransactionDate(LocalDateTime.now());
//         CreateTransactionResponse createTransactionResponse = transactionService.createTransaction(createTransactionRequest);
//         assertNotNull(createTransactionResponse);
//         assertNotNull(createTransactionResponse.getId());
//     }

//     @Test
//     public void testAddFundTransaction() {
//         DepositRequest depositRequest = new DepositRequest();
//         depositRequest.setAmount(BigDecimal.TEN);
//         depositRequest.setCoinType(CoinType.Bitcoin);
//         depositRequest.setCustomerId(2L);
//         DepositResponse DepositResponse = transactionService.fundWallet(depositRequest);
//         assertNotNull(DepositResponse);
//         assertNotNull(DepositResponse.getId());
//     }
//     @Test
//     public void testWithdrawFund(){
//         WithdrawRequest withdrawRequest = new WithdrawRequest();
//         withdrawRequest.setAmount(BigDecimal.TEN);
//         withdrawRequest.setCustomerId(1L);
//         WithdrawResponse withdrawResponse = transactionService.withdraw(withdrawRequest);
//         assertNotNull(withdrawResponse);
//         assertNotNull(withdrawResponse.getId());
//     }
//     @Test
//     public void testInvestFund(){
//         InvestmentRequest investmentRequest = new InvestmentRequest();
//         investmentRequest.setAmount(BigDecimal.TEN);
//         investmentRequest.setCoinType(CoinType.Bitcoin);
//         investmentRequest.setCustomerId(1L);
//         TransactionResponse transactionResponse = transactionService.invest(investmentRequest);
//         assertNotNull(transactionResponse);
//         assertNotNull(transactionResponse.getId());
//     }


// }