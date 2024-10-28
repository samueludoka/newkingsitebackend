package org.example.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Customer;

@Getter
@Setter
public class StockRequest {
    private Long StockId;
    private Customer customer;
//    private String investmentAmount;
//    private CoinType coinType;
//    private StockType stockType;
}
