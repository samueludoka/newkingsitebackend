package org.example.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerResponse {
    private String message;
    private Long walletId;
}
