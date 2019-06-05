package com.monster.repository.car;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.monster.model.entity.car.Car;

public interface CarRepository extends
			Serializable,
			JpaRepository<Car, Integer>,
			JpaSpecificationExecutor<Car>{
	
	@Query(
			nativeQuery = true,
			value = "select * from car"
	)
	List<Map<String, Object>> findAllToMap();
}
