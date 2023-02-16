package com.devwinter.authservice.adapter.output.persistence.member;

import com.devwinter.authservice.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member entityToDomain(MemberJpaEntity memberJpaEntity) {
        if(memberJpaEntity == null) {
            return null;
        }

        return new Member(
                memberJpaEntity.getId(),
                memberJpaEntity.getEmail(),
                memberJpaEntity.getPassword());
    }
}
