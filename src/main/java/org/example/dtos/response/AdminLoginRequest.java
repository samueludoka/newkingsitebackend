package org.example.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLoginRequest {
    private Long id;
    private String email;
    private String password;
}
