package com.jojiapp.springbootasync;

import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.concurrent.*;

import static java.lang.Thread.*;

@Component
public class Parallel {

    private static int num = 0;

    @Async
    public CompletableFuture<String> getNameAsync() {
        num++;
        CompletableFuture<String> result = new CompletableFuture<>();

        if (num > 10) {
            result.completeExceptionally(new RuntimeException("예외 발생"));
        } else {
            result.complete("시간 Async: " + LocalDateTime.now());
        }

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public String get() {

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "시간: " + LocalDateTime.now();
    }
}
