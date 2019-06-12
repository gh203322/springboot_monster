package com.monster.utils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Administrator jpa 更新工具类
 */
public class UpdateUtil {

	/**   
	 * @Title: copyNullProperties   
	 * @Description: 复制对象属性值，忽略空字段
	 * @param: @param source
	 * @param: @param target
	 * @param: @param 指定不需要复制属性值的字段      
	 * @return: void      
	 * @throws   
	 */
	public static void copyNullProperties(Object source, Object target, String...params) {
		List<String> fields = getNullField(source);
		for(String field: params) {
			fields.add(field);
		}
		String[] IgnoreFields = new String[fields.size()];
		fields.toArray(IgnoreFields);
		
		BeanUtils.copyProperties(source, target, IgnoreFields);
	}
	
	/**   
	 * @Title: copyNullPropertiesIgnoe   
	 * @Description: 复制对象属性值，忽略空字段
	 * @param: @param source
	 * @param: @param target
	 * @param: @param 指定为空依旧需要复制属性值的字段      
	 * @return: void      
	 * @throws   
	 */
	public static void copyNullPropertiesIgnore(Object source, Object target, String...params) {
		
		List<String> fields = getNullField(source);
		for(String field: params) {
			if(fields.contains(field)) {
				fields.remove(field);
			}
		}
		String[] IgnoreFields = new String[fields.size()];
		fields.toArray(IgnoreFields);
		
		BeanUtils.copyProperties(source, target, IgnoreFields);
	}
	
	
	/**   
	 * @Title: getNullField   
	 * @Description: TODO(方法作用)   
	 * @param: @param target
	 * @param: @return      
	 * @return: list  
	 * @throws   
	 */
	private static List<String> getNullField(Object target) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(target);
		PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
		List<String> notNullFieldList = new ArrayList<String>();
		if (propertyDescriptors.length > 0) {
			for (PropertyDescriptor p : propertyDescriptors) {
				String name = p.getName();
				Object value = beanWrapper.getPropertyValue(name);
				if (Objects.isNull(value)) {
					notNullFieldList.add(name);
				}
			}
		}
		
		return notNullFieldList;
	}
}
