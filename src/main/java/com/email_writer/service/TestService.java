package com.email_writer.service;

import com.email_writer.dto.TestRequest;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public String test(TestRequest testRequest) {
        return testRequest.message();
    }
}
