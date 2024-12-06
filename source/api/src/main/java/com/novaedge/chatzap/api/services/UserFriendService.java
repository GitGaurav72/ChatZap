package com.novaedge.chatzap.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novaedge.chatzap.api.entity.UserFriendsId;
import com.novaedge.chatzap.api.entity.userEntity;
import com.novaedge.chatzap.api.entity.userFriendsEntity;
import com.novaedge.chatzap.api.model.UserModel;
import com.novaedge.chatzap.api.repository.UserFriendRepository;
import com.novaedge.chatzap.api.repository.UserRepository;

@Service
public class UserFriendService {
	
	@Autowired
	private UserFriendRepository userFriendRepository;
	
	@Autowired
	private UserRepository userRepository;

	public String sndfrndReq(Long user_id, Long friend_id) {
		userFriendsEntity ufe = new userFriendsEntity();
		UserFriendsId userFriendsId = new UserFriendsId();
		userFriendsId.setUserId(user_id);
		userFriendsId.setFriendId(friend_id);
		ufe.setId(userFriendsId);
		ufe.setStatus("pending");
		userFriendRepository.save(ufe);
		return "Friend request sent";
	}

	public String AcptfrndReq(Long user_id, Long friend_id) {
//		userFriendRepository.addFriend(user_id,friend_id);
		return "Friend request accepted";
	}
	
	
	public List<UserModel> usrFrnfList(Long user_id, String status ){
		
		
		List<Long> userFriendsEntityList = userFriendRepository.findUserFriendsByStatusAndUserId(user_id, status);
		List<UserModel> result = new ArrayList<>();
		for(Long userFriendEntity : userFriendsEntityList) {
			Optional<userEntity> usrEnty = userRepository.findById(userFriendEntity);
			UserModel usrModel = new UserModel();
			
			usrModel.setId(usrEnty.get().getId());
			usrModel.setFirstname(usrEnty.get().getFirstname());
			usrModel.setLastname(usrEnty.get().getLastname());
			usrModel.setUsername(usrEnty.get().getUsername());
			usrModel.setStatus(usrEnty.get().getStatus());
			usrModel.setProfilePicture(usrEnty.get().getProfilePicture());
			usrModel.setEmail(usrEnty.get().getEmail());
			
			result.add(usrModel);
		}
		
		return result;
	
	}
	
	
//	@Transactional
//	public List<UserModel> usrAllFrnd(Long user_id, String status) {
//		List<UserModel> list = userFriendRepository.findByIdUserIdAndStatus(user_id,status);
//	      
//		
////		List<UserModel>  results = new ArrayList<>();
////		for(Object[] obj : list) {
////			UserModel model = new UserModel();
////			model.setId(((BigDecimal) obj[0]).longValue());  // Convert BigDecimal to Long
////			model.setEmail((String) obj[1]);
////			model.setFirstname((String) obj[2]);
////			model.setLastname((String) obj[3]);
////			model.setStatus((String) obj[4]);
////			model.setUsername((String) obj[5]);
////			model.setProfilePicture((byte[]) obj[6]);
////			
////			
////			results.add(model);
////		}
////		
//		  return list;
//	     }
	
}
