package com.devwinter.authservice.adapter.input.api;

import com.devwinter.authservice.adapter.input.api.dto.BaseResponse;
import com.devwinter.authservice.adapter.input.api.dto.MemberLogin;
import com.devwinter.authservice.adapter.input.api.dto.MemberLogout;
import com.devwinter.authservice.adapter.input.api.dto.MemberValid;
import com.devwinter.authservice.application.port.input.AuthMemberUseCase;
import com.devwinter.authservice.application.port.input.DeleteRefreshTokenUseCase;
import com.devwinter.authservice.application.port.input.GenerateTokenUseCase;
import com.devwinter.authservice.application.port.input.GenerateTokenUseCase.TokenDto;
import com.devwinter.authservice.application.port.input.GetMemberValidQuery;
import com.devwinter.authservice.application.port.input.GetMemberValidQuery.ValidMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthMemberUseCase authMemberUseCase;
    private final GenerateTokenUseCase generateTokenUseCase;
    private final DeleteRefreshTokenUseCase deleteRefreshTokenUseCase;
    private final GetMemberValidQuery getMemberValidQuery;

    @PostMapping("/login")
    public BaseResponse<MemberLogin.Response> login(HttpServletResponse response, @Valid @RequestBody MemberLogin.Request request) {
        AuthMemberUseCase.AuthMemberDto credential = authMemberUseCase.credential(request.toCommand());
        TokenDto tokenDto = generateTokenUseCase.generate(credential);

        if (Objects.nonNull(tokenDto.memberId())) {
            response.setHeader("MemberId", tokenDto.memberId().toString());
        }

        return MemberLogin.Response.success(tokenDto);
    }

    @PostMapping("/logout")
    public BaseResponse<MemberLogout.Response> logout(@RequestHeader("email") String email) {
        deleteRefreshTokenUseCase.delete(email);
        return MemberLogout.Response.success();
    }

    @GetMapping("/{memberId}/valid")
    public BaseResponse<MemberValid.Response> getMemberValid(@PathVariable Long memberId) {
        ValidMemberDto memberDto = getMemberValidQuery.valid(memberId);
        return MemberValid.Response.success(memberDto);
    }
}
