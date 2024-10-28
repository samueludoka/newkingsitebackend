package org.example.repositories;

import org.example.model.AmountToDeposit;
import org.example.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmountToDepositRepository extends JpaRepository<AmountToDeposit, Long> {
}
