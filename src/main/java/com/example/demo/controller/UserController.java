package com.example.demo.controller;

import com.example.demo.api.GetUserRequest;
import com.example.demo.api.ReactorUserApiGrpc;
import com.example.demo.api.UserApiGrpc;
import com.example.demo.api.UserDto;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class UserController {

    @GrpcClient("userApi")
    private ReactorUserApiGrpc.ReactorUserApiStub userApiStub;

    @GetMapping(path = "/user/{userId}")
    public Mono<UserDto> getUser(@PathVariable String userId) {
        log.info("Getting user: {}", userId);
        return userApiStub.getUser(GetUserRequest.newBuilder().setId(userId).build());
    }
}
