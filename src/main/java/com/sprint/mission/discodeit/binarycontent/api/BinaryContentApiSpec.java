package com.sprint.mission.discodeit.binarycontent.api;

import com.sprint.mission.discodeit.binarycontent.BinaryContentDto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "Binary Content API")
public interface BinaryContentApiSpec {

    @Operation(summary = "Get binary content by ID")
    ResponseEntity<Response> getBinaryContentById(UUID id);
}
