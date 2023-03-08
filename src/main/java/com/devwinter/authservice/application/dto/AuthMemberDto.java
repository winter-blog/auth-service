package com.devwinter.authservice.application.dto;

import com.devwinter.authservice.domain.Member;

public record AuthMemberDto(Long id, String email) {
    public static AuthMemberDto of(Member member) {
        return new AuthMemberDto(member.getId(), member.getEmail());
    }
}