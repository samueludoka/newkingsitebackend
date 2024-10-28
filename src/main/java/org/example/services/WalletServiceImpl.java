package org.example.services;

import lombok.AllArgsConstructor;
import org.example.dtos.request.CreateWalletRequest;
import org.example.dtos.response.CreateWalletResponse;
import org.example.dtos.response.WalletResponse;
import org.example.exception.WalletExistException;
import org.example.exception.WalletNotFoundException;
import org.example.model.Wallet;
import org.example.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;


    @Override
    public WalletResponse viewWallet(Long walletId) {
        Wallet foundWallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("wallet with id " + walletId + " not found"));
        WalletResponse walletResponse = new WalletResponse();
        walletResponse.setId(foundWallet.getId());
        walletResponse.setBalance(foundWallet.getBalance());
        walletResponse.setAmountToDeposit(foundWallet.getAmountToDeposit());
        return walletResponse;
    }
}

