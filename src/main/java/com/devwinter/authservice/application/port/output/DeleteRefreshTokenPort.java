package com.devwinter.authservice.application.port.output;

public interface DeleteRefreshTokenPort {
    void delete(String key);
}
