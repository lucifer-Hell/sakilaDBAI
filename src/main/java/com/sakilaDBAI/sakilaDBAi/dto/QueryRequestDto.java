package com.sakilaDBAI.sakilaDBAi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QueryRequestDto {
    @JsonProperty("query")
    private String query;
}
