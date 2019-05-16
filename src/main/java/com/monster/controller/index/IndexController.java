package com.monster.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IndexController {
    @ResponseBody
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() {
        return "Hello World!";
    }


    /**   
     * @Title: index   
     * @Description: TODO(方法作用)   
     * @param: @param map
     * @param: @return      
     * @return: String      
     * @throws   
     */
    @RequestMapping("/")
    public String index(ModelMap map){
        map.addAttribute("name","tcc");
        map.addAttribute("number","9527");
        return "index";
    }
}
