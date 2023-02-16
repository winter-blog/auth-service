package com.devwinter.authservice.application.service;

import com.devwinter.authservice.application.exception.AuthErrorCode;
import com.devwinter.authservice.application.exception.AuthException;
import com.devwinter.authservice.application.port.input.ExistRefreshTokenValidUseCase;
import com.devwinter.authservice.application.port.output.ExistRefreshTokenPort;
import com.devwinter.authservice.application.port.output.LoadRefreshTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.devwinter.authservice.application.exception.AuthErrorCode.REFRESH_TOKEN_NOT_EXIST;
import static com.devwinter.authservice.application.exception.AuthErrorCode.REFRESH_TOKEN_NOT_SAME;


@Service
@RequiredArgsConstructor
public class ExistRefreshTokenValidService implements ExistRefreshTokenValidUseCase {

    private final LoadRefreshTokenPort loadRefreshTokenPort;

    @Override
    public void valid(String key, String refreshToken) {
        String findRefreshToken = loadRefreshTokenPort.getRefreshToken(key);

        if(Objects.isNull(findRefreshToken)) {
            throw new AuthException(REFRESH_TOKEN_NOT_EXIST);
        }

        if(!findRefreshToken.equals(refreshToken)) {
            throw new AuthException(REFRESH_TOKEN_NOT_SAME);
        }
    }
}
