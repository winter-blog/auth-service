package com.devwinter.authservice.adapter.input.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigApiController {
    @Value("${auth.version}")
    private String version;

    @GetMapping("/api/v1/auth/version")
    public String version() {
        return version;
    }
}
