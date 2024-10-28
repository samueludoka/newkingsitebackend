package org.example.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateWalletResponse {
    private Long id;
    private BigDecimal amount;
    private BigDecimal balance;
}
