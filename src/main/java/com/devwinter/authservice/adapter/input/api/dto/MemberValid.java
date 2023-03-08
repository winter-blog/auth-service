package com.devwinter.authservice.adapter.input.api.dto;

import com.devwinter.authservice.application.port.input.GetMemberValidQuery.ValidMemberDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberValid {
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private Long memberId;
        private String email;

        public static BaseResponse<Response> success(ValidMemberDto memberDto) {
            return BaseResponse.success("회원 인증에 성공하였습니다.",
                    new MemberValid.Response(memberDto.id(), memberDto.email())
            );
        }
    }
}
