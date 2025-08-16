package com.email_writer.controller;

import com.email_writer.dto.TestRequest;
import com.email_writer.service.TestService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class TestController {
    TestService testService;

    @PostMapping("/test")
    public String test(@RequestBody TestRequest testRequest) {
        return testService.test(testRequest);
    }
}
