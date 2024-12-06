package com.novaedge.chatzap.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novaedge.chatzap.api.entity.conversationEntity;
import com.novaedge.chatzap.api.entity.convrPrtcpntEntity;
import com.novaedge.chatzap.api.entity.messageEntity;
import com.novaedge.chatzap.api.entity.userEntity;
import com.novaedge.chatzap.api.repository.ConversationRepository;
import com.novaedge.chatzap.api.repository.ConvrPartcpntRepository;
import com.novaedge.chatzap.api.repository.UserRepository;
import com.novaedge.chatzap.api.services.MessageService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user/api")
public class UsrMessageController {
	
	@Autowired
	private MessageService  messageService;
	
	@Autowired
	private ConvrPartcpntRepository convrPartcpntRepository;
	
	@Autowired
	private ConversationRepository conversationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
    
    
//    @PostMapping("/messages/send")
//    public messageEntity sendMessage(@RequestBody messageEntity msg) {
//        messageEntity savedMsg = messageService.sndMsg(msg);
//        messagingTemplate.convertAndSend("/topic/messages/" + msg.getReceiver().getId(), savedMsg);
//        return savedMsg;
//    }
//
//    @MessageMapping("/send/{receiverId}")
//    @SendTo("/topic/messages/{receiverId}")
//    public messageEntity sendMsgToReceiver(@RequestBody messageEntity msg, @PathVariable Long receiverId) {
//        return messageService.sndMsg(msg);
//    } 
    
	@PostMapping("/messages/send")
	public messageEntity sendMessage(@RequestBody messageEntity msg) 
	{
		return messageService.sndMsg(msg);
		
	}
	
//	@PostMapping("/{sendr}/sendMsg/{rcvr}")
//	@CrossOrigin(origins = "http://localhost:4200")
//	public messageEntity sendMsg(@PathVariable("sendr") Long sendr, @PathVariable("rcvr") Long rcvr, @RequestBody String msg) {
//	    return messageService.sendMsg(sendr, rcvr, msg);
//	}
	
	@GetMapping("/{sendr}/Message/{rcvr}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<messageEntity> chatList(@PathVariable("sendr") Long sendr, @PathVariable("rcvr") Long rcvr) {
	    // You can now access the authenticated user's details
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  
//	    String username = authentication.getName(); // Extract the username (or ID) of the authenticated user
//	    System.out.println(username);
	    // Proceed with your logic
	    return messageService.chatMsg(sendr, rcvr);
	    
	}

	
	@GetMapping("/messages")
	public messageEntity DeliverMessage(@RequestBody messageEntity msg) {
		return null;
		
	}
	
	@PutMapping("/messages/read")
	public messageEntity sendRead (@RequestBody messageEntity msg) {
		return null;
		
	}
	
	@PutMapping("/messages/conv")
	public List<convrPrtcpntEntity> addConv (){
		List<convrPrtcpntEntity> l = new ArrayList<>();
//		for(long i = 101; i < 47052 ; i=i+50) {
		long i = 40801;
			for(long j = 52; j < 72 ; j++) {
				for(long k = 1 ; k< 3; k++) {
					if(j==k) continue;
			conversationEntity convEntity = conversationRepository.findById(i).get();
			userEntity UserEntity = userRepository.findById(j).get();
			convrPrtcpntEntity convrPrtcpnt = new convrPrtcpntEntity();
			convrPrtcpnt.setConversation(convEntity);
			convrPrtcpnt.setUser(UserEntity);
			convrPartcpntRepository.save(convrPrtcpnt);
			i+=50;
			conversationEntity convEntity1 = conversationRepository.findById(i).get();
			userEntity UserEntity1 = userRepository.findById(k).get();
			convrPrtcpntEntity convrPrtcpnt1 = new convrPrtcpntEntity();
			convrPrtcpnt1.setConversation(convEntity1);
			convrPrtcpnt1.setUser(UserEntity1);
			convrPartcpntRepository.save(convrPrtcpnt1);
			l.add(convrPrtcpnt);
			l.add(convrPrtcpnt1);
			i+=50;
				}
			}
			return l;
			
//		}
//		convrPartcpntRepository
	}
	
	

}
