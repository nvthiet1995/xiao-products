package com.xiao.products.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseDto {

    private String statusCode;

    private String statusMsg;

}