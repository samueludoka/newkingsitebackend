package org.example.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.example.model.AmountToDeposit;

import java.math.BigDecimal;

@Getter
@Setter

public class WalletResponse {
    private Long id;
    private BigDecimal balance;
    private AmountToDeposit amountToDeposit;
}
