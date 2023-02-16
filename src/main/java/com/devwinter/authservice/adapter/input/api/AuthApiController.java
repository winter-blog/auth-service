package com.devwinter.authservice.adapter.input.api;

import com.devwinter.authservice.adapter.input.api.dto.MemberLogin;
import com.devwinter.authservice.application.port.input.AuthMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthMemberUseCase authMemberUseCase;

    @PostMapping("/login")
    public void login(@Valid @RequestBody MemberLogin.Request request) {
        AuthMemberUseCase.AuthMemberDto credential = authMemberUseCase.credential(request.toCommand());



    }
}
