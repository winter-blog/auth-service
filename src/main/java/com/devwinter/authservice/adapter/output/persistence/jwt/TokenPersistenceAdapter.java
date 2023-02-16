package com.devwinter.authservice.adapter.output.persistence.jwt;

import com.devwinter.authservice.application.port.input.TokenGenerateUseCase.TokenDto;
import com.devwinter.authservice.application.port.output.SaveRefreshTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TokenPersistenceAdapter implements SaveRefreshTokenPort {
    private final JwtRedisRepository jwtRedisRepository;
    @Override
    public void save(String key, TokenDto tokenInfoDto) {
        jwtRedisRepository.save(key, tokenInfoDto);
    }
}
