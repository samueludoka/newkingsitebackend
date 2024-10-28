package org.example.services;

import org.example.dtos.response.WalletResponse;

public interface WalletService {

    WalletResponse viewWallet(Long walletId);
}
