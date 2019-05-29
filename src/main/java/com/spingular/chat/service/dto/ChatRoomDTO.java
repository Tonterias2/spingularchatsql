package com.spingular.chat.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.spingular.chat.domain.ChatRoom} entity.
 */
public class ChatRoomDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    @NotNull
    @Size(min = 2, max = 50)
    private String roomName;

    @Size(min = 2, max = 250)
    private String roomDescription;

    private Boolean privateRoom;


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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public Boolean isPrivateRoom() {
        return privateRoom;
    }

    public void setPrivateRoom(Boolean privateRoom) {
        this.privateRoom = privateRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChatRoomDTO chatRoomDTO = (ChatRoomDTO) o;
        if (chatRoomDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chatRoomDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChatRoomDTO{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", roomName='" + getRoomName() + "'" +
            ", roomDescription='" + getRoomDescription() + "'" +
            ", privateRoom='" + isPrivateRoom() + "'" +
            "}";
    }
}
