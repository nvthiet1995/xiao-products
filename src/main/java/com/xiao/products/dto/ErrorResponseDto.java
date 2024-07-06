package com.xiao.products.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponseDto {

    private String message;

    private HttpStatus code;

    private String title;

    private Map<String, String> errors;

    private Object data;

}