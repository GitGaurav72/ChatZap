package com.novaedge.chatzap.api.services;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novaedge.chatzap.api.entity.conversationEntity;
import com.novaedge.chatzap.api.entity.messageEntity;
import com.novaedge.chatzap.api.entity.userEntity;
import com.novaedge.chatzap.api.repository.ConversationRepository;
import com.novaedge.chatzap.api.repository.MessageRepository;
import com.novaedge.chatzap.api.repository.UserRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageDao;
	
	@Autowired
	private ConversationUserService conversationUserService;
	
	@Autowired
	private ConversationRepository conversationRepository;
	
	@Autowired
	private UserRepository userRepository;

	public messageEntity sndMsg(messageEntity msg) {
		
		return messageDao.save(msg);
	}
	
	
	public List<messageEntity> chatMsg(Long sender , Long reciver){
		Long conversion_id = conversationUserService.findConverIdByUsrId(sender, reciver);
		conversationEntity conversation = conversationRepository.findById(conversion_id).get();
		return messageDao.findByConversationOrderByTimestampDesc(conversion_id);
	}


	public messageEntity sendMsg(Long sendr, Long rcvr, String msg) {
		messageEntity  msgEntty = new messageEntity();
		Long conversion_id = conversationUserService.findConverIdByUsrId(sendr, rcvr);
		conversationEntity conversation = conversationRepository.findById(conversion_id).get();
		Optional<userEntity> sndr = userRepository.findById(sendr);
		Optional<userEntity> recever = userRepository.findById(rcvr);
		msgEntty.setContent(msg);
		msgEntty.setSender(sndr.get().getId());
		msgEntty.setReceiver(recever.get().getId());
		msgEntty.setConversation(conversation.getConversationId());
		msgEntty.setTimestamp(LocalDateTime.now());
		msgEntty.setRead(false);
		return messageDao.save(msgEntty);
	}
}
