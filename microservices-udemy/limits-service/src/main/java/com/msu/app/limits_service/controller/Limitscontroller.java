package com.msu.app.limits_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msu.app.limits_service.bean.Limit;

@RestController
public class Limitscontroller {

    @GetMapping("/limits")
    public Limit getLimits() {
        return new Limit(1, 1000);
    }
}
