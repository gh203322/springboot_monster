package com.monster.controller.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	 * @Description: 数据新增页面
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	@GetMapping("/add")
    public String data(){
		
          return "car/add";
    }
	
	/**   
	 * @Title: list   
	 * @Description: 数据编辑页面
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	@GetMapping("/edit")
    public String edit(){
		
          return "car/edit";
    }
	
	/**   
	 * @Title: list   
	 * @Description: 删除数据
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	@PostMapping("/delete")
	@ResponseBody
    public String delete(@RequestBody List<Car> cars){
		
          return Result.ok(
        		  service.deleteBatchByEntitys(cars) 
          );
    }
	
	/**   
	 * @Title: list   
	 * @Description: 新增数据
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	@PostMapping("/addData")
	@ResponseBody
    public String addData(@ModelAttribute  Car car, MultipartFile file){
		
		  
          return Result.ok(
        		  service.saveOrUpdate(car)
          );
    }

}
