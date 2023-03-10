package com.devwinter.authservice.adapter.input.api.dto;

import com.devwinter.authservice.application.port.input.LoginMemberUseCase;
import com.devwinter.authservice.application.port.input.GenerateTokenUseCase.TokenDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberLogin {
    @Getter
    public static class Request {
        private String email;
        private String password;


        public LoginMemberUseCase.AuthMemberCommand toCommand() {
            return new LoginMemberUseCase.AuthMemberCommand(email, password);
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private Long memberId;
        private String accessToken;
        private String grantType;
        private String refreshToken;

        public static BaseResponse<Response> success(TokenDto tokenDto) {

            return BaseResponse.success("로그인에 성공하였습니다.",
                    new Response(
                            tokenDto.memberId(),
                            tokenDto.accessToken(),
                            tokenDto.grantType(),
                            tokenDto.refreshToken())
            );
        }
    }
}
