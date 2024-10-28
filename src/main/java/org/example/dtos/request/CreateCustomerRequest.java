package org.example.dtos.request;

import lombok.Getter;
import lombok.Setter;
//import org.example.model.Country;
import org.example.model.Country;
import org.example.model.Wallet;

@Getter
@Setter

public class CreateCustomerRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String country;
    private String phoneNumber;
    private String password;
    private Wallet wallet;

}
