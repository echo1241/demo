package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/webflux")
public class WebFluxController {

    @GetMapping
    public Mono<String> getResult(@RequestParam("count") int count) {

        return Mono.just("Hello Word" + count)
                .doOnNext(data -> log.info(data));
    }
}
