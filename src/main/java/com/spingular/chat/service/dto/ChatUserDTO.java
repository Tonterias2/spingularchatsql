package com.spingular.chat.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.spingular.chat.domain.ChatUser} entity.
 */
public class ChatUserDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    private Boolean bannedUser;


    private Long userId;

    private Set<ChatRoomDTO> chatRooms = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean isBannedUser() {
        return bannedUser;
    }

    public void setBannedUser(Boolean bannedUser) {
        this.bannedUser = bannedUser;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<ChatRoomDTO> getChatRooms() {
        return chatRooms;
    }

    public void setChatRooms(Set<ChatRoomDTO> chatRooms) {
        this.chatRooms = chatRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChatUserDTO chatUserDTO = (ChatUserDTO) o;
        if (chatUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chatUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChatUserDTO{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", bannedUser='" + isBannedUser() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
