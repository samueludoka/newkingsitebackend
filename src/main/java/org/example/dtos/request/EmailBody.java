package org.example.dtos.request;

import lombok.Builder;

@Builder
public record EmailBody(String to, String subject, String body, String text) {
}
