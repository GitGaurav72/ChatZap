package com.novaedge.chatzap.api.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.novaedge.chatzap.api.entity.conversationEntity;
import com.novaedge.chatzap.api.entity.convrPrtcpntEntity;

@Repository
public interface ConvrPartcpntRepository extends JpaRepository<convrPrtcpntEntity, Long>{
	
	
	@Query(value = "SELECT DISTINCT cp1.conversation_id " +
            "FROM CT_CONVR_PRTCPNTENTITY cp1 " +
            "JOIN CT_CONVR_PRTCPNTENTITY cp2 ON cp1.conversation_id = cp2.conversation_id " +
            "WHERE cp1.user_id = :userId1 AND cp2.user_id = :userId2 AND cp1.id != cp2.id", nativeQuery = true)
	Long findConversatioIdByUserId (@Param("userId1")Long recieve, @Param("userId2")Long Sender);
	
	

    @Query(value = "    SELECT * \n"
    		+ "    FROM CT_CONVR_PRTCPNTENTITY cp1 \n"
    		+ "    JOIN CT_CONVR_PRTCPNTENTITY cp2 ON cp1.conversation_id = cp2.conversation_id \n"
    		+ "    WHERE cp1.user_id = :userId1 AND cp2.user_id = :userId2 AND cp1.id != cp2.id", nativeQuery = true)
	convrPrtcpntEntity findByUserId(@Param("userId1")Long recieve, @Param("userId2")Long Sender);
    
    
    List<convrPrtcpntEntity> findByConversation (conversationEntity conversation);
}
