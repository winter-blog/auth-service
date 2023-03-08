package com.devwinter.authservice.application.port.input;

import com.devwinter.authservice.application.dto.AuthMemberDto;

public interface GetMemberValidQuery {

    AuthMemberDto valid(Long memberId);

}
