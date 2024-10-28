package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class AmountToDeposit {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal amount;
    private String customerWalletAddress;
    private PaymentStatus status;
    private CoinType coinType;
    @OneToOne
    private Wallet wallet;

    public AmountToDeposit(BigDecimal amount, String customerWalletAddress, PaymentStatus status, CoinType coinType, Wallet wallet) {
        this.amount = amount;
        this.status = status;
        this.coinType = coinType;
        this.wallet = wallet;
        this.customerWalletAddress = customerWalletAddress;
    }

}
