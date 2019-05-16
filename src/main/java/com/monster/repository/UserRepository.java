package com.monster.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.monster.model.entity.User;

public interface UserRepository extends
			Serializable,
			JpaRepository<User, Integer>,
			JpaSpecificationExecutor<User>{
	
	

}
