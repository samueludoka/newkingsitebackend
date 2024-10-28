package org.example.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyEmailResponse {
    private String email;
    private String message;
}
