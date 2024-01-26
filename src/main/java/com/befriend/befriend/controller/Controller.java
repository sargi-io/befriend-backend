package com.befriend.befriend.controller;

// import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hello")
    public String hello() {
        // Logic to fetch all items
        return "Hello world";
    }
}
