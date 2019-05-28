package com.monster.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;


@Api(tags = "菜单接口！")
@Controller(value = "MenuController")
@RequestMapping("/auth")
public class MenuController {

    /**   
     * @Title: initIndex   
     * @Description: 刷新菜单
     * @param: @param map
     * @param: @return      
     * @return: String      
     * @throws   
     */
	@GetMapping("/refreshMenu")
	public String aaa() {
	    return "404";
	}	

}
