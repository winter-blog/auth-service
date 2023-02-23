package com.devwinter.authservice.adapter.input.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConfigApiController {
    @Value("${auth.version}")
    private String version;

    private final Environment env;

    @GetMapping("/api/v1/auth/version")
    public String version() {
        return "@Value: " + version + ", Environment: " + env.getProperty("auth.version");
}
