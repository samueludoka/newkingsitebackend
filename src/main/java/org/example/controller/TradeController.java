package org.example.controller;

import org.example.dtos.request.TradeRequest;
import org.example.dtos.response.TradeResponse;
import org.example.services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/customer")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class TradeController {
    @Autowired
    private InvestmentService investmentService;

    @PostMapping("/initiateTrade")
    public ResponseEntity<?> initiateTrade(@RequestBody TradeRequest tradeRequest) {
        try {
            TradeResponse responseDto = investmentService.initiateTrade(tradeRequest);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
