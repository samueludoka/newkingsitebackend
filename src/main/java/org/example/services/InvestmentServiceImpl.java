package org.example.services;

import org.example.dtos.request.TradeRequest;
import org.example.dtos.response.TradeResponse;
import org.example.model.*;
import org.example.repositories.CustomerRepository;
import org.example.repositories.InvestmentRepository;
import org.example.repositories.TradeRepository;
import org.example.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class InvestmentServiceImpl implements InvestmentService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private WalletRepository walletRepository;

    public TradeResponse initiateTrade(TradeRequest tradeRequest) {
        Customer customer = customerRepository.findById(tradeRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Wallet wallet = customer.getWallet();
        BigDecimal walletBalance = wallet.getBalance();

        if (walletBalance.compareTo(tradeRequest.getAmount()) < 0) {
            throw new RuntimeException("Insufficient wallet balance for the investment.");
        }

        wallet.setBalance(walletBalance.subtract(tradeRequest.getAmount()));
        walletRepository.save(wallet);

        Investment investment = new Investment();
        investment.setCustomer(customer);
        investment.setPlanType(PlanType.valueOf(tradeRequest.getPlanType().toUpperCase()));
        investment.setAmount(tradeRequest.getAmount());
        investment.setCoinType(convertToEnum(CoinType.class, tradeRequest.getCoinType()));
        investment.setCreatedAt(LocalDateTime.now());

        investment = investmentRepository.save(investment);

        scheduleInvestmentPayout(investment);

        BigDecimal updatedAmount = processTrade(investment);

        Trade trade = new Trade();
        trade.setInvestment(investment);
        trade.setAmountBefore(investment.getAmount());
        trade.setAmountAfter(updatedAmount);
        trade.setTradeStatus(TradeStatus.SUCCESS);
        trade.setTradeTime(LocalDateTime.now());

        tradeRepository.save(trade);

        // Build the response
        TradeResponse responseDto = new TradeResponse();
        responseDto.setWalletId(wallet.getId());
        responseDto.setTradeStatus("success");
        responseDto.setUpdatedAmount(updatedAmount);
        responseDto.setTradeFlow("Trade executed successfully");
        responseDto.setTradeStats("Stats related to the trade");

        return responseDto;
    }

    private <T extends Enum<T>> T convertToEnum(Class<T> enumClass, String value) {
        for (T enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.name().equalsIgnoreCase(value)) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException("Invalid value for enum " + enumClass.getSimpleName() + ": " + value);
    }

    private BigDecimal processTrade(Investment investment) {
        PlanType planType = investment.getPlanType();

        BigDecimal investmentAmount = investment.getAmount();
        if (investmentAmount.compareTo(BigDecimal.valueOf(planType.getMin())) < 0 ||
                investmentAmount.compareTo(BigDecimal.valueOf(planType.getMax())) > 0) {
            throw new IllegalArgumentException("Investment amount must be between " + planType.getMin() + " and " + planType.getMax() + " for the selected plan.");
        }

        BigDecimal updatedAmount = investmentAmount.multiply(BigDecimal.valueOf(planType.getPercentage()));

        System.out.println("Plan Type: " + planType);
        System.out.println("Investment Amount: " + investmentAmount);
        System.out.println("Updated Amount: " + updatedAmount);
        System.out.println("Days: " + planType.getDays());

        return updatedAmount;
    }


    private void scheduleInvestmentPayout(Investment investment) {
        PlanType planType = investment.getPlanType();
        int days = planType.getDays();

        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            processPayout(investment);
        }, days, TimeUnit.DAYS);
    }

    private void processPayout(Investment investment) {
        Customer customer = investment.getCustomer();
        Wallet wallet = customer.getWallet();

        BigDecimal updatedAmount = processTrade(investment);

        wallet.setBalance(wallet.getBalance().add(updatedAmount));
        walletRepository.save(wallet);  

        Trade trade = tradeRepository.findByInvestment(investment);
        trade.setTradeStatus(TradeStatus.COMPLETED);
        tradeRepository.save(trade);
    }

}
