package com.devwinter.authservice.application.port.output;

import com.devwinter.authservice.domain.Member;

public interface LoadMemberPort {
    Member findById(Long memberId);
    Member findByEmail(String email);
}
