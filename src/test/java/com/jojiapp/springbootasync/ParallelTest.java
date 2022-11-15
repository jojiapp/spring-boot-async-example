package com.jojiapp.springbootasync;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

import static java.lang.Thread.*;

@SpringBootTest
public class ParallelTest {

    @Autowired
    private Parallel parallel;

    @Test
    @DisplayName("CompletableFuture")
    void test() throws Exception {

        // Given
        List<CompletableFuture<String>> completableFutures = IntStream.range(0, 25)
                .mapToObj(n -> parallel.getNameAsync()
                        .orTimeout(3, TimeUnit.SECONDS)
                        .exceptionally(throwable -> throwable.getClass().toString()))
                .toList();

        completableFutures.stream()
                .map(CompletableFuture::join)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("parallel")
    void test2() throws Exception {
        IntStream.range(0, 25)
                .parallel()
                .mapToObj(n -> parallel.get())
                .forEach(System.out::println);
    }
}
