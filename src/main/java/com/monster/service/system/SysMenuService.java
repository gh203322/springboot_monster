package com.monster.service.system;

import java.util.List;

import com.monster.model.request.system.SysMenuSearch;

public interface SysMenuService{

	/**   
	 * @Title: getMenuTree   
	 * @Description: 获取菜单树
	 * @param: @return      
	 * @return: List<SysMenuDto>      
	 * @throws   
	 */
	List<SysMenuSearch> getMenuTree();
	
	/**   
	 * @Title: getMenuTreeByStream   
	 * @Description: 获取菜单数据
	 * @param: @return      
	 * @return: List<SysMenuDto>      
	 * @throws   
	 */
	List<SysMenuSearch> getMenuTreeByStream();
	
	/**   
	 * @Title: getMenuTreeByQuery   
	 * @Description: 获取菜单数据
	 * @param: @return      
	 * @return: List<SysMenuDto>      
	 * @throws   
	 */
	List<SysMenuSearch> getMenuTreeByQuery();
}
