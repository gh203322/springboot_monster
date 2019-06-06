package com.monster.controller.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monster.base.Request;
import com.monster.base.Result;
import com.monster.model.entity.car.Car;
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
	 * @Description: 数据列表页面  
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	@PostMapping("/view")
    public String list(){
		
          return "car/view";
    }
	
	@ApiOperation(value = "获取车辆列表数据")
	@PostMapping("/list")
	@ResponseBody
	public String getMenuTreeByQuery(Pageable pageable) {
	    
		  return Result.ok(
				  service.findAllToPage(pageable)
		  );
	}
	
	/**   
	 * @Title: list   
	 * @Description: 数据新增或者修改页面
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	@PostMapping("/data")
    public String data(){
		
          return "car/data";
    }
	
	/**   
	 * @Title: list   
	 * @Description: 删除数据
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	@PostMapping("/delete")
    public String delete(Request request){
		
          return Result.ok(
        		  service.deleteBatchByEntitys(null) 
          );
    }

}
