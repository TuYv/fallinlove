package com.max.fallinlove.finance.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTestController {

    @Autowired
    private UserInfoProducer userLogProducer;

    /**
     * test insert
     */
    @GetMapping("/kafka/insert")
    public String insert() {
        userLogProducer.sendMessage();

        return null;
    }

}