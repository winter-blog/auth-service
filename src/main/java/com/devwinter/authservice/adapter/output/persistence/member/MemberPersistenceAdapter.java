package com.devwinter.authservice.adapter.output.persistence.member;

import com.devwinter.authservice.application.exception.AuthErrorCode;
import com.devwinter.authservice.application.exception.AuthException;
import com.devwinter.authservice.application.port.output.LoadMemberPort;
import com.devwinter.authservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.devwinter.authservice.application.exception.AuthErrorCode.MEMBER_ALREADY_DELETE;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements LoadMemberPort {

    private final MemberJpaEntityRepository memberRepository;
    private final MemberMapper memberMapper;


    @Override
    public Member findByEmail(String email) {
        MemberJpaEntity memberJpaEntity = memberRepository.findByEmail(email)
                                                          .orElseThrow(() -> new AuthException(AuthErrorCode.MEMBER_NOT_FOUND));
        memberDeleteValid(memberJpaEntity);
        return memberMapper.entityToDomain(memberJpaEntity);
    }

    @Override
    public Member findById(Long id) {
        MemberJpaEntity memberJpaEntity = memberRepository.findById(id)
                                                          .orElseThrow(() -> new AuthException(AuthErrorCode.MEMBER_NOT_FOUND));
        memberDeleteValid(memberJpaEntity);
        return memberMapper.entityToDomain(memberJpaEntity);
    }

    private void memberDeleteValid(MemberJpaEntity memberJpaEntity) {
        if(memberJpaEntity != null && memberJpaEntity.isDeleted()) {
            throw new AuthException(MEMBER_ALREADY_DELETE);
        }
    }
}
