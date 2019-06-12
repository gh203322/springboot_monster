package com.monster.repository.system;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monster.model.entity.system.SysMenu;
import com.monster.repository.base.BaseRepository;

public interface SysMenuRepository extends BaseRepository<SysMenu> {
	
	  
		  /**   
		 * @Title: findBootNode   
		 * @Description: 查询根节点   
		 * @param: @param <SysMenu>      
		 * @return: void      
		 * @throws   
		 */
		 @Query(
				nativeQuery = true,
				value = "select m.*,(select count(*) from sys_menu u where u.parentId = m.id) as childNum,'1' as isBoot from sys_menu m where m.isShow = 1 AND m.parentId is NULL"
		  )
		  List<Map<String, Object>> findBootNode();
	 
	  /**   
		 * @Title: findBootNode   
		 * @Description: 根据parentId查询 
		 * @param: @param <SysMenu>      
		 * @return: List      
		 * @throws   
		 */
		 @Query(
				nativeQuery = true,
				value = "select m.*,(select count(*) from sys_menu u where u.parentId = m.id) as childNum,'0' as isBoot from sys_menu m where m.isShow = 1 AND m.parentId = :parentId"
		  )
		 List<Map<String, Object>> findByParentId(@Param(value = "parentId") Long parentId);
		 
		  /**   
		 * @Title: findBootNode   
		 * @Description: 查找子集个数
		 * @param: @param <SysMenu>      
		 * @return: Integer      
		 * @throws   
		 */
		 @Query(
				nativeQuery = true,
				value = "select count(*) from sys_menu where isShow = 1 AND parentId = :parentId"
		  )
		 Integer findChildNum(@Param("parentId") Long parentId);
		 
		  /**   
		 * @Title: findBootNode   
		 * @Description: 查询根节点   
		 * @param: @param <SysMenu>      
		 * @return: void      
		 * @throws   
		 */
		  List<SysMenu> findBySysMenuIdIsNull();
}
