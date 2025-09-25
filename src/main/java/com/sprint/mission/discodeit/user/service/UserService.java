package com.sprint.mission.discodeit.user.service;

import com.sprint.mission.discodeit.user.UserDto.Request;
import com.sprint.mission.discodeit.user.UserDto.Response;
import com.sprint.mission.discodeit.user.UserDto.ResponseWithLastActivatedAt;
import com.sprint.mission.discodeit.user.UserDto.ResponseWithOnline;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public interface UserService {

    Response createUser(Request request, MultipartFile userProfile);

    ResponseWithOnline getUserById(UUID id);

    ResponseWithLastActivatedAt getUserByUsernameAndPassword(String username, String password);

    Set<ResponseWithOnline> getUsers();

    Response updateUserById(UUID id, Request request, MultipartFile userUpdateRequest);

    ResponseWithLastActivatedAt updateUserById(UUID id, Instant lastActivatedAt);

    void deleteUserById(UUID id);
}
