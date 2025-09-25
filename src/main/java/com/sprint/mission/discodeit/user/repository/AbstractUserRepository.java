package com.sprint.mission.discodeit.user.repository;

import com.sprint.mission.discodeit.common.exception.DiscodeitException.DiscodeitPersistenceException;
import com.sprint.mission.discodeit.common.persistence.Validator;
import com.sprint.mission.discodeit.user.domain.User;

import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractUserRepository implements UserRepository {

    protected AbstractUserRepository() {
    }

    @Override
    public User save(User user) {
        Validator<User> validator = Validator.identity();
        if (user.isNew()) {
            validator = validator.and(Validator.uniqueKey(
                    User::getId,
                    userKey -> new DiscodeitPersistenceException(
                            "User ID already exists: '%s'".formatted(userKey.getId()))
            ));
        }
        validator = validator.and(Validator.uniqueKey(
                userKey -> userKey.getUserCredentials().username(),
                userKey -> new DiscodeitPersistenceException(
                        "User username already exists: '%s'".formatted(userKey.getUserCredentials().username()))
        )).and(Validator.uniqueKey(
                User::getEmail,
                userKey -> new DiscodeitPersistenceException(
                        "User email already exists: '%s'".formatted(userKey.getEmail()))
        ));
        Map<UUID, User> data = getData();
        User validated = validator.validate(data, user);
        data.put(validated.getId(), validated);
        flush(data);
        return validated;
    }

    @Override
    public User findById(UUID id) {
        Map<UUID, User> data = getData();
        User user = data.get(id);
        if (user == null) {
            throw new DiscodeitPersistenceException("User not found for ID: '%s'".formatted(id));
        }
        return user;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        Map<UUID, User> data = getData();
        Map<String, User> usernameToUser = groupByUsername(data);
        User user = usernameToUser.get(username);
        if (user == null) {
            throw new DiscodeitPersistenceException("User not found for Username: '%s'".formatted(username));
        }
        if (!user.getUserCredentials().password().equals(password)) {
            throw new DiscodeitPersistenceException("User not found for Password: '%s'".formatted(username));
        }
        return user;
    }

    @Override
    public Iterable<User> findAll() {
        Map<UUID, User> data = getData();
        return new HashSet<>(data.values());
    }

    @Override
    public void deleteById(UUID id) {
        Map<UUID, User> data = getData();
        data.remove(id);
        flush(data);
    }

    private Map<String, User> groupByUsername(Map<UUID, User> data) {
        return data.values()
                .stream()
                .collect(Collectors.toMap(user -> user.getUserCredentials().username(), Function.identity()));
    }

    protected abstract Map<UUID, User> getData();

    protected void flush(Map<?, ?> data) {
    }
}
