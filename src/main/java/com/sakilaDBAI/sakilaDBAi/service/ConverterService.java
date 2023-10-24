package com.sakilaDBAI.sakilaDBAi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {
    @Autowired
    OpenApiService openApiService;
    @Autowired
    QueryExecutorService queryExecutorService;
    public String convertTextToSql(String text){
        return openApiService.getSqlQuery(text);
    }
}
