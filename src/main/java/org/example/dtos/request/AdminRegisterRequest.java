package org.example.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRegisterRequest {
    private Long Id;
    private String fullName;
    private String email;
    private String password;
}
