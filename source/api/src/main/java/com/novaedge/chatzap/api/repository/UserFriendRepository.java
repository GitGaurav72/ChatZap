package com.novaedge.chatzap.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.novaedge.chatzap.api.entity.UserFriendsId;
import com.novaedge.chatzap.api.entity.userFriendsEntity;

@Repository
public interface UserFriendRepository extends JpaRepository<userFriendsEntity, UserFriendsId> {

//	// Method to get the list of UserModels that are friends of the given user ID
//	@Query("SELECT new com.novaedge.chatzap.api.Model.UserModel(u.id, u.firstname, u.lastname, u.username, u.email, u.profilePicture, u.status) " +
//		       "FROM userEntity u " +
//		       "LEFT JOIN userFriendsEntity uf ON u.id = uf.friendId " +
//		       "WHERE uf.userId = :userId AND uf.status = :status")
//		List<UserModel> findFriendsByUserId(@Param("userId") Long userId, @Param("status") String status);

	
	
//	@Query(value = "SELECT u.id, u.email, u.first_name, u.last_name, u.status, u.user_name, u.profile_picture " +
//            "FROM CT_USER u " +
//            "LEFT OUTER JOIN ct_user_friends uf " +
//            "ON u.id = uf.friend_id " +
//            "WHERE uf.user_id = :userId AND uf.status = :status", nativeQuery = true)
//     List<UserModel> findFriendsByUserId(@Param("userId") Long userId, @Param("status") String status);


	
	
//	@Query(value = "update ct_user_friends set ct_user_friends.status = 'accepted'\r\n"
//			+ "where ct_user_friends.user_id = :user_id and ct_user_friends.friend_id =:friend_id", nativeQuery = true)
//	void addFriend(@Param("user_id")Long user_id, @Param("friend_id")Long friend_id);
	
	
	@Query("SELECT uf.id.friendId FROM userFriendsEntity uf WHERE uf.status = :status AND uf.id.userId = :userId")
	List<Long> findUserFriendsByStatusAndUserId(@Param("userId") Long userId, @Param("status") String status);

//	List<userFriendsEntity> findByIdUserIdAndStatus(Long userId, String status);

}

