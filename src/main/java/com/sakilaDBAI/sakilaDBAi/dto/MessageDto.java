package com.sakilaDBAI.sakilaDBAi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDto {
    String role;
    String content;
}
