package com.devwinter.authservice.application.port.input;


import com.devwinter.authservice.application.port.input.AuthMemberUseCase.AuthMemberDto;
import lombok.Builder;

public interface TokenGenerateUseCase {

    TokenDto generate(AuthMemberDto authMemberDto);

    @Builder
    record TokenDto(String grantType,
                    String accessToken,
                    String refreshToken,
                    Long userId,
                    Long refreshTokenExpirationTime) {

    }
}
