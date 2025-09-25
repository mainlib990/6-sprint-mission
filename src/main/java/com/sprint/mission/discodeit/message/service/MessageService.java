package com.sprint.mission.discodeit.message.service;

import com.sprint.mission.discodeit.message.MessageDto.Request;
import com.sprint.mission.discodeit.message.MessageDto.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

public interface MessageService {

    Response createMessage(Request request, MultipartFile messageAttachmentRequest);

    Set<Response> getMessagesByChannelId(UUID channelId);

    Response updateMessageById(UUID messageId, Request request);

    void deleteMessageById(UUID messageId);
}
