package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RequestTest {

    int requestCount = 15000;

    @Test
    public void testVirtualThreadSync() throws Exception {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        RestClient restClient = RestClient.create();

        Instant start = Instant.now();

        for (int i = 0; i < requestCount; i++) {
            int finalI = i;
            executor.submit(() -> {
                try {
                    String result = restClient.get()
                            .uri("http://localhost:8080/virtual?count=" + finalI)
                            .retrieve()
                            .body(String.class);

                    System.out.println(result);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

        long totalTime = Duration.between(start, Instant.now()).toMillis();
        System.out.println("Total Elapsed Time for "+ requestCount + " requests (VirtualThread): " + totalTime + "ms");
    }

    @Test
    public void testWebFluxAsync() throws InterruptedException {
        WebClient webClient = WebClient.create();

        Instant start = Instant.now();

        Flux.range(1, requestCount)
                .flatMap(count ->
                    webClient.get()
                            .uri("http://localhost:8080/webflux?count="+count)
                            .retrieve()
                            .bodyToMono(String.class)
                )
                .doOnNext(System.out::println)
                .then()
                .block();

        long totalTime = Duration.between(start, Instant.now()).toMillis();
        System.out.println("Total Elapsed Time for 500 requests (WebFlux): " + totalTime + "ms");
    }
}
