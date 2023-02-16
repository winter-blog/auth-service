package com.devwinter.authservice.application.port.input;

public interface ExistRefreshTokenValidUseCase {
    void valid(String key, String refreshToken);
}
