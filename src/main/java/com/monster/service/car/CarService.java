package com.monster.service.car;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.monster.model.entity.car.Car;
import com.monster.model.request.car.CarSearch;
import com.monster.service.base.baseService;

public interface CarService extends baseService<Car>{

	 List<Map<String, Object>> findAllToMap();
	 
	 Page<Car> findAllToPage(Pageable pageable);
	 
	 Page<Car> findAllToPage(Pageable pageable, CarSearch carSearch) throws Exception;
}
