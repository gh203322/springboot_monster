package com.monster.base;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.monster.utils.DataUtil;

import lombok.Data;

/**
 * @author Administrator
 *  自定义分页排序类
 */
@Data
public class Ipage{
	
	private String sortName;
	private String sortOrder;
	private Integer size;
	private Integer page;
	private String[] sortNames;
	private Object params;
	
	/**   
	 * @Title: of   
	 * @Description: 获取 pageable 方法 
	 * @param: @return      
	 * @return: Pageable      
	 * @throws   
	 */
	public Pageable of() {
		
		 if(DataUtil.isNotEmptyObj(sortNames) && DataUtil.isNotEmptyObj(sortOrder)) {
			 if("asc".equals(sortOrder.toLowerCase())) {
				 return PageRequest.of(page, size, Direction.ASC, sortNames);
			 }else {
				 return PageRequest.of(page, size, Direction.DESC, sortNames);
			 }
		 }else if(DataUtil.isNotEmptyObj(sortName) && DataUtil.isNotEmptyObj(sortOrder)) {
			 if("asc".equals(sortOrder.toLowerCase())) {
				 return PageRequest.of(page, size, Direction.ASC, sortName);
			 }else {
				 return PageRequest.of(page, size, Direction.DESC, sortName);
			 }
		 }else {
			 return PageRequest.of(page, size);
		 }
	}
	
	/**   
	 * @Title: of   
	 * @Description: 传参方法
	 * @param: @param params
	 * @param: @return      
	 * @return: Page      
	 * @throws   
	 */
	public Ipage of(Object params) {
		
		 if(DataUtil.isNotEmptyObj(params)) {
			 this.params = params;
		 }
		 return this;
	}
	
}
