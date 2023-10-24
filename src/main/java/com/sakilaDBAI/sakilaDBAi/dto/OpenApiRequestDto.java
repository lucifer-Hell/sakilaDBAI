package com.sakilaDBAI.sakilaDBAi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OpenApiRequestDto {
    private String model;
    private List<MessageDto> messages;
    private int temperature;
    private int max_tokens;
    private double top_p;
    private double frequency_penalty;
    private double presence_penalty;
}
