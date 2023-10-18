package com.projet17backend.backend.dto;

import java.util.Map;

public record ErrorDTO (
        String code,
        Map<String,String> messages
){
}
