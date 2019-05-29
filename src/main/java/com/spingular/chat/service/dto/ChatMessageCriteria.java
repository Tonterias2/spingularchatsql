package com.spingular.chat.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.spingular.chat.domain.ChatMessage} entity. This class is used
 * in {@link com.spingular.chat.web.rest.ChatMessageResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /chat-messages?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ChatMessageCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter messageSentAt;

    private StringFilter message;

    private BooleanFilter isDelivered;

    private BooleanFilter isOffensive;

    private LongFilter chatRoomId;

    private LongFilter chatUserId;

    public ChatMessageCriteria(){
    }

    public ChatMessageCriteria(ChatMessageCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.messageSentAt = other.messageSentAt == null ? null : other.messageSentAt.copy();
        this.message = other.message == null ? null : other.message.copy();
        this.isDelivered = other.isDelivered == null ? null : other.isDelivered.copy();
        this.isOffensive = other.isOffensive == null ? null : other.isOffensive.copy();
        this.chatRoomId = other.chatRoomId == null ? null : other.chatRoomId.copy();
        this.chatUserId = other.chatUserId == null ? null : other.chatUserId.copy();
    }

    @Override
    public ChatMessageCriteria copy() {
        return new ChatMessageCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMessageSentAt() {
        return messageSentAt;
    }

    public void setMessageSentAt(StringFilter messageSentAt) {
        this.messageSentAt = messageSentAt;
    }

    public StringFilter getMessage() {
        return message;
    }

    public void setMessage(StringFilter message) {
        this.message = message;
    }

    public BooleanFilter getIsDelivered() {
        return isDelivered;
    }

    public void setIsDelivered(BooleanFilter isDelivered) {
        this.isDelivered = isDelivered;
    }

    public BooleanFilter getIsOffensive() {
        return isOffensive;
    }

    public void setIsOffensive(BooleanFilter isOffensive) {
        this.isOffensive = isOffensive;
    }

    public LongFilter getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(LongFilter chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public LongFilter getChatUserId() {
        return chatUserId;
    }

    public void setChatUserId(LongFilter chatUserId) {
        this.chatUserId = chatUserId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ChatMessageCriteria that = (ChatMessageCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(messageSentAt, that.messageSentAt) &&
            Objects.equals(message, that.message) &&
            Objects.equals(isDelivered, that.isDelivered) &&
            Objects.equals(isOffensive, that.isOffensive) &&
            Objects.equals(chatRoomId, that.chatRoomId) &&
            Objects.equals(chatUserId, that.chatUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        messageSentAt,
        message,
        isDelivered,
        isOffensive,
        chatRoomId,
        chatUserId
        );
    }

    @Override
    public String toString() {
        return "ChatMessageCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (messageSentAt != null ? "messageSentAt=" + messageSentAt + ", " : "") +
                (message != null ? "message=" + message + ", " : "") +
                (isDelivered != null ? "isDelivered=" + isDelivered + ", " : "") +
                (isOffensive != null ? "isOffensive=" + isOffensive + ", " : "") +
                (chatRoomId != null ? "chatRoomId=" + chatRoomId + ", " : "") +
                (chatUserId != null ? "chatUserId=" + chatUserId + ", " : "") +
            "}";
    }

}
