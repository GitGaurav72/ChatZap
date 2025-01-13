package com.novaedge.chatzap.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.novaedge.chatzap.api.entity.UserFriendsId;
import com.novaedge.chatzap.api.entity.userEntity;
import com.novaedge.chatzap.api.entity.userFriendsEntity;

@Repository
public interface UserFriendRepository extends JpaRepository<userFriendsEntity, Long> {
	@Query("SELECT uf.usrFrndId.friendId FROM userFriendsEntity uf WHERE uf.status = :status AND uf.usrFrndId.userId = :userId")
	List<Long> findUserFriendsByStatusAndUserId(@Param("userId") Long userId, @Param("status") String status);
	
	@Query("SELECT u FROM userEntity u WHERE u.id NOT IN ( SELECT uf.usrFrndId.friendId FROM userFriendsEntity uf WHERE uf.usrFrndId.userId = :userId ) AND u.id != :userId")
	List<userEntity> findNewFriendsByUserId(@Param("userId") Long userId);
		
	userFriendsEntity findByUsrFrndId_UserIdAndUsrFrndId_FriendIdAndStatus(Long userId, Long friendId, String status);

	List<userFriendsEntity> findUsrFrndId_UserIdByUsrFrndId_FriendIdAndStatus(Long friendId, String status);
	
}

