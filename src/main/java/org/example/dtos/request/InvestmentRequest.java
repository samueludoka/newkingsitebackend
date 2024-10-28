package org.example.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.example.model.CoinType;

import java.math.BigDecimal;

@Getter
@Setter
public class InvestmentRequest {
    private Long customerId;
    private Long transactionId;
    private CoinType coinType;
    private BigDecimal amount;
}
