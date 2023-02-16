package com.devwinter.authservice.adapter.input.api.dto;


public class MemberLogout {
    public static class Response {
        public static BaseResponse<Response> success() {
            return BaseResponse.success("로그아웃에 성공하였습니다.");
        }
    }
}
