package com.sprint.mission.discodeit.user.domain;

import com.sprint.mission.discodeit.common.exception.DiscodeitException;

public sealed class UserException extends DiscodeitException {

    public UserException(String message) {
        super(message);
    }

    public static final class BlankUsernameExcpetion extends UserException {

        public BlankUsernameExcpetion() {
            super("Username cannot be blank");
        }
    }

    public static final class BlankUserPasswordException extends UserException {

        public BlankUserPasswordException() {
            super("User password cannot be blank.");
        }
    }

    public static final class InvalidUserEmailFormatException extends UserException {

        public InvalidUserEmailFormatException(String email) {
            super("User email pattern is invalid: '%s'".formatted(email));
        }
    }
}
