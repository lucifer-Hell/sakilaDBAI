package com.sakilaDBAI.sakilaDBAi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QueryExecutorService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QueryExecutorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method to execute a SQL query
    public List<Map<String, Object>> executeQuery(String sqlQuery) {
        // Execute the query
        if(!sqlQuery.toLowerCase().contains("select")){
            jdbcTemplate.execute(sqlQuery);
            Map<String,Object> message=new HashMap<>();
            message.put("message","query execution sucessfull ");
            ArrayList<Map<String,Object>> response=new ArrayList<>();
            response.add(message);
            return response;
        }
        return jdbcTemplate.queryForList(sqlQuery);
    }

}
