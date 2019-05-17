package com.monster.controller.index;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


@Api(tags = "主页测试接口！")
@RestController
@RequestMapping("/auth/index")
public class IndexController {


    /**   
     * @Title: initIndex   
     * @Description: TODO(方法作用)   
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
        return "index";
    }
}
