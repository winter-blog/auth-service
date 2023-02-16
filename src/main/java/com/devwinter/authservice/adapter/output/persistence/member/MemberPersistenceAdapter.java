package com.devwinter.authservice.adapter.output.persistence.member;

import com.devwinter.authservice.application.port.output.LoadMemberPort;
import com.devwinter.authservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements LoadMemberPort {

    private final MemberJpaEntityRepository memberRepository;
    private final MemberMapper memberMapper;


    @Override
    public Optional<Member> findByEmail(String email) {
        MemberJpaEntity memberJpaEntity = memberRepository.findByEmail(email)
                                                          .orElse(null);

        return Optional.ofNullable(memberMapper.entityToDomain(memberJpaEntity));
    }

    @Override
    public Optional<Member> findById(Long id) {
        MemberJpaEntity memberJpaEntity = memberRepository.findById(id)
                                                          .orElse(null);

        return Optional.ofNullable(memberMapper.entityToDomain(memberJpaEntity));
    }
}
