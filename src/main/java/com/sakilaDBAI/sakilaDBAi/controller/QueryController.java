package com.sakilaDBAI.sakilaDBAi.controller;

import com.sakilaDBAI.sakilaDBAi.dto.QueryRequestDto;
import com.sakilaDBAI.sakilaDBAi.service.ConverterService;
import com.sakilaDBAI.sakilaDBAi.service.QueryExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "query")
public class QueryController {
    @Autowired
    QueryExecutorService queryExecutorService;
    @Autowired
    ConverterService converterService;

    @PostMapping
    public ResponseEntity getQueryResponse(@RequestBody QueryRequestDto queryRequestDto){
        return new ResponseEntity(queryExecutorService.executeQuery(converterService.convertTextToSql(queryRequestDto.getQuery())),HttpStatus.OK);
    }
    @GetMapping("test")
    public ResponseEntity<HashMap<String,String>> getTestResponse(){
        HashMap<String ,String> response= new HashMap<>();
        response.put("message","Query Service is up");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
