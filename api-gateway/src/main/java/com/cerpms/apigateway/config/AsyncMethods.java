package com.cerpms.apigateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncMethods {

    @Autowired
    private WebClient.Builder builder;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<?> getUserDetailsFromAdmin(String email) {
        log.info("getUserDetailsFromAdmin started at " + System.currentTimeMillis() + " " + Thread.currentThread().getName());
        return builder.build().post().uri("http://ADMIN-SERVICE/admin-service/admins/auth").body(BodyInserters.fromValue(email)).retrieve().bodyToMono(String.class).toFuture();
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<?> getUserDetailsFromStudent(String email){
        log.info("getUserDetailsFromStudent started at " + System.currentTimeMillis() + " " + Thread.currentThread().getName());
        return builder.build().post().uri("http://STUDENT-SERVICE/student-service/students/auth").body(BodyInserters.fromValue(email)).retrieve().bodyToMono(String.class).toFuture();
    }

    ;
}