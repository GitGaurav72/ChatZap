package com.novaedge.chatzap.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novaedge.chatzap.api.model.UserModel;
import com.novaedge.chatzap.api.model.UserModelMsg;
import com.novaedge.chatzap.api.services.UserFriendService;

@CrossOrigin(origins = "http://localhost:4200/**")
@RestController
@RequestMapping("/api/users")
public class UsrFrndMngmtController {
	
	@Autowired
	public UserFriendService userFriendService;

	
	@PostMapping("/{user_id}/sendReq/{friend_id}")
	public String SndFrndRqst(@PathVariable Long user_id, @PathVariable Long friend_id) {
		return userFriendService.sndfrndReq(user_id, friend_id);
	}
	
	@PostMapping("/{user_id}/accpReq/{friend_id}")
	public String AcptFrndRqst(@PathVariable Long user_id, @PathVariable Long friend_id) {
		return userFriendService.AcptfrndReq(user_id, friend_id);
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200/**")
	@GetMapping("/rcvreq")
	public List<UserModel> getAllReqFriends(
	        @RequestParam("userId") Long userId, 
	        @RequestParam(value = "sts", required = false) String status) {
	    return userFriendService.reqFrndList(userId, status);
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200/**")
	@GetMapping("/friends")
	public List<UserModelMsg> getUserAllFriends(
	        @RequestParam("userId") Long userId, 
	        @RequestParam(value = "sts", required = false) String status) {
	    return userFriendService.usrFrnfList(userId, status);
	}
	
	@GetMapping("/{user_id}/new-friends")
	public List<UserModel> GetAllNewFrnd(@PathVariable Long user_id){
		return userFriendService.usrNewFrnfList(user_id);		
	}
}
