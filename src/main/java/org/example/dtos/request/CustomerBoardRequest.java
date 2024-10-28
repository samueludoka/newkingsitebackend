package org.example.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerBoardRequest {
    private int customerId;
    private String customerName;
}
