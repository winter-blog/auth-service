package com.devwinter.authservice.adapter.input.api;

import com.devwinter.authservice.adapter.input.api.dto.BaseResponse;
import com.devwinter.authservice.adapter.input.api.dto.MemberLogin;
import com.devwinter.authservice.application.port.input.AuthMemberUseCase;
import com.devwinter.authservice.application.port.input.TokenGenerateUseCase;
import com.devwinter.authservice.application.port.input.TokenGenerateUseCase.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthMemberUseCase authMemberUseCase;
    private final TokenGenerateUseCase tokenGenerateUseCase;

    @PostMapping("/login")
    public BaseResponse<MemberLogin.Response> login(
            HttpServletResponse response,
            @Valid @RequestBody MemberLogin.Request request) {
        AuthMemberUseCase.AuthMemberDto credential = authMemberUseCase.credential(request.toCommand());
        TokenDto tokenDto = tokenGenerateUseCase.generate(credential);

        if (Objects.nonNull(tokenDto.userId())) {
            response.setHeader("User-Id", tokenDto.userId().toString());
        }

        return MemberLogin.Response.success(tokenDto);
    }
}
