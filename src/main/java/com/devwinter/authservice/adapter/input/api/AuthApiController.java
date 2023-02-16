package com.devwinter.authservice.adapter.input.api;

import com.devwinter.authservice.adapter.input.api.dto.*;
import com.devwinter.authservice.application.port.input.*;
import com.devwinter.authservice.application.port.input.AuthMemberUseCase.AuthMemberDto;
import com.devwinter.authservice.application.port.input.GenerateTokenUseCase.TokenDto;
import com.devwinter.authservice.application.port.input.GetMemberValidQuery.ValidMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthMemberUseCase authMemberUseCase;
    private final GetMemberValidQuery getMemberValidQuery;
    private final GenerateTokenUseCase generateTokenUseCase;
    private final DeleteRefreshTokenUseCase deleteRefreshTokenUseCase;
    private final ExistRefreshTokenValidUseCase existRefreshTokenValidUseCase;

    @PostMapping("/login")
    public BaseResponse<MemberLogin.Response> login(HttpServletResponse response, @Valid @RequestBody MemberLogin.Request request) {
        AuthMemberDto credential = authMemberUseCase.credential(request.toCommand());
        TokenDto tokenDto = generateTokenUseCase.generate(credential);

        if (Objects.nonNull(tokenDto.memberId())) {
            response.setHeader("MemberId", tokenDto.memberId().toString());
        }

        return MemberLogin.Response.success(tokenDto);
    }

    @PostMapping("/logout")
    public BaseResponse<MemberLogout.Response> logout(@RequestHeader("Email") String email) {
        deleteRefreshTokenUseCase.delete(email);
        return MemberLogout.Response.success();
    }

    @PostMapping("/refresh-token")
    public BaseResponse<RefreshToken.Response> refreshToken(
            @RequestHeader("MemberId") Long id,
            @RequestHeader("Email") String email,
            @RequestHeader("RefreshToken") String refreshToken) {

        existRefreshTokenValidUseCase.valid(email, refreshToken);

        AuthMemberDto authMemberDto = new AuthMemberDto(id, email);
        TokenDto token = generateTokenUseCase.generate(authMemberDto);
        return RefreshToken.Response.success(token);
    }


    @GetMapping("/{memberId}/valid")
    public BaseResponse<MemberValid.Response> getMemberValid(@PathVariable Long memberId) {
        ValidMemberDto memberDto = getMemberValidQuery.valid(memberId);
        return MemberValid.Response.success(memberDto);
    }
}
