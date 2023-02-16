package com.devwinter.authservice.application.service;

import com.devwinter.authservice.application.exception.AuthException;
import com.devwinter.authservice.application.port.input.DeleteRefreshTokenUseCase;
import com.devwinter.authservice.application.port.output.DeleteRefreshTokenPort;
import com.devwinter.authservice.application.port.output.ExistRefreshTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import static com.devwinter.authservice.application.exception.AuthErrorCode.LOGOUT_ALREADY_STATUS;

@Service
@RequiredArgsConstructor
public class DeleteRefreshTokenService implements DeleteRefreshTokenUseCase {

    private final ExistRefreshTokenPort existRefreshTokenPort;
    private final DeleteRefreshTokenPort deleteRefreshTokenPort;

    @Override
    public void delete(String key) {
        if(!existRefreshTokenPort.exist(key)) {
            throw new AuthException(LOGOUT_ALREADY_STATUS);
        }

        deleteRefreshTokenPort.delete(key);
    }
}
