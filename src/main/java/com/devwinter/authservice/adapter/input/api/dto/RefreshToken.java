package com.devwinter.authservice.adapter.input.api.dto;

import com.devwinter.authservice.application.port.input.GenerateTokenUseCase.TokenDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class RefreshToken {
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private String accessToken;
        private String grantType;
        private String refreshToken;

        public static BaseResponse<RefreshToken.Response> success(TokenDto tokenDto) {

            return BaseResponse.success("access-token 재발급에 성공하였습니다.",
                    new RefreshToken.Response(
                            tokenDto.accessToken(),
                            tokenDto.grantType(),
                            tokenDto.refreshToken())
            );
        }
    }
}
