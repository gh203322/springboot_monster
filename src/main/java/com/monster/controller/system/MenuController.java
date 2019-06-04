package com.monster.controller.system;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monster.base.Result;
import com.monster.service.system.SysMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "菜单接口！")
@Controller(value = "MenuController")
@RequestMapping("/auth/menu")
@Validated
public class MenuController {

	@Autowired
	private SysMenuService sysMenuService;
	
	/**   
	 * @Title: openContent   
	 * @Description: 从菜单打开对应页面
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	@PostMapping("/openContent")
    public String openContent(
    		@NotEmpty(message = "href 不能为空")@RequestParam(name = "href") String href){
		
        return href;
    }
	
    /**   
     * @Title: initIndex   
     * @Description: 获取菜单树
     * @param: @param map
     * @param: @return      
     * @return: String      
     * @throws   
     */
	@ApiOperation(value = "获取菜单树方法1！")
	@PostMapping("/getMenuTree")
	@ResponseBody
	public String getMenuTree() {
	    
		  return Result.ok(
				  sysMenuService.getMenuTree()
		  );
	}	
	
	@ApiOperation(value = "获取菜单树方法2！")
	@PostMapping("/getMenuTreeByStream")
	@ResponseBody
	public String getMenuTreeByStream() {
	    
		  return Result.ok(
				  sysMenuService.getMenuTreeByStream()
		  );
	}
	
	@ApiOperation(value = "获取菜单树方法3！")
	@PostMapping("/getMenuTreeByQuery")
	@ResponseBody
	public String getMenuTreeByQuery() {
	    
		  return Result.ok(
				  sysMenuService.getMenuTreeByQuery()
		  );
	}

}
