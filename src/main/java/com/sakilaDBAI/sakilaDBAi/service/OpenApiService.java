package com.sakilaDBAI.sakilaDBAi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakilaDBAI.sakilaDBAi.dto.MessageDto;
import com.sakilaDBAI.sakilaDBAi.dto.OpenApiRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenApiService {

    public String getSqlQuery(String userQuery) {
        String apiKey = "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";
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        // Create a RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        // Set up request headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<MessageDto> messageDtos = new ArrayList<>();
        messageDtos.add(new MessageDto(
                "system",
                "For the given user question generate the sutiable sql query:-"
        ));
        messageDtos.add(new MessageDto(
                "user",
                userQuery
        ));
        OpenApiRequestDto openApiRequestDto = new OpenApiRequestDto(
                "ft:gpt-3.5-turbo-0613:personal::89o43iyS",
                messageDtos,
                0,
                2048,
                1,
                0,
                0
        );
        // Create an HttpEntity with headers and request body
        HttpEntity<OpenApiRequestDto> request = new HttpEntity<>(openApiRequestDto, headers);
        // Send a POST request to OpenAI API
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

        // Check the response
        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                // Parse the JSON response using Jackson ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(response.getBody());
                JsonNode choicesNode = rootNode.get("choices").get(0);
                JsonNode messageNode = choicesNode.get("message");
                String content = messageNode.get("content").asText();
                System.out.println("content recieved " + content);
                return content.replace("<code>", "").replace(" </code>", "");
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        else return null;
    }

}
