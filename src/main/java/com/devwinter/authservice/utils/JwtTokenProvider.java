package com.devwinter.authservice.utils;

import com.devwinter.authservice.application.port.input.AuthMemberUseCase;
import com.devwinter.authservice.application.port.input.AuthMemberUseCase.AuthMemberDto;
import com.devwinter.authservice.application.port.input.GenerateTokenUseCase.TokenDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;
    private final Long accessTokenValidTime;
    private final Long refreshTokenValidTime;

    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey,
                            @Value("${jwt.access-token-expiration-time}") Long accessTokenValidTime,
                            @Value("${jwt.refresh-token-expiration-time}") Long refreshTokenValidTime) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenValidTime = accessTokenValidTime;
        this.refreshTokenValidTime = refreshTokenValidTime;
    }

    public TokenDto generateToken(AuthMemberDto member) {
        long now = (new Date()).getTime();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + accessTokenValidTime);
        String accessToken = Jwts.builder()
                                 .setSubject(member.email())
                                 .setAudience(member.id()
                                                    .toString())
                                 .setExpiration(accessTokenExpiresIn)
                                 .signWith(key, SignatureAlgorithm.HS256)
                                 .compact();

        // Refresh Token 생성
        Date refreshTokenExpiresIn = new Date(now + refreshTokenValidTime);
        String refreshToken = Jwts.builder()
                                  .setExpiration(refreshTokenExpiresIn)
                                  .signWith(key, SignatureAlgorithm.HS256)
                                  .compact();

        return TokenDto.builder()
                       .grantType("Bearer")
                       .accessToken(accessToken)
                       .refreshToken(refreshToken)
                       .memberId(member.id())
                       .refreshTokenExpirationTime(this.refreshTokenValidTime)
                       .build();
    }
}