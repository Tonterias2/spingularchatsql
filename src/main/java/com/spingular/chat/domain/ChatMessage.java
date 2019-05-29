package com.spingular.chat.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ChatMessage.
 */
@Entity
@Table(name = "chat_message")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "message_sent_at", nullable = false)
    private String messageSentAt;

    @NotNull
    @Size(max = 65000)
    @Column(name = "message", length = 65000, nullable = false)
    private String message;

    @Column(name = "is_delivered")
    private Boolean isDelivered;

    @Column(name = "is_offensive")
    private Boolean isOffensive;

    @ManyToOne
    @JsonIgnoreProperties("chatMessages")
    private ChatRoom chatRoom;

    @ManyToOne
    @JsonIgnoreProperties("chatMessages")
    private ChatUser chatUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageSentAt() {
        return messageSentAt;
    }

    public ChatMessage messageSentAt(String messageSentAt) {
        this.messageSentAt = messageSentAt;
        return this;
    }

    public void setMessageSentAt(String messageSentAt) {
        this.messageSentAt = messageSentAt;
    }

    public String getMessage() {
        return message;
    }

    public ChatMessage message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isIsDelivered() {
        return isDelivered;
    }

    public ChatMessage isDelivered(Boolean isDelivered) {
        this.isDelivered = isDelivered;
        return this;
    }

    public void setIsDelivered(Boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public Boolean isIsOffensive() {
        return isOffensive;
    }

    public ChatMessage isOffensive(Boolean isOffensive) {
        this.isOffensive = isOffensive;
        return this;
    }

    public void setIsOffensive(Boolean isOffensive) {
        this.isOffensive = isOffensive;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public ChatMessage chatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        return this;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public ChatUser getChatUser() {
        return chatUser;
    }

    public ChatMessage chatUser(ChatUser chatUser) {
        this.chatUser = chatUser;
        return this;
    }

    public void setChatUser(ChatUser chatUser) {
        this.chatUser = chatUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChatMessage)) {
            return false;
        }
        return id != null && id.equals(((ChatMessage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
            "id=" + getId() +
            ", messageSentAt='" + getMessageSentAt() + "'" +
            ", message='" + getMessage() + "'" +
            ", isDelivered='" + isIsDelivered() + "'" +
            ", isOffensive='" + isIsOffensive() + "'" +
            "}";
    }
}
