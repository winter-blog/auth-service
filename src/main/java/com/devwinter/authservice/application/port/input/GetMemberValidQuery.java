package com.devwinter.authservice.application.port.input;

import com.devwinter.authservice.domain.Member;

public interface GetMemberValidQuery {

    ValidMemberDto valid(Long memberId);

    record ValidMemberDto(Long memberId, String email) {

        public static ValidMemberDto of(Member member) {
            return new ValidMemberDto(member.getId(), member.getEmail());
        }
    }
}
