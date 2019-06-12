package com.monster.controller.car;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.monster.base.Result;
import com.monster.model.entity.car.Car;
import com.monster.model.request.car.CarSearch;
import com.monster.service.car.CarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "车辆管理接口！")
@Controller(value = "CarController")
@RequestMapping("/auth/car")
@Validated
public class CarController {

	@Autowired
	private CarService service;
	
 
	@ApiOperation(value = "加载车辆主页面")
	@PostMapping("/view")
    public String list(){
		
          return "car/view";
    }
	
	@ApiOperation(value = "获取车辆列表")
	@PostMapping("/list")
	@ResponseBody
	public String getMenuTreeByQuery(Pageable pageable) {
	    
		  return Result.ok(
				  service.findAllToPage(pageable)
		  );
	}
	
	@ApiOperation(value = "加载新增页面")
	@GetMapping("/add")
    public String data(){
		
          return "car/add";
    }
	
	@ApiOperation(value = "加载编辑页面")
	@GetMapping("/edit")
    public String edit(@NotNull(message = "id不能为空")@RequestParam(name = "id") Integer id, Model model){
		
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
	@PostMapping("/addData")
	@ResponseBody
    public String addData(@ModelAttribute  Car car, MultipartFile file){
		
		  
          return Result.ok(
        		  service.saveOrUpdate(car)
          );
    }
	
	@ApiOperation(value = "编辑车辆数据")
	@PostMapping("/editData")
	@ResponseBody
    public String editData(@ModelAttribute  Car car, MultipartFile file){
		
		  
          return Result.ok(
        		  service.saveOrUpdateIgnoreNull(car)
          );
    }
	
	@ApiOperation(value = "通过条件获取车辆列表数据")
	@PostMapping("/listSearch")
	@ResponseBody
	public String listSearch(Pageable pageable, CarSearch carSearch) throws Exception{
	    
		  return Result.ok(
				  service.findAllToPage(pageable, carSearch)
		  );
	}

}
