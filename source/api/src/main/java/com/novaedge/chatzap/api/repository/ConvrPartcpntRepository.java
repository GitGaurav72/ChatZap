package com.novaedge.chatzap.api.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.novaedge.chatzap.api.entity.convrPrtcpntEntity;

@Repository
public interface ConvrPartcpntRepository extends JpaRepository<convrPrtcpntEntity, Long>{
	
	
	@Query(value = "SELECT cp1.conversation_id " +
            "FROM CT_CONVR_PRTCPNTENTITY cp1 " +
            "JOIN CT_CONVR_PRTCPNTENTITY cp2 ON cp1.conversation_id = cp2.conversation_id " +
            "WHERE cp1.user_id = :userId1 AND cp2.user_id = :userId2", nativeQuery = true)
	Long findConversatioIdByUserId (@Param("userId1")Long recieve, @Param("userId2")Long Sender);
	

}
