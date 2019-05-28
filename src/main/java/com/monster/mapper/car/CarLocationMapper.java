package com.monster.mapper.car;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.monster.model.entityVo.car.CarLocationVo;

@Component
public interface CarLocationMapper {

	/**   
	 * @Title: selectByPrimaryKeyToMap   
	 * @Description: 通过主键查询到map
	 * @param: @param id
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws   
	 */
	Map<String, Object> selectByPrimaryKeyToMap(Integer id);
	
	/**   
	 * @Title: selectAllToMap   
	 * @Description: 查询所有到map
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws   
	 */
	Map<String, Object> selectAllToMap();
	
	/**   
	 * @Title: selectAll   
	 * @Description: 查询所有到实体vo
	 * @param: @return      
	 * @return: List<CarLocationVo>      
	 * @throws   
	 */
	List<CarLocationVo> selectAll();
}