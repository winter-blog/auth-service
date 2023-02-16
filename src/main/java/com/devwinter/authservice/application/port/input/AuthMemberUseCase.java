package com.devwinter.authservice.application.port.input;

import com.devwinter.authservice.domain.Member;

public interface AuthMemberUseCase {

    AuthMemberDto credential(AuthMemberCommand command);

    record AuthMemberCommand(String email, String password) {

    }

    record AuthMemberDto(Long userId, String email) {
        public static AuthMemberDto of(Member member) {
            return new AuthMemberDto(member.getId(), member.getEmail());
        }
    }
}