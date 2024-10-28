package org.example.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.example.model.CoinType;
import org.example.model.PlanType;

import java.math.BigDecimal;

@Getter
@Setter
public class TradeRequest {
    private Long customerId;
    private String planType;
    private BigDecimal amount;
    private String coinType;
}
