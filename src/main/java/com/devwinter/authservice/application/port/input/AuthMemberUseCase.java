package com.devwinter.authservice.application.port.input;

import com.devwinter.authservice.application.dto.AuthMemberDto;

public interface AuthMemberUseCase {

    AuthMemberDto credential(AuthMemberCommand command);

    record AuthMemberCommand(String email, String password) {

    }


}
