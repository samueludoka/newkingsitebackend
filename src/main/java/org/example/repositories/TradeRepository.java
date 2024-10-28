package org.example.repositories;


import org.example.model.Investment;
import org.example.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
    Trade findByInvestment(Investment investment);
}
