package com.sprint.mission.discodeit.user.api;

import com.sprint.mission.discodeit.user.UserDto.*;
import com.sprint.mission.discodeit.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController implements UserApiSpec {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Response> createUser(
            @RequestPart("userCreateRequest") @Valid Request request,
            @RequestPart(name = "profile", required = false) MultipartFile userProfile
    ) {
        // TODO null check
        Response body = userService.createUser(request, userProfile);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public ResponseEntity<Set<ResponseWithOnline>> getUsers() {
        Set<ResponseWithOnline> body = userService.getUsers();
        return ResponseEntity.ok(body);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseWithOnline> getUserById(@PathVariable UUID id) {
        ResponseWithOnline body = userService.getUserById(id);
        return ResponseEntity.ok(body);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateUserById(
            @PathVariable UUID id,
            @RequestBody @Valid Request request,
            @RequestPart MultipartFile userUpdateRequest
    ) {
        Response body = userService.updateUserById(id, request, userUpdateRequest);
        return ResponseEntity.ok(body);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PatchMapping("/{id}/status")
    public ResponseEntity<ResponseWithLastActivatedAt> updateUserById(
            @PathVariable UUID id,
            @RequestBody @Valid RequestWithLastActivateAt request
    ) {
        ResponseWithLastActivatedAt body = userService.updateUserById(id, request.lastActivatedAt());
        return ResponseEntity.ok(body);
    }
}
