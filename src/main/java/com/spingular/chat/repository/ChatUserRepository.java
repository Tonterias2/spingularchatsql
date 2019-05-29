package com.spingular.chat.repository;

import com.spingular.chat.domain.ChatUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the ChatUser entity.
 */
@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Long>, JpaSpecificationExecutor<ChatUser> {

    @Query(value = "select distinct chatUser from ChatUser chatUser left join fetch chatUser.chatRooms",
        countQuery = "select count(distinct chatUser) from ChatUser chatUser")
    Page<ChatUser> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct chatUser from ChatUser chatUser left join fetch chatUser.chatRooms")
    List<ChatUser> findAllWithEagerRelationships();

    @Query("select chatUser from ChatUser chatUser left join fetch chatUser.chatRooms where chatUser.id =:id")
    Optional<ChatUser> findOneWithEagerRelationships(@Param("id") Long id);

}
