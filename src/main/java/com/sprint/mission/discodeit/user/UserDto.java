package com.sprint.mission.discodeit.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.UUID;

public final class UserDto {

    private UserDto() {
    }

    public record Request(
            @NotBlank String username,
            @NotBlank String password,
            @NotNull @Email String email
    ) {
    }

    public record RequestWithLastActivateAt(@NotNull Instant lastActivatedAt) {
    }

    public record Response(
            UUID id,
            Instant createdAt,
            Instant updatedAt,
            String username,
            String email
    ) {
    }

    public record ResponseWithOnline(
            UUID id,
            Instant createdAt,
            Instant updatedAt,
            String username,
            String email,
            @Nullable UUID userProfileId,
            Boolean online
    ) {
    }

    public record ResponseWithLastActivatedAt(
            UUID id,
            Instant createdAt,
            Instant updatedAt,
            String username,
            String email,
            Instant lastActivatedAt
    ) {
    }
}
