package com.monster.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monster.base.Result;
import com.monster.service.system.SysMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "菜单接口！")
@Controller(value = "MenuController")
@RequestMapping("/auth")
public class MenuController {

	@Autowired
	private SysMenuService sysMenuService;
	
    /**   
     * @Title: initIndex   
     * @Description: 获取菜单树
     * @param: @param map
     * @param: @return      
     * @return: String      
     * @throws   
     */
	@ApiOperation(value = "查询系统菜单树！")
	@PostMapping("/getMenuTree")
	@ResponseBody
	public String getMenuTree() {
	    
		  return Result.to(
				  sysMenuService.getMenuTree()
		  );
	}	
	
	@ApiOperation(value = "查询系统菜单数据！")
	@PostMapping("/getMenuTreeLists")
	@ResponseBody
	public String getMenuTreeLists() {
	    
		  return Result.to(
				  sysMenuService.getMenuTreeLists()
		  );
	}

}
