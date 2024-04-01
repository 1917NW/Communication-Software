package com.lxy.imapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imServer")
public class ImServerURLController {
    @GetMapping("/serverUrl")
    public String getImServerURL(){
        return "lxy";
    }
}
