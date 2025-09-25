package com.sprint.mission.discodeit.user.api;

import com.sprint.mission.discodeit.user.UserDto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@Tag( name = "User API")
public interface UserApiSpec {

    @Operation(summary = "Create a new user")
    ResponseEntity<Response> createUser(Request request, MultipartFile userProfile);

    @Operation(summary = "Get all users")
    ResponseEntity<Set<ResponseWithOnline>> getUsers();

    @Operation(summary = "Get user by ID")
    ResponseEntity<ResponseWithOnline> getUserById(UUID id);

    @Operation(summary = "Update user by ID")
    ResponseEntity<Response> updateUserById(UUID id, Request request, MultipartFile userUpdateRequest);

    @Operation(summary = "Delete user by ID")
    ResponseEntity<Void> deleteUserById(UUID id);

    @Operation(summary = "Update the last activated at date of a user")
    ResponseEntity<ResponseWithLastActivatedAt> updateUserById(UUID id, RequestWithLastActivateAt request);
}
