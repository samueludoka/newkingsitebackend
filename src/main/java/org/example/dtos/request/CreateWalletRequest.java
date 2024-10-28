package org.example.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateWalletRequest {
    private Long id;
    private BigDecimal amount;
    private BigDecimal balance;
}
