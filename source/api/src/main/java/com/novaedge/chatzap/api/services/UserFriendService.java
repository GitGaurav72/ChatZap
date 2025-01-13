package com.novaedge.chatzap.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.novaedge.chatzap.api.entity.UserFriendsId;
import com.novaedge.chatzap.api.entity.convrPrtcpntEntity;
import com.novaedge.chatzap.api.entity.userEntity;
import com.novaedge.chatzap.api.entity.userFriendsEntity;
import com.novaedge.chatzap.api.model.UserModel;
import com.novaedge.chatzap.api.model.UserModelMsg;
import com.novaedge.chatzap.api.repository.ConvrPartcpntRepository;
import com.novaedge.chatzap.api.repository.UserFriendRepository;
import com.novaedge.chatzap.api.repository.UserRepository;


@Service
public class UserFriendService {
	
	@Autowired
	private UserFriendRepository userFriendRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConvrPartcpntRepository convrPartcpntRepository;

	public String sndfrndReq(Long user_id, Long friend_id) {
		userFriendsEntity userFriendEntity = new userFriendsEntity();
		UserFriendsId userFriendsId = new UserFriendsId();
		userFriendsId.setUserId(user_id);
		userFriendsId.setFriendId(friend_id);
		userFriendEntity.setUsrFrndId(userFriendsId);
		userFriendEntity.setStatus("requested");
		userFriendRepository.save(userFriendEntity);
		return "Friend request sent";
	}

	public String AcptfrndReq(Long user_id, Long friend_id) {
		userFriendsEntity userFriendEntity = userFriendRepository.findByUsrFrndId_UserIdAndUsrFrndId_FriendIdAndStatus(user_id,friend_id,"requested");
		userFriendEntity.setStatus("accepted");
		userFriendRepository.save(userFriendEntity);
		userFriendsEntity userFriendEntity1 = new userFriendsEntity();
		UserFriendsId userFriendsId1 = new UserFriendsId();
		userFriendsId1.setFriendId(friend_id);
		userFriendsId1.setUserId(user_id);
		userFriendEntity1.setUsrFrndId(userFriendsId1);
		userFriendEntity1.setStatus("accepted");
		userFriendRepository.save(userFriendEntity1);
		return "Friend request accepted";
	}
	

	public List<UserModelMsg> usrFrnfList(Long user_id, String status ){
		
		
		List<Long> userFriendsEntityList = userFriendRepository.findUserFriendsByStatusAndUserId(user_id, status);
		List<UserModelMsg> result = new ArrayList<>();
		for(Long userFriendEntity : userFriendsEntityList) {
			Optional<userEntity> usrEnty = userRepository.findById(userFriendEntity);
			UserModelMsg usrModel = new UserModelMsg();
			usrModel.setId(usrEnty.get().getId());
			usrModel.setFirstname(usrEnty.get().getFirstname());
			usrModel.setLastname(usrEnty.get().getLastname());
			usrModel.setUsername(usrEnty.get().getUsername());
			usrModel.setStatus(usrEnty.get().getStatus());
			usrModel.setProfilePicture(usrEnty.get().getProfilePicture());
			usrModel.setEmail(usrEnty.get().getEmail());
			convrPrtcpntEntity convrPrtcpntEty = convrPartcpntRepository.findByUserId(user_id, usrEnty.get().getId());
			if(!StringUtils.isEmpty(convrPrtcpntEty)) {
			usrModel.setLstMsg(convrPrtcpntEty.getLstMsg());
			usrModel.setLstMsgTm(convrPrtcpntEty.getLstMsgTm());
			}
			result.add(usrModel);
		}
		
		return result;
	
	}

	public List<UserModel> usrNewFrnfList(Long user_id) {
		
		List<userEntity> userNewFriendsList = userFriendRepository.findNewFriendsByUserId(user_id);
		List<UserModel> result = new ArrayList<>();
		for(userEntity userFriendEntity : userNewFriendsList) {
			UserModel usrModel = new UserModel();
			
			usrModel.setId(userFriendEntity.getId());
			usrModel.setFirstname(userFriendEntity.getFirstname());
			usrModel.setLastname(userFriendEntity.getLastname());
			usrModel.setUsername(userFriendEntity.getUsername());
			usrModel.setStatus(userFriendEntity.getStatus());
			usrModel.setProfilePicture(userFriendEntity.getProfilePicture());
			usrModel.setEmail(userFriendEntity.getEmail());
			
			result.add(usrModel);
		}
		
		return result;
	}

	public List<UserModel> reqFrndList(Long friendId, String status) {
		List<userFriendsEntity> userFriendsEntityList = userFriendRepository.findUsrFrndId_UserIdByUsrFrndId_FriendIdAndStatus(friendId, status);
		
		List<UserModel> result = new ArrayList<>();
		for(userFriendsEntity userFriendEntity : userFriendsEntityList) {
			userEntity userEnty = userRepository.getById(userFriendEntity.getUsrFrndId().getUserId());
			UserModel usrModel = new UserModel();
			
			usrModel.setId(userEnty.getId());
			usrModel.setFirstname(userEnty.getFirstname());
			usrModel.setLastname(userEnty.getLastname());
			usrModel.setUsername(userEnty.getUsername());
			usrModel.setStatus(userEnty.getStatus());
			usrModel.setProfilePicture(userEnty.getProfilePicture());
			usrModel.setEmail(userEnty.getEmail());
			
			result.add(usrModel);
		}
		
		return result;
	}
	
}
