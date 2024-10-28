package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Entity

public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "investment_id")
    private Investment investment;

    private BigDecimal amountBefore;
    private BigDecimal amountAfter;
    private TradeStatus tradeStatus;  // success, pending, failure
    private LocalDateTime tradeTime;
}
