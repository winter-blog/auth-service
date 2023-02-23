package com.devwinter.authservice.adapter.output.persistence.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class MemberJpaEntity {

    @Id
    @Column(name = "member_id")
    private Long id;

    private String nickName;
    private String email;
    private String password;
}
