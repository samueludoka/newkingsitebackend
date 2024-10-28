package org.example.repositories;

import org.example.model.AmountToDeposit;
import org.example.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByAmountToDeposit(AmountToDeposit amountToDeposit);
}
