package com.novaedge.chatzap.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novaedge.chatzap.api.model.UserModel;
import com.novaedge.chatzap.api.services.UserFriendService;

@CrossOrigin(origins = "http://localhost:4200/**")
@RestController
@RequestMapping("/api/users")
public class UsrFrndMngmtController {
	
	@Autowired
	public UserFriendService userFriendService;

	
	@PostMapping("/{user_id}/friends")
	public String SndFrndRqst(@PathVariable Long user_id, @RequestBody Long friend_id) {
		return userFriendService.sndfrndReq(user_id, friend_id);
	}
	
	@PostMapping("/{user_id}/friends/{friend_id}")
	public String AcptFrndRqst(@PathVariable Long user_id, @PathVariable Long friend_id) {
		return userFriendService.AcptfrndReq(user_id, friend_id);
	}
	@CrossOrigin(origins = "http://localhost:4200/**")
	@GetMapping("/{user_id}/friends")
	public List<UserModel> GetUserAllFrnd(@PathVariable Long user_id){
		return userFriendService.usrFrnfList(user_id,"accepted");		
	}
}
