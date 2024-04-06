package com.lxy;

import com.lxy.service.PrivateMsgService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class ImConsumerApplication {



    public static void main(String[] args) {
        SpringApplication.run(ImConsumerApplication.class, args);
    }


}
