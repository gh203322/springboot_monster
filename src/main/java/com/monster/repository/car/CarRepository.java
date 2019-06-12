package com.monster.repository.car;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import com.monster.model.entity.car.Car;
import com.monster.repository.base.BaseRepository;

public interface CarRepository extends BaseRepository<Car>{
	
	@Query(
			nativeQuery = true,
			value = "select * from car"
	)
	List<Map<String, Object>> findAllToMap();
}
