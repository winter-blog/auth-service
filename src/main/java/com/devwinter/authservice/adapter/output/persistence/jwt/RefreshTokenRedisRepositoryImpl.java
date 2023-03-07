package com.devwinter.authservice.adapter.output.persistence.jwt;

import com.devwinter.authservice.application.port.input.GenerateTokenUseCase.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRedisRepositoryImpl implements JwtRedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final static String REFRESH_TOKEN_KEY_PREFIX = "REFRESH-TOKEN";

    @Override
    public void save(String email, TokenDto tokenInfoDto) {
        redisTemplate.opsForValue()
                     .set(createKey(email), tokenInfoDto.refreshToken(), tokenInfoDto.refreshTokenExpirationTime(), TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean existRefreshToken(String email) {
        return Objects.nonNull(redisTemplate.opsForValue()
                                            .get(createKey(email)));
    }

    @Override
    public void delete(String email) {
        redisTemplate.delete(createKey(email));
    }

    @Override
    public String getRefreshToken(String email) {
        return (String) redisTemplate.opsForValue()
                            .get(createKey(email));
    }

    private String createKey(String email) {
        return String.format("%s::%s", REFRESH_TOKEN_KEY_PREFIX, email);
    }
}
