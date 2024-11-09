package com.example.demo.api.utils;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse {
    private int status;
    private String message;
    private String details;
}
