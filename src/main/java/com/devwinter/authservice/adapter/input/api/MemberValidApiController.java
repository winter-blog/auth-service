package com.devwinter.authservice.adapter.input.api;

import com.devwinter.authservice.adapter.input.api.dto.BaseResponse;
import com.devwinter.authservice.adapter.input.api.dto.MemberValid;
import com.devwinter.authservice.application.port.input.GetMemberValidQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberValidApiController {
    private final GetMemberValidQuery getMemberValidQuery;

    @GetMapping("/{memberId}/valid")
    public BaseResponse<MemberValid.Response> getMemberValid(@PathVariable Long memberId) {
        GetMemberValidQuery.ValidMemberDto memberDto = getMemberValidQuery.valid(memberId);
        return MemberValid.Response.success(memberDto);
    }
}
