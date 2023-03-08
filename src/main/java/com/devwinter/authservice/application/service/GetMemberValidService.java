package com.devwinter.authservice.application.service;

import com.devwinter.authservice.application.dto.AuthMemberDto;
import com.devwinter.authservice.application.port.input.GetMemberValidQuery;
import com.devwinter.authservice.application.port.output.LoadMemberPort;
import com.devwinter.authservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMemberValidService implements GetMemberValidQuery {
    private final LoadMemberPort loadMemberPort;

    @Override
    @Cacheable(value = "MEMBER", key = "#memberId", cacheManager = "cacheManager")
    public AuthMemberDto valid(Long memberId) {
        Member member = loadMemberPort.findById(memberId);
        return AuthMemberDto.of(member);
    }
}
