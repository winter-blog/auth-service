package com.devwinter.authservice.adapter.input.api.dto;

import com.devwinter.authservice.application.port.input.AuthMemberUseCase;
import com.devwinter.authservice.application.port.input.TokenGenerateUseCase.TokenDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberLogin {
    @Getter
    public static class Request {
        private String email;
        private String password;


        public AuthMemberUseCase.AuthMemberCommand toCommand() {
            return new AuthMemberUseCase.AuthMemberCommand(email, password);
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private String accessToken;
        private String grantType;
        private String refreshToken;

        public static BaseResponse<Response> success(TokenDto tokenDto) {

            return BaseResponse.success("로그인에 성공하였습니다.",
                    new Response(
                            tokenDto.accessToken(),
                            tokenDto.grantType(),
                            tokenDto.refreshToken())
            );
        }
    }
}
