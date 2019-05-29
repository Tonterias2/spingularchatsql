package com.spingular.chat.service;

import com.spingular.chat.service.dto.ChatUserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.spingular.chat.domain.ChatUser}.
 */
public interface ChatUserService {

    /**
     * Save a chatUser.
     *
     * @param chatUserDTO the entity to save.
     * @return the persisted entity.
     */
    ChatUserDTO save(ChatUserDTO chatUserDTO);

    /**
     * Get all the chatUsers.
     *
     * @return the list of entities.
     */
    List<ChatUserDTO> findAll();

    /**
     * Get all the chatUsers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<ChatUserDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" chatUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChatUserDTO> findOne(Long id);

    /**
     * Delete the "id" chatUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
