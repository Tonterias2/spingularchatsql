package com.spingular.chat.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ChatRoom.
 */
@Entity
@Table(name = "chat_room")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(name = "room_name", length = 50, nullable = false)
    private String roomName;

    @Size(min = 2, max = 250)
    @Column(name = "room_description", length = 250)
    private String roomDescription;

    @Column(name = "private_room")
    private Boolean privateRoom;

    @OneToMany(mappedBy = "chatRoom")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ChatMessage> chatMessages = new HashSet<>();

    @OneToMany(mappedBy = "chatRoom")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ChatRoomAllowedUser> chatRoomAllowedUsers = new HashSet<>();

    @ManyToMany(mappedBy = "chatRooms")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<ChatUser> chatUsers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public ChatRoom creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getRoomName() {
        return roomName;
    }

    public ChatRoom roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public ChatRoom roomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
        return this;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public Boolean isPrivateRoom() {
        return privateRoom;
    }

    public ChatRoom privateRoom(Boolean privateRoom) {
        this.privateRoom = privateRoom;
        return this;
    }

    public void setPrivateRoom(Boolean privateRoom) {
        this.privateRoom = privateRoom;
    }

    public Set<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public ChatRoom chatMessages(Set<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
        return this;
    }

    public ChatRoom addChatMessage(ChatMessage chatMessage) {
        this.chatMessages.add(chatMessage);
        chatMessage.setChatRoom(this);
        return this;
    }

    public ChatRoom removeChatMessage(ChatMessage chatMessage) {
        this.chatMessages.remove(chatMessage);
        chatMessage.setChatRoom(null);
        return this;
    }

    public void setChatMessages(Set<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public Set<ChatRoomAllowedUser> getChatRoomAllowedUsers() {
        return chatRoomAllowedUsers;
    }

    public ChatRoom chatRoomAllowedUsers(Set<ChatRoomAllowedUser> chatRoomAllowedUsers) {
        this.chatRoomAllowedUsers = chatRoomAllowedUsers;
        return this;
    }

    public ChatRoom addChatRoomAllowedUser(ChatRoomAllowedUser chatRoomAllowedUser) {
        this.chatRoomAllowedUsers.add(chatRoomAllowedUser);
        chatRoomAllowedUser.setChatRoom(this);
        return this;
    }

    public ChatRoom removeChatRoomAllowedUser(ChatRoomAllowedUser chatRoomAllowedUser) {
        this.chatRoomAllowedUsers.remove(chatRoomAllowedUser);
        chatRoomAllowedUser.setChatRoom(null);
        return this;
    }

    public void setChatRoomAllowedUsers(Set<ChatRoomAllowedUser> chatRoomAllowedUsers) {
        this.chatRoomAllowedUsers = chatRoomAllowedUsers;
    }

    public Set<ChatUser> getChatUsers() {
        return chatUsers;
    }

    public ChatRoom chatUsers(Set<ChatUser> chatUsers) {
        this.chatUsers = chatUsers;
        return this;
    }

    public ChatRoom addChatUser(ChatUser chatUser) {
        this.chatUsers.add(chatUser);
        chatUser.getChatRooms().add(this);
        return this;
    }

    public ChatRoom removeChatUser(ChatUser chatUser) {
        this.chatUsers.remove(chatUser);
        chatUser.getChatRooms().remove(this);
        return this;
    }

    public void setChatUsers(Set<ChatUser> chatUsers) {
        this.chatUsers = chatUsers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChatRoom)) {
            return false;
        }
        return id != null && id.equals(((ChatRoom) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", roomName='" + getRoomName() + "'" +
            ", roomDescription='" + getRoomDescription() + "'" +
            ", privateRoom='" + isPrivateRoom() + "'" +
            "}";
    }
}
