package com.monster.repository.car;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.monster.model.entity.car.Car;

public interface CarRepository extends
			Serializable,
			JpaRepository<Car, Integer>,
			JpaSpecificationExecutor<Car>{
	
	

}
