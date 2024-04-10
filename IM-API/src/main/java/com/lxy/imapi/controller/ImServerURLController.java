package com.lxy.imapi.controller;


import com.lxy.imapi.service.ImServerURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imServer")
public class ImServerURLController {

    @Autowired
    private ImServerURLService imServerURLService;




    @GetMapping("/serverUrl")
    public String getImServerURL(){
        return imServerURLService.getImServerURL();
    }
}
