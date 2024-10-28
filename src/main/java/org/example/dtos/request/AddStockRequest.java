package org.example.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.example.model.PlanType;

@Getter
@Setter
public class AddStockRequest {
    private Long StockId;
    private String StockName;
    private String currentName;
    private double currentPrice;
    private int totalQuantity;
    private int availableQuantity;
    private PlanType planType;
    private boolean isActive = true;
}
