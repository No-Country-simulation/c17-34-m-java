package com.c174.models.embed;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditResponse {
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
