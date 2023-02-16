package com.devwinter.authservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {
    private Long id;
    private String email;
    private String password;
}
