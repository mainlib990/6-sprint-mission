package com.sprint.mission.discodeit.user.domain;

import com.sprint.mission.discodeit.user.domain.UserException.BlankUserPasswordException;
import com.sprint.mission.discodeit.user.domain.UserException.BlankUsernameExcpetion;

import java.io.Serial;
import java.io.Serializable;

public record UserCredentials(
        String username,
        String password
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    public UserCredentials {
        if (username.isBlank()) {
            throw new BlankUsernameExcpetion();
        }
        if (password.isBlank()) {
            throw new BlankUserPasswordException();
        }
        username = username.trim();
    }
}
