package com.monster.controller.car;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monster.base.Result;
import com.monster.service.car.CarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "车辆管理接口！")
@Controller(value = "CarController")
@RequestMapping("/auth/car")
public class CarController {

	@Autowired
	private CarService service;
	
 
	/**   
	 * @Title: list   
	 * @Description: 车辆主页  
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	@PostMapping("/list")
    public String list(){
        return "car/list";
    }
	
	@ApiOperation(value = "获取车辆列表数据")
	@PostMapping("/listData")
	@ResponseBody
	public String getMenuTreeByQuery(Pageable pageable) {
	    
		  return Result.ok(
				  service.findAllToPage(pageable)
		  );
	}

}
