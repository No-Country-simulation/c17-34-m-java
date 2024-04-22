package com.c174.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ExceptionDto {
    private int status;
    private String message;

    private Map<String, Object> details;
    private Boolean success = Boolean.FALSE;
}
