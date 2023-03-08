package com.devwinter.authservice.application.service;

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
    public ValidMemberDto valid(String email) {
        Member member = loadMemberPort.findByEmail(email);
        return ValidMemberDto.of(member);
    }
}
