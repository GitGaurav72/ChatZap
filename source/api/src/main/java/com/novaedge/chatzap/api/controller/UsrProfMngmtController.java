package com.novaedge.chatzap.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novaedge.chatzap.api.model.UserModel;
import com.novaedge.chatzap.api.entity.userEntity;
import com.novaedge.chatzap.api.services.UserService;

@RestController
@RequestMapping("/api/users/")
public class UsrProfMngmtController {
	
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{user_id}")
	public userEntity getUserById(@PathVariable Long user_id) {
		return userService.getUrById(user_id);
	}
	
	@PutMapping("/{user_id}")
	public userEntity updtUrById(@PathVariable Long user_id, @RequestBody UserModel usrModel ) {
		return userService.updtUsrById(usrModel);
	}
	
	@GetMapping("/detail/{user_id}")
	public UserModel getUserDetailById(@PathVariable Long user_id) {
		return userService.getUrDltById(user_id);
	}

	
}
