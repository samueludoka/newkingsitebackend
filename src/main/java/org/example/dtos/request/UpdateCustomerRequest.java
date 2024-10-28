package org.example.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCustomerRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isActive = true;

}
