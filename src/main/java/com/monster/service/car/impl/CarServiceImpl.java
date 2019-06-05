package com.monster.service.car.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monster.model.entity.car.Car;
import com.monster.repository.car.CarRepository;
import com.monster.service.car.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
    private CarRepository repository;
    
	@Override
	public Car save(Car entity) {
		
		return repository.save(entity);
	}

	@Override
	public Car saveOrUpdate(Car entity) {

		return repository.saveAndFlush(entity);
	}

	@Override
	public void saveAll(Iterable<Car> entities) {

		repository.saveAll(entities);
	}

	@Override
	public void delete(Car entity) {

		repository.delete(entity);
	}

	@Override
	public void deleteById(Integer id) {

		repository.deleteById(Integer.parseInt(String.valueOf(id)));
	}

	@Override
	public void deleteBatchByEntitys(Iterable<Car> entities) {
		
		repository.deleteInBatch(entities);
	}

	@Override
	public Car update(Car entity) {

		return repository.saveAndFlush(entity);
	}

	@Override
	public List<Car> findAll() {

		return repository.findAll();
	}

	@Override
	public Car findById(Integer id) {

		return repository.findById(id).get();
	}

	@Override
	public List<Car> findAllByExample(Example<Car> example) {

		return repository.findAll(example);
	}

	@Override
	public List<Car> findAllByExampleAndSort(Example<Car> example, Sort sort) {

		return repository.findAll(example,sort);
	}

	@Override
	public List<Map<String, Object>> findAllToMap() {
		
		return repository.findAllToMap();
	}

	@Override
	public Page<Car> findAllToPage(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

	
}
