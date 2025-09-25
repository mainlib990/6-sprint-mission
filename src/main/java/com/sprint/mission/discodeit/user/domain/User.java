package com.sprint.mission.discodeit.user.domain;

import com.sprint.mission.discodeit.common.persistence.BaseEntity;
import com.sprint.mission.discodeit.user.domain.UserException.InvalidUserEmailFormatException;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.time.Instant;
import java.util.UUID;
import java.util.regex.Pattern;

public class User extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 4L;

    private static final Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    private final UserCredentials userCredentials;
    private final UserStatus userStatus;
    private final String email;

    private User(
            @Nullable UUID id,
            @Nullable Instant createdAt,
            @Nullable Instant updatedAt,
            UserCredentials userCredentials,
            UserStatus userStatus,
            String email
    ) {
        super(id, createdAt, updatedAt);
        if (!mailPattern.matcher(email).matches()) {
            throw new InvalidUserEmailFormatException(email);
        }
        this.userCredentials = userCredentials;
        this.userStatus = userStatus;
        this.email = email.trim();
    }

    public static User of(
            UserCredentials userCredentials,
            UserStatus userStatus,
            String email
    ) {
        return new User(
                null,
                null,
                null,
                userCredentials,
                userStatus,
                email
        );
    }

    public User withUserCredentials(UserCredentials userCredentials) {
        return new User(
                this.getId(),
                this.getCreatedAt(),
                this.getUpdatedAt(),
                userCredentials,
                this.userStatus,
                this.email
        );
    }

    public User withUserStatus(UserStatus userStatus) {
        return new User(
                this.getId(),
                this.getCreatedAt(),
                this.getUpdatedAt(),
                this.userCredentials,
                userStatus,
                this.email
        );
    }

    public User with(String email) {
        return new User(
                this.getId(),
                this.getCreatedAt(),
                this.getUpdatedAt(),
                this.userCredentials,
                this.userStatus,
                email
        );
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userCredentials=" + userCredentials +
                ", userStatus=" + userStatus +
                ", email='" + email + '\'' +
                "} " + super.toString();
    }
}
