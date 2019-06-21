package com.monster.controller.car;

import com.monster.base.reqAndRsp.Ipage;
import com.monster.base.reqAndRsp.Result;
import com.monster.controller.base.BaseController;
import com.monster.model.entity.car.Car;
import com.monster.model.request.car.CarSearch;
import com.monster.service.car.CarService;
import com.monster.service.car.CarUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Api(tags = "车辆管理接口！")
@Controller(value = "CarController")
@RequestMapping("/auth/car")
@Validated
public class CarController extends BaseController {

	@Autowired
	private CarService service;
	@Autowired
	private CarUserService carUserService;
	
 
	@ApiOperation(value = "加载车辆主页面")
	@PostMapping("/view")
    public String view(){

          return "car/view";
    }
	
	@ApiOperation(value = "获取车辆列表")
	@PostMapping("/list")
	@ResponseBody
	public String list(Pageable pageable) {
	    
		  return Result.ok(
				  service.findAllToPage(pageable)
		  );
	}
	
	@ApiOperation(value = "加载新增页面")
	@GetMapping("/addView")
    public String addView(){

          return "car/add";
    }
	
	@ApiOperation(value = "加载编辑页面")
	@GetMapping("/editView")
    public String editView(@NotNull(message = "id不能为空")@RequestParam(name = "id") Long id, Model model){
		
		  model.addAttribute("car", service.findById(id));
		  
          return "car/edit";
    }
	
	@ApiOperation(value = "删除车辆数据")
	@PostMapping("/delete")
	@ResponseBody
    public String delete(@RequestBody List<Car> cars){
		
          return Result.ok(
        		  service.deleteBatchByEntitys(cars) 
          );
    }
	
	@ApiOperation(value = "新增车辆数据")
	@PostMapping("/add")
	@ResponseBody
    public String add(@ModelAttribute  Car car, MultipartFile file){
		
		  
          return Result.ok(
        		  service.saveOrUpdate(car)
          );
    }
	
	@ApiOperation(value = "编辑车辆数据")
	@PostMapping("/edit")
	@ResponseBody
    public String edit(@ModelAttribute  Car car, MultipartFile file){


          return Result.ok(
        		  service.saveOrUpdateIgnoreNull(car)
          );
    }
	
	@ApiOperation(value = "通过条件查询车辆列表数据")
	@PostMapping("/search")
	@ResponseBody
	public String search(Ipage ipage, CarSearch carSearch, Model model) throws Exception{

		  return Result.ok(
				  service.findAllToPage(ipage.of(carSearch))
		  );
	}

	@ApiOperation(value = "查询车辆用户数据")
	@PostMapping("/getUsers")
	@ResponseBody
	public String getUsers(){

		return Result.ok(
				carUserService.findAll()
		);
	}
}
