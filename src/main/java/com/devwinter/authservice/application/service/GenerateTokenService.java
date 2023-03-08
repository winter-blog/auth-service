package com.devwinter.authservice.application.service;

import com.devwinter.authservice.application.port.input.AuthMemberUseCase.AuthMemberDto;
import com.devwinter.authservice.application.port.input.GenerateTokenUseCase;
import com.devwinter.authservice.application.port.output.SaveRefreshTokenPort;
import com.devwinter.authservice.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenerateTokenService implements GenerateTokenUseCase {

    private final JwtTokenProvider jwtTokenProvider;
    private final SaveRefreshTokenPort saveRefreshTokenPort;

    @Override
    public TokenDto generate(AuthMemberDto authMemberDto) {

        TokenDto token = jwtTokenProvider.generateToken(authMemberDto);
        saveRefreshTokenPort.save(authMemberDto.email(), token);

        return token;
    }
}
