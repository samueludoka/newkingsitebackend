package org.example.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.model.CoinType;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class DepositRequest {
    private Long customerId;
    private CoinType coinType;
    private BigDecimal amount;
    private BigDecimal balance;

}
