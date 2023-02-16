package com.devwinter.authservice.application.service;

import com.devwinter.authservice.application.exception.AuthErrorCode;
import com.devwinter.authservice.application.exception.AuthException;
import com.devwinter.authservice.application.port.input.GetMemberValidQuery;
import com.devwinter.authservice.application.port.output.LoadMemberPort;
import com.devwinter.authservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMemberValidService implements GetMemberValidQuery {
    private final LoadMemberPort loadMemberPort;

    @Override
    public ValidMemberDto valid(Long memberId) {
        Member member = loadMemberPort.findById(memberId)
                                      .orElseThrow(() -> new AuthException(AuthErrorCode.MEMBER_NOT_FOUND));
        return ValidMemberDto.of(member);
    }
}
