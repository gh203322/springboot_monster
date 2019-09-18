package com.monster.controller.car;	
	
import com.monster.base.reqAndRsp.Ipage;	
import com.monster.base.reqAndRsp.Result;	
import com.monster.model.entity.car.CarUser;	
import com.monster.model.request.car.CarUserSearch;	
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
	
	
@Api(tags = "CarUser")	
@Controller(value = "CarUserController")	
@RequestMapping("/auth/carUser")	
@Validated	
public class CarUserController {	
	
	@Autowired	
	private CarUserService service;	
		
 	
	@ApiOperation(value = "加载CarUser主页面")	
	@PostMapping("/view")	
    public String view(){	
			
          return "carUser/view";	
    }	
		
	@ApiOperation(value = "获取CarUser列表")	
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
			
          return "carUser/add";	
    }	
		
	@ApiOperation(value = "加载编辑页面")	
	@GetMapping("/editView")	
    public String editView(@NotNull(message = "id不能为空")@RequestParam(name = "id") Long id, Model model){	
			
		  model.addAttribute("carUser", service.findById(id));	
		  	
          return "carUser/edit";	
    }	
		
	@ApiOperation(value = "删除CarUser数据")	
	@PostMapping("/delete")	
	@ResponseBody	
    public String delete(@RequestBody List<CarUser> entitys){	
			
          return Result.ok(	
        		  service.deleteBatchByEntitys(entitys)	
          );	
    }	
		
	@ApiOperation(value = "新增CarUser数据")	
	@PostMapping("/add")	
	@ResponseBody	
    public String add(@ModelAttribute  CarUser carUser, MultipartFile file){	
			
		  	
          return Result.ok(	
        		  service.saveOrUpdate(carUser)	
          );	
    }	
		
	@ApiOperation(value = "编辑CarUser数据")	
	@PostMapping("/edit")	
	@ResponseBody	
    public String edit(@ModelAttribute  CarUser carUser, MultipartFile file){	

          return Result.ok(	
        		  service.saveOrUpdateIgnoreNull(carUser)	
          );	
    }	
		
	@ApiOperation(value = "通过条件查询CarUser列表数据")	
	@PostMapping("/search")	
	@ResponseBody	
	public String search(Ipage ipage, CarUserSearch search) throws Exception{

		  return Result.ok(
				  service.findAllToPage(ipage.of(search))
		  );
	}	
	
}	
