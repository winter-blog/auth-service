package com.devwinter.authservice.application.port.input;

public interface DeleteRefreshTokenUseCase {
    void delete(String email);
}
