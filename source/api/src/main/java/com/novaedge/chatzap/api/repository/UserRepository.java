package com.novaedge.chatzap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novaedge.chatzap.api.entity.userEntity;


@Repository
public interface UserRepository extends JpaRepository<userEntity, Long>{

	userEntity findByUsername(String username);

    
    @Query(value = "SELECT * FROM CT_USER WHERE USER_NAME = :username", nativeQuery = true)
    userEntity getUserByUsrName(@Param("username") String username);




	
}
