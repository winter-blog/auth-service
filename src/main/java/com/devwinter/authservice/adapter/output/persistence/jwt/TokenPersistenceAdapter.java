package com.devwinter.authservice.adapter.output.persistence.jwt;

import com.devwinter.authservice.application.port.input.GenerateTokenUseCase.TokenDto;
import com.devwinter.authservice.application.port.output.DeleteRefreshTokenPort;
import com.devwinter.authservice.application.port.output.ExistRefreshTokenPort;
import com.devwinter.authservice.application.port.output.LoadRefreshTokenPort;
import com.devwinter.authservice.application.port.output.SaveRefreshTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TokenPersistenceAdapter implements
        SaveRefreshTokenPort,
        ExistRefreshTokenPort,
        DeleteRefreshTokenPort,
        LoadRefreshTokenPort {
    private final JwtRedisRepository jwtRedisRepository;
    @Override
    public void save(String key, TokenDto tokenInfoDto) {
        jwtRedisRepository.save(key, tokenInfoDto);
    }

    @Override
    public void delete(String key) {
        jwtRedisRepository.delete(key);
    }

    @Override
    public boolean exist(String key) {
        return jwtRedisRepository.existRefreshToken(key);
    }

    @Override
    public String getRefreshToken(String key) {
        return jwtRedisRepository.getRefreshToken(key);
    }
}
