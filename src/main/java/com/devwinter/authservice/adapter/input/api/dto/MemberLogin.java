package com.devwinter.authservice.adapter.input.api.dto;

import com.devwinter.authservice.application.port.input.AuthMemberUseCase;

public class MemberLogin {
    public static class Request {
        private String email;
        private String password;


        public AuthMemberUseCase.AuthMemberCommand toCommand() {
            return new AuthMemberUseCase.AuthMemberCommand(email, password);
        }
    }

    public static class Response {

    }
}
