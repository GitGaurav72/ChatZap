package com.novaedge.chatzap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novaedge.chatzap.api.entity.conversationEntity;

@Repository
public interface ConversationRepository extends JpaRepository<conversationEntity, Long>{
	

}
