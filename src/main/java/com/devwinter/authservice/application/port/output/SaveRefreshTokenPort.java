package com.devwinter.authservice.application.port.output;

import com.devwinter.authservice.application.port.input.GenerateTokenUseCase.TokenDto;

public interface SaveRefreshTokenPort {
    void save(String key, TokenDto token);
}
