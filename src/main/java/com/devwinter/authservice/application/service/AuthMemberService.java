package com.devwinter.authservice.application.service;

import com.devwinter.authservice.application.exception.AuthErrorCode;
import com.devwinter.authservice.application.exception.AuthException;
import com.devwinter.authservice.application.port.input.AuthMemberUseCase;
import com.devwinter.authservice.application.port.output.LoadMemberPort;
import com.devwinter.authservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthMemberService implements AuthMemberUseCase {

    private final LoadMemberPort loadMemberPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthMemberDto credential(AuthMemberCommand command) {
        Member member = loadMemberPort.findByEmail(command.email());

        if(!passwordEncoder.matches(command.password(), member.getPassword())) {
            throw new AuthException(AuthErrorCode.MEMBER_PASSWORD_NOT_VALID);
        }

        return AuthMemberDto.of(member);
    }
}
