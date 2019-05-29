package com.spingular.chat.service.mapper;

import com.spingular.chat.domain.*;
import com.spingular.chat.service.dto.ChatUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChatUser} and its DTO {@link ChatUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ChatRoomMapper.class})
public interface ChatUserMapper extends EntityMapper<ChatUserDTO, ChatUser> {

    @Mapping(source = "user.id", target = "userId")
    ChatUserDTO toDto(ChatUser chatUser);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "chatMessages", ignore = true)
    @Mapping(target = "chatRoomAllowedUsers", ignore = true)
    ChatUser toEntity(ChatUserDTO chatUserDTO);

    default ChatUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        ChatUser chatUser = new ChatUser();
        chatUser.setId(id);
        return chatUser;
    }
}
