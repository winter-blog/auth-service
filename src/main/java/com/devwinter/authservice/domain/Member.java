package com.devwinter.authservice.domain;

import lombok.Getter;

@Getter
public class Member {
    private Long id;
    private String email;
    private String password;
}
