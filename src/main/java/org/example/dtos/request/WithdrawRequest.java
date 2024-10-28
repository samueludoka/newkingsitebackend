package org.example.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.example.model.CoinType;

import java.math.BigDecimal;

@Getter
@Setter
public class WithdrawRequest {
    private Long customerId;
    private String customerWalletAddress;
    private BigDecimal amount;
    private CoinType coinType;
}
