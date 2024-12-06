package com.novaedge.chatzap.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novaedge.chatzap.api.repository.ConvrPartcpntRepository;

@Service
public class ConversationUserService {
	
	@Autowired
	private ConvrPartcpntRepository convrPartcpntRepository;
	
	public Long findConverIdByUsrId(Long Sernder, Long Reciver) {
		Long id = convrPartcpntRepository.findConversatioIdByUserId(Sernder, Reciver);
		return id;
	}

}
