package com.devwinter.authservice.adapter.input.api;

public enum AuthApiDocumentInfo {
    LOGIN("인증", "로그인", "로그인", "로그인 API", false),
    LOGOUT("인증", "로그아웃", "로그아웃", "회원 비밀번호 변경 API", true),
    REFRESH_TOKEN("인증", "access-token 요청", "access-token 요청", "access-token 요청 API", true),
    ;

    private final String tag;
    private final String identifier;
    private final String summary;
    private final String description;
    private final boolean certification;

    AuthApiDocumentInfo(String tag, String identifier, String summary, String description, boolean certification) {
        this.tag = tag;
        this.identifier = identifier;
        this.summary = summary;
        this.description = description;
        this.certification = certification;
    }

    public String getTag() {
        return tag;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCertification() {
        return certification;
    }
}
