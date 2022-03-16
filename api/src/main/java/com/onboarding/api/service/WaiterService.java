package com.onboarding.api.service;

import org.springframework.stereotype.Service;

@Service
public class WaiterService {

    private Integer id;

    private String name;

    private Integer queryCount;

    public void setWaiter(Integer id, String name){
        this.id = id;
        this.name = name;
        queryCount = 0;
    }

    public void addQuery(){
        queryCount++;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQueryCount() {
        return queryCount;
    }
}
