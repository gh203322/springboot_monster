package com.monster.repository.car;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.monster.model.entity.car.CarUser;

public interface CarUserRepository extends
			Serializable,
			JpaRepository<CarUser, Integer>,
			JpaSpecificationExecutor<CarUser>{
	
	

}
