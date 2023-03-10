package com.devwinter.authservice.application.port.input;


import com.devwinter.authservice.application.port.input.LoginMemberUseCase.AuthMemberDto;
import lombok.Builder;

public interface GenerateTokenUseCase {

    TokenDto generate(AuthMemberDto authMemberDto);

    @Builder
    record TokenDto(String grantType,
                    String accessToken,
                    String refreshToken,
                    Long memberId,
                    Long refreshTokenExpirationTime) {

    }
}
