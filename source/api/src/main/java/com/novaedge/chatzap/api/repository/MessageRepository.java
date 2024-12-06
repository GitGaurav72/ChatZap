package com.novaedge.chatzap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.novaedge.chatzap.api.entity.messageEntity;
import java.util.*;


@Repository
public interface MessageRepository extends JpaRepository<messageEntity, Long> {

 //   @Query("select m from messageEntity m where m.conversations = :conversationId")
	List<messageEntity> findByConversationOrderByTimestampDesc(Long conversion_id);
}

