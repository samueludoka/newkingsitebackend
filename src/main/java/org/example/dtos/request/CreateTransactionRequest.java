package org.example.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.example.model.CoinType;
import org.example.model.TransactionType;

import java.time.LocalDateTime;

@Getter
@Setter

public class CreateTransactionRequest {
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private LocalDateTime transactionDate;
    private Double transactionPrice;
}
