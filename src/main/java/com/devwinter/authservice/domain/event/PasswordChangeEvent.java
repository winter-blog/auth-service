package com.devwinter.authservice.domain.event;

public record PasswordChangeEvent(Long id, String email) {
}
