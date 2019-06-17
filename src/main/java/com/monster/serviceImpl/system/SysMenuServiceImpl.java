package com.monster.serviceImpl.system;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monster.model.entity.system.SysMenu;
import com.monster.model.request.system.SysMenuSearch;
import com.monster.repository.system.SysMenuRepository;
import com.monster.service.system.SysMenuService;
import com.monster.utils.DataUtil;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	
	    @Autowired
	    private SysMenuRepository repository;
	    @PersistenceContext
	    private EntityManager em;
	    
		/**
		 * @throws InvocationTargetException 
		 * @throws IllegalAccessException    
		 * @Title: getMenuTree   
		 * @Description: 获取菜单树方法1
		 * @param: @return      
		 * @return: List<SysMenuDto>      
		 * @throws   
		 */
	     public List<SysMenuSearch> getMenuTree(){
	    	 
	    	    //查找根菜单
	    	    StringBuffer sql = new StringBuffer();
	    	    sql.append("select m.*,(select count(*) from sys_menu u where u.parentId = m.id) as childNum,'1' as isBoot from sys_menu m where m.isShow = 1 AND m.parentId is NULL order by m.sort asc");
	    	    List<Map<String, Object>> list = repository.nativeQueryListMap(em, sql.toString(), new Object[] {});
	    	    
	    	    List<SysMenuSearch> res = new ArrayList<SysMenuSearch>();
			    if(DataUtil.isNotEmptyObj(list)) { 
					 for(Map<String, Object> sysMenu: list) {
				           SysMenuSearch sysMenuDto = new SysMenuSearch(); 
				           try {
							   BeanUtils.copyProperties(sysMenuDto,sysMenu);
							   sysMenuDto.setChilds(getChilds(sysMenu));
							   
							   res.add(sysMenuDto); 
						   } catch (IllegalAccessException | InvocationTargetException e) {}
				     } 
				}
	    	    
	    	    return res;
	     }
	     
	     /**   
	     * @Title: getChilds   
	     * @Description: 找到子节点方法1 
	     * @param: @param sysMenu
	     * @param: @return      
	     * @return: List<SysMenuDto>      
	     * @throws   
	     */
	    private List<SysMenuSearch> getChilds(Map<String, Object> map){
	    	    if(DataUtil.isEmptyObj(map)) {
	    	    	return null;
	    	    }
	    	    
	    	    StringBuffer sql = new StringBuffer();
	    	    sql.append("select m.*,(select count(*) from sys_menu u where u.parentId = m.id) as childNum,'0' as isBoot from sys_menu m where m.isShow = 1 AND m.parentId = ?1 order by m.sort asc");
	    	    List<Map<String, Object>> list = repository.nativeQueryListMap(em, sql.toString(), new Object[] {Long.parseLong(map.get("id")+"")});
	    	    
			    if(DataUtil.isEmptyObj(list)) {
			    	return null;
			    }
			    
			    List<SysMenuSearch> res = new ArrayList<SysMenuSearch>();
			    for(Map<String, Object> child: list) {
			    	SysMenuSearch sysMenuDto = new SysMenuSearch();
			    	try {
			    		BeanUtils.copyProperties(sysMenuDto,child);
			    		sysMenuDto.setChilds(getChilds(child));
				    	
				    	res.add(sysMenuDto);
					} catch (IllegalAccessException | InvocationTargetException e) {}
			    }
			    
			  return res;
	     }

	    /**   
		 * @Title: getMenuTree   
		 * @Description: 获取菜单树方法2
		 * @param: @return      
		 * @return: List<SysMenuDto>      
		 * @throws   
		 */
		@Override
		public List<SysMenuSearch> getMenuTreeByStream() {
			
			List<SysMenu> list = repository.findBySysMenuIdIsNull();
			List<SysMenuSearch> res = new ArrayList<SysMenuSearch>();
		    if(DataUtil.isNotEmptyObj(list)) { 
				 for(SysMenu sysMenu: list) {
			           try {
			        	   if(new Integer("1").equals(sysMenu.getIsShow())) {
			        		   SysMenuSearch sysMenuDto = new SysMenuSearch();
							   BeanUtils.copyProperties(sysMenuDto,sysMenu);
							   sysMenuDto.setIsBoot(1);
							   if(DataUtil.isNotEmptyObj(sysMenu.getSysMenus())) {
								   List<SysMenuSearch> childsDtos = getChilds(sysMenu.getSysMenus());
								   sysMenuDto.setChildNum(childsDtos.size());
								   sysMenuDto.setChilds(childsDtos);
							   }else {
								   sysMenuDto.setChildNum(0);
							   }
							   
							   res.add(sysMenuDto); 
			        	   }
					   } catch (IllegalAccessException | InvocationTargetException e) {}
			     } 
			}
    	    
    	    return res;
		}
		

	    /**   
	     * @Title: getChilds   
	     * @Description: 找到子节点方法2
	     * @param: @param sysMenu      
	     * @return: void      
	     * @throws   
	     */
		public List<SysMenuSearch> getChilds(List<SysMenu> list){
			if(DataUtil.isEmptyObj(list)) {
    	    	return null;
    	    }
	    	
	    	List<SysMenuSearch> res = new ArrayList<SysMenuSearch>();
	    	for(SysMenu sysMenu: list) {
				   try {
					   if(new Integer("1").equals(sysMenu.getIsShow())) {
		        		   SysMenuSearch sysMenuDto = new SysMenuSearch();
					       BeanUtils.copyProperties(sysMenuDto,sysMenu);
					       sysMenuDto.setIsBoot(0);
						   if(DataUtil.isNotEmptyObj(sysMenu.getSysMenus())) {
							   List<SysMenuSearch> childsDtos = getChilds(sysMenu.getSysMenus());
							   sysMenuDto.setChildNum(childsDtos.size());
							   sysMenuDto.setChilds(childsDtos);
						   }else {
							   sysMenuDto.setChildNum(0);
						   }
						   
						   res.add(sysMenuDto); 
		        	    }
					} catch (IllegalAccessException | InvocationTargetException e) {}
		     } 
		    
		  return res;
	    }

		/**
		 * @throws InvocationTargetException 
		 * @throws IllegalAccessException    
		 * @Title: getMenuTree   
		 * @Description: 获取菜单树方法3
		 * @param: @return      
		 * @return: List<SysMenuDto>      
		 * @throws   
		 */
		@Override
		public List<SysMenuSearch> getMenuTreeByQuery() {
			 //查找根菜单
    	    List<Map<String, Object>> list = repository.findBootNode();
    	    
    	    List<SysMenuSearch> res = new ArrayList<SysMenuSearch>();
		    if(DataUtil.isNotEmptyObj(list)) { 
				 for(Map<String, Object> sysMenu: list) {
			           SysMenuSearch sysMenuDto = new SysMenuSearch(); 
			           try {
						   BeanUtils.copyProperties(sysMenuDto,sysMenu);
						   sysMenuDto.setChilds(getChilds3(sysMenu));
						   
						   res.add(sysMenuDto); 
					   } catch (IllegalAccessException | InvocationTargetException e) {}
			     } 
			}
    	    
    	    return res;
		}
		
		 /**   
	     * @Title: getChilds   
	     * @Description: 找到子节点方法3
	     * @param: @param sysMenu
	     * @param: @return      
	     * @return: List<SysMenuDto>      
	     * @throws   
	     */
	    private List<SysMenuSearch> getChilds3(Map<String, Object> map){
	    	    if(DataUtil.isEmptyObj(map)) {
	    	    	return null;
	    	    }
	    	    
	    	    List<Map<String, Object>> list = repository.findByParentId(Long.parseLong(String.valueOf(map.get("id"))));
	    	    
			    if(DataUtil.isEmptyObj(list)) {
			    	return null;
			    }
			    
			    List<SysMenuSearch> res = new ArrayList<SysMenuSearch>();
			    for(Map<String, Object> child: list) {
			    	SysMenuSearch sysMenuDto = new SysMenuSearch();
			    	try {
			    		BeanUtils.copyProperties(sysMenuDto,child);
			    		sysMenuDto.setChilds(getChilds(child));
				    	
				    	res.add(sysMenuDto);
					} catch (IllegalAccessException | InvocationTargetException e) {}
			    }
			    
			  return res;
	     }
}
