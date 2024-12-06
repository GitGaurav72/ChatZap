package com.novaedge.chatzap.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.novaedge.chatzap.api.entity.userEntity;
import com.novaedge.chatzap.api.model.UserModel;
import com.novaedge.chatzap.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public userEntity addUserEntiry(userEntity user) {
		userEntity userObj = new userEntity();
		userObj.setFirstname(user.getFirstname());
		userObj.setLastname(user.getLastname());
		userObj.setUsername(user.getUsername());
		userObj.setEmail(user.getEmail());
		userObj.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(userObj);
	}

	public userEntity getUrById(Long user_id) {
		userEntity userObj = new userEntity();
		userObj = repository.getById(user_id);
		return userObj;
	}
	
	public userEntity getUsrByUsrNm(String usr_nm) {
		userEntity userObj = new userEntity();
		userObj = repository.getUserByUsrName(usr_nm);
		return userObj;
	}

	public userEntity updtUsrById(UserModel usrModel) {
		userEntity userObj = new userEntity();
		userObj = repository.getUserByUsrName(usrModel.getUsername());
		userObj.setFirstname(usrModel.getFirstname());
		userObj.setLastname(usrModel.getLastname());
		userObj.setEmail(usrModel.getEmail());
		userObj.setProfilePicture(usrModel.getProfilePicture());
		userObj.setStatus(usrModel.getStatus());
		
		return repository.save(userObj);
	}
	
	public List<userEntity> getallUser(){
		return repository.findAll();
	}
	
	public UserModel getUrDltById (Long usrId) {
		userEntity userObj = repository.getById(usrId);
		UserModel usrMdl = new UserModel(); 
		usrMdl.setId(userObj.getId());
		usrMdl.setFirstname(userObj.getFirstname());
		usrMdl.setLastname(userObj.getLastname());
		usrMdl.setUsername(userObj.getUsername());
		usrMdl.setEmail(userObj.getEmail());
		usrMdl.setProfilePicture(userObj.getProfilePicture());
		usrMdl.setStatus(userObj.getStatus());
		
		return usrMdl;
		
		
	}
	
}
