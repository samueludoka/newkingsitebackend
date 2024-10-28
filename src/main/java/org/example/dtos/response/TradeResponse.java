package org.example.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TradeResponse {
    private Long walletId;
    private String tradeStatus;
    private BigDecimal updatedAmount;
    private String tradeFlow;  // Optional trade flow stats
    private String tradeStats; //
}
