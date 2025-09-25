package com.sprint.mission.discodeit.message.api;

import com.sprint.mission.discodeit.message.MessageDto.Request;
import com.sprint.mission.discodeit.message.MessageDto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@Tag(name = "Message API")
public interface MessageApiSpec {

    @Operation(summary = "Create a new message")
    ResponseEntity<Response> createMessage(Request request, MultipartFile file);

    @Operation(summary = "Get messages by channel ID")
    ResponseEntity<Set<Response>> getMessagesByChannelId(UUID channelId);

    @Operation(summary = "Get message by ID")
    ResponseEntity<Response> updateMessageById(UUID id, Request request);

    @Operation(summary = "Delete message by ID")
    ResponseEntity<Response> deleteMessageById(UUID id);
}
