package org.example.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.example.model.PaymentStatus;

import java.math.BigDecimal;

@Getter
@Setter
public class WithdrawResponse {
    private Long id;
    private Long walletId;
    private PaymentStatus paymentStatus;
    private BigDecimal balance;
    private String customerWalletAddress;
}
