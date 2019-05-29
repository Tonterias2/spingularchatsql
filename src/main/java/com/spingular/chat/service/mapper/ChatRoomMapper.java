package com.spingular.chat.service.mapper;

import com.spingular.chat.domain.*;
import com.spingular.chat.service.dto.ChatRoomDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChatRoom} and its DTO {@link ChatRoomDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ChatRoomMapper extends EntityMapper<ChatRoomDTO, ChatRoom> {


    @Mapping(target = "chatMessages", ignore = true)
    @Mapping(target = "chatRoomAllowedUsers", ignore = true)
    @Mapping(target = "chatUsers", ignore = true)
    ChatRoom toEntity(ChatRoomDTO chatRoomDTO);

    default ChatRoom fromId(Long id) {
        if (id == null) {
            return null;
        }
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setId(id);
        return chatRoom;
    }
}
