package com.devwinter.authservice.adapter.output.persistence.jwt;

import com.devwinter.authservice.application.port.input.TokenGenerateUseCase.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class JwtRedisRepositoryImpl implements JwtRedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    @Value("${spring.redis.key.prefix}")
    private String redis_key_prefix;

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

    private String createKey(String email) {
        return String.format("%s: %s", redis_key_prefix, email);
    }
}
