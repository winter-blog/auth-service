package com.devwinter.authservice.application.port.output;

import com.devwinter.authservice.domain.Member;

import java.util.Optional;

public interface LoadMemberPort {
    Optional<Member> findByEmail(String email);
    Optional<Member> findById(Long id);
}
