package org.example.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerResponse {
    private Long Id;
    private String message;
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String password;
//    private String phone;
    private boolean active = false;
}
