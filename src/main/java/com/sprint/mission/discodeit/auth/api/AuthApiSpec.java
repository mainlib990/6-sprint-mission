package com.sprint.mission.discodeit.auth.api;

import com.sprint.mission.discodeit.auth.AuthDto.Request;
import com.sprint.mission.discodeit.auth.AuthDto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Auth API")
public interface AuthApiSpec {

    @Operation(summary = "User login")
    ResponseEntity<Response> login(Request request);
}
