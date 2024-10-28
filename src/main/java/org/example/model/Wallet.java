package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Wallet {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToOne
    private AmountToDeposit amountToDeposit;

//    @Getter
//    @Setter
//    @Entity
//    @RequiredArgsConstructor
//    public static class AmountToDeposit{
//        @Id
//        @GeneratedValue
//        private Long id;
//        private BigDecimal amount;
//        private PaymentStatus status;
//        private CoinType coinType;
//        private BigDecimal balance = BigDecimal.ZERO; // Ensure balance is never null
//
//        public AmountToDeposit(BigDecimal amount, PaymentStatus status, CoinType coinType, BigDecimal balance) {
//            this.amount = amount;
//            this.status = status;
//            this.coinType = coinType;
//            this.balance = balance;
//        }

}
