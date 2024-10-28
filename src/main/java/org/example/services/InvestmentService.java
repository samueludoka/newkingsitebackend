package org.example.services;

import org.example.dtos.request.TradeRequest;
import org.example.dtos.response.TradeResponse;

public interface InvestmentService {
    TradeResponse initiateTrade(TradeRequest tradeRequest);
}
