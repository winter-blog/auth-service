package com.devwinter.authservice.application.port.output;

public interface LoadRefreshTokenPort {
    String getRefreshToken(String key);
}
