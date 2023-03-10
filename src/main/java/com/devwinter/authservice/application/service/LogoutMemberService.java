package com.devwinter.authservice.application.service;

import com.devwinter.authservice.application.exception.AuthException;
import com.devwinter.authservice.application.port.input.LogoutMemberUseCase;
import com.devwinter.authservice.application.port.output.DeleteRefreshTokenPort;
import com.devwinter.authservice.application.port.output.ExistRefreshTokenPort;
import com.devwinter.authservice.common.CacheKey;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;


import static com.devwinter.authservice.application.exception.AuthErrorCode.LOGOUT_ALREADY_STATUS;

@Service
@RequiredArgsConstructor
public class LogoutMemberService implements LogoutMemberUseCase {

    private final ExistRefreshTokenPort existRefreshTokenPort;
    private final DeleteRefreshTokenPort deleteRefreshTokenPort;

    @Override
    @CacheEvict(value = CacheKey.MEMBER, key = "#email")
    public void logout(String email) {
        if(!existRefreshTokenPort.exist(email)) {
            throw new AuthException(LOGOUT_ALREADY_STATUS);
        }

        deleteRefreshTokenPort.delete(email);
    }
}
