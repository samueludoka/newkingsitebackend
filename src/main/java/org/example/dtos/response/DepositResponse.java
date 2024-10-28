package org.example.dtos.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.model.PaymentStatus;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class DepositResponse {
    private Long id;
    private PaymentStatus paymentStatus;
    private BigDecimal balance;
    private Long walletId;
}
