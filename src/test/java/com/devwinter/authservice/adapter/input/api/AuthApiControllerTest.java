package com.devwinter.authservice.adapter.input.api;

import com.devwinter.authservice.adapter.input.api.dto.AccessTokenIssuance;
import com.devwinter.authservice.adapter.input.api.dto.MemberLogin;
import com.devwinter.authservice.adapter.input.api.dto.MemberLogout;
import com.devwinter.authservice.application.port.input.ExistRefreshTokenValidUseCase;
import com.devwinter.authservice.application.port.input.GenerateTokenUseCase;
import com.devwinter.authservice.application.port.input.LoginMemberUseCase;
import com.devwinter.authservice.application.port.input.LogoutMemberUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static com.devwinter.authservice.adapter.input.api.AbstractRestDocs.FieldDescriptorDto.descriptor;
import static com.devwinter.authservice.adapter.input.api.AuthApiDocumentInfo.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthApiController.class)
class AuthApiControllerTest extends AbstractRestDocs {
    @MockBean
    private LoginMemberUseCase loginMemberUseCase;
    @MockBean
    private GenerateTokenUseCase generateTokenUseCase;
    @MockBean
    private LogoutMemberUseCase logoutMemberUseCase;
    @MockBean
    private ExistRefreshTokenValidUseCase existRefreshTokenValidUseCase;

    @Test
    @DisplayName("로그인 API 테스트")
    void loginApiTest() throws Exception {
        String body = requestToJson(new MemberLogin.Request("test@gamil.com", "aBcD2FG!"));

        GenerateTokenUseCase.TokenDto tokenDto = new GenerateTokenUseCase.TokenDto("Bearer", "access-token",
                "refresh-token", 1L, 1000L);

        // given
        given(loginMemberUseCase.credential(any())).willReturn(tokenDto);

        // when & then
        mockMvc.perform(post("/login")
                       .contentType(APPLICATION_JSON)
                       .content(body))
               .andDo(print())
               .andDo(
                       document(
                               LOGIN,
                               MemberLogin.Request.class,
                               MemberLogin.Response.class,
                               Arrays.asList(
                                       descriptor("email", STRING, "아이디"),
                                       descriptor("password", STRING, "비밀번호")
                               ),
                               Arrays.asList(
                                       descriptor("result.status", STRING, "결과"),
                                       descriptor("result.message", STRING, "메세지"),
                                       descriptor("body.memberId", NUMBER, "로그인 회원 id"),
                                       descriptor("body.accessToken", STRING, "access-token"),
                                       descriptor("body.grantType", STRING, "토큰 타입"),
                                       descriptor("body.refreshToken", STRING, "refresh-token")
                               )
                       )
               )
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그아웃 API 테스트")
    void logoutApiTest() throws Exception {
        // given
        doNothing().when(logoutMemberUseCase)
                .logout(anyString());

        // when & then
        mockMvc.perform(post("/logout")
                       .contentType(APPLICATION_JSON)
                       .headers(auth()))
               .andDo(print())
               .andDo(
                       document(
                               LOGOUT,
                               null,
                               MemberLogout.Response.class,
                               null,
                               Arrays.asList(
                                       descriptor("result.status", STRING, "결과"),
                                       descriptor("result.message", STRING, "메세지")
                               )
                       )
               )
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("refresh-token 요청 API 테스트")
    void refreshTokenApiTest() throws Exception {
        // given
        GenerateTokenUseCase.TokenDto tokenDto = new GenerateTokenUseCase.TokenDto("Bearer", "access-token",
                "refresh-token", 1L, 1000L);

        doNothing().when(existRefreshTokenValidUseCase)
                   .valid(anyString(), anyString());
        given(generateTokenUseCase.generate(any())).willReturn(tokenDto);

        // when & then
        mockMvc.perform(post("/refresh-token")
                       .contentType(APPLICATION_JSON)
                       .headers(auth())
                       .header("RefreshToken", "refresh-token")
               )
               .andDo(print())
               .andDo(
                       document(
                               REFRESH_TOKEN,
                               null,
                               AccessTokenIssuance.Response.class,
                               null,
                               Arrays.asList(
                                       descriptor("result.status", STRING, "결과"),
                                       descriptor("result.message", STRING, "메세지"),
                                       descriptor("body.memberId", NUMBER, "로그인 회원 id"),
                                       descriptor("body.accessToken", STRING, "access-token"),
                                       descriptor("body.grantType", STRING, "토큰 타입"),
                                       descriptor("body.refreshToken", STRING, "refresh-token")
                               )
                       )
               )
               .andExpect(status().isOk());
    }
}