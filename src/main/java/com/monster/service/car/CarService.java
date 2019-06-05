package com.monster.service.car;

import java.util.List;
import java.util.Map;

import com.monster.model.entity.car.Car;
import com.monster.service.base.baseService;

public interface CarService extends baseService<Car>{

	 List<Map<String, Object>> findAllToMap();
}
