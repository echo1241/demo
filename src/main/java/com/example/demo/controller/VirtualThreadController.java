package com.example.demo.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("virtual")
public class VirtualThreadController {

    @Async
    @GetMapping
    public String virtualThread(@RequestParam("count") int count) {
        System.out.println(count);
        return "Hello World" + count;
    }
}
