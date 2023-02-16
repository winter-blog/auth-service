package com.devwinter.authservice.adapter.output.persistence.jwt;


import com.devwinter.authservice.application.port.input.TokenGenerateUseCase.TokenDto;

public interface JwtRedisRepository {
    void save(String email, TokenDto tokenInfoDto);

    boolean existRefreshToken(String email);

    void delete(String email);
}
