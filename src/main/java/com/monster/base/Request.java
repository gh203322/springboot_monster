package com.monster.base;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.monster.model.entity.car.Car;

import lombok.Data;

/**
 * @author Administrator
 *  通用请求类
 */
@Data
public class Request<T> {

	  private List<T> idObjects = new ArrayList<T>();
	  
	  //前端通过json传值,批量传递主键 key 必须与之对应为 ids
	  private List<Integer> ids = new ArrayList<Integer>();

	/*
	 * public List<T> getIdObjects() {
	 * 
	 * if(null != ids && !ids.isEmpty()) { ids.stream().forEach(id ->
	 * idObjects.add(T)); return this.idObjects; } return null; }
	 */
	  
}
