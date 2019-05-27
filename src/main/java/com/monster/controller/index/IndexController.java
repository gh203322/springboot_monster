package com.monster.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


@Api(tags = "主页测试接口！")
@Controller
@RequestMapping("/auth")
public class IndexController {


    /**   
     * @Title: initIndex   
     * @Description: 打开主页  
     * @param: @param map
     * @param: @return      
     * @return: String      
     * @throws   
     */
	@RequestMapping("/index")
    public String index(){
        return "index";
    }
	
    /**   
     * @Title: initIndex   
     * @Description: 初始化主页
     * @param: @param map
     * @param: @return      
     * @return: String      
     * @throws   
     */
	@ApiOperation(value = "查询初始化数据！")
    @ApiImplicitParam(name="map",value="模板引擎传参对象，无需传值！")
    @GetMapping("/initIndex")
    public String initIndex(ModelMap map){
        map.addAttribute("name","tcc");
        map.addAttribute("number","9527");
        return "backup_index";
    }
}
