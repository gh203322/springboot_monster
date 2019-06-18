package com.monster.controller.system;	
	
import com.monster.base.reqAndRsp.Ipage;	
import com.monster.base.reqAndRsp.Result;	
import com.monster.model.entity.system.SysError;	
import com.monster.model.request.system.SysErrorSearch;	
import com.monster.service.system.SysErrorService;	
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
	
	
@Api(tags = "SysError")	
@Controller(value = "SysErrorController")	
@RequestMapping("/auth/sysError")	
@Validated	
public class SysErrorController {	
	
	@Autowired	
	private SysErrorService service;	
		
 	
	@ApiOperation(value = "加载SysError主页面")	
	@PostMapping("/view")	
    public String view(){	
			
          return "sysError/view";	
    }	
		
	@ApiOperation(value = "获取SysError列表")	
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
			
          return "sysError/add";	
    }	
		
	@ApiOperation(value = "加载编辑页面")	
	@GetMapping("/editView")	
    public String editView(@NotNull(message = "id不能为空")@RequestParam(name = "id") Long id, Model model){	
			
		  model.addAttribute("sysError", service.findById(id));	
		  	
          return "sysError/edit";	
    }	
		
	@ApiOperation(value = "删除SysError数据")	
	@PostMapping("/delete")	
	@ResponseBody	
    public String delete(@RequestBody List<SysError> entitys){	
			
          return Result.ok(	
        		  service.deleteBatchByEntitys(entitys)	
          );	
    }	
		
	@ApiOperation(value = "新增SysError数据")	
	@PostMapping("/add")	
	@ResponseBody	
    public String add(@ModelAttribute  SysError sysError, MultipartFile file){	
			
		  	
          return Result.ok(	
        		  service.saveOrUpdate(sysError)	
          );	
    }	
		
	@ApiOperation(value = "编辑SysError数据")	
	@PostMapping("/edit")	
	@ResponseBody	
    public String edit(@ModelAttribute  SysError sysError, MultipartFile file){	
			
		  	
          return Result.ok(	
        		  service.saveOrUpdateIgnoreNull(sysError)	
          );	
    }	
		
	@ApiOperation(value = "通过条件查询SysError列表数据")	
	@PostMapping("/search")	
	@ResponseBody	
	public String search(Ipage ipage, SysErrorSearch search) throws Exception{	
	    	
		  return Result.ok(	
				  service.findAllToPage(ipage.of(search))	
		  );	
	}	
	
}	
