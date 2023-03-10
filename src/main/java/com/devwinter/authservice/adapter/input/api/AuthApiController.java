package com.devwinter.authservice.adapter.input.api;

import com.devwinter.authservice.adapter.input.api.dto.AccessTokenIssuance;
import com.devwinter.authservice.adapter.input.api.dto.BaseResponse;
import com.devwinter.authservice.adapter.input.api.dto.MemberLogin;
import com.devwinter.authservice.adapter.input.api.dto.MemberLogout;
import com.devwinter.authservice.application.port.input.LoginMemberUseCase;
import com.devwinter.authservice.application.port.input.LoginMemberUseCase.AuthMemberDto;
import com.devwinter.authservice.application.port.input.LogoutMemberUseCase;
import com.devwinter.authservice.application.port.input.ExistRefreshTokenValidUseCase;
import com.devwinter.authservice.application.port.input.GenerateTokenUseCase;
import com.devwinter.authservice.application.port.input.GenerateTokenUseCase.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class AuthApiController {
    private final LoginMemberUseCase loginMemberUseCase;
    private final GenerateTokenUseCase generateTokenUseCase;
    private final LogoutMemberUseCase logoutMemberUseCase;
    private final ExistRefreshTokenValidUseCase existRefreshTokenValidUseCase;

    @PostMapping("/login")
    public BaseResponse<MemberLogin.Response> login(HttpServletResponse response, @Valid @RequestBody MemberLogin.Request request) {
        TokenDto tokenDto = loginMemberUseCase.credential(request.toCommand());

        if (Objects.nonNull(tokenDto.memberId())) {
            response.setHeader("MemberId", tokenDto.memberId()
                                                   .toString());
        }

        return MemberLogin.Response.success(tokenDto);
    }

    @PostMapping("/logout")
    public BaseResponse<MemberLogout.Response> logout(
            @RequestHeader("Email") String email) {
        logoutMemberUseCase.logout(email);
        return MemberLogout.Response.success();
    }

    @PostMapping("/refresh-token")
    public BaseResponse<AccessTokenIssuance.Response> refreshToken(
            @RequestHeader("MemberId") Long id,
            @RequestHeader("Email") String email,
            @RequestHeader("RefreshToken") String refreshToken) {

        existRefreshTokenValidUseCase.valid(email, refreshToken);

        AuthMemberDto authMemberDto = new AuthMemberDto(id, email);
        TokenDto token = generateTokenUseCase.generate(authMemberDto);
        return AccessTokenIssuance.Response.success(token);
    }
}
