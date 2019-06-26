package com.monster.controller.system;	
	
import com.monster.base.reqAndRsp.Ipage;
import com.monster.base.reqAndRsp.Result;
import com.monster.model.request.system.SysRoleSearch;
import com.monster.service.system.SysRoleService;
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
	
	
@Api(tags = "SysRole")	
@Controller(value = "SysRoleController")	
@RequestMapping("/auth/sysRole")	
@Validated	
public class SysRoleController {	
	
	@Autowired	
	private SysRoleService service;	
		
 	
	@ApiOperation(value = "加载SysRole主页面")	
	@PostMapping("/view")	
    public String view(){	
			
          return "sysRole/view";	
    }	
		
	@ApiOperation(value = "获取SysRole列表")	
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
			
          return "sysRole/add";	
    }	
		
	@ApiOperation(value = "加载编辑页面")	
	@GetMapping("/editView")	
    public String editView(@NotNull(message = "id不能为空")@RequestParam(name = "id") Long id, Model model){	
			
		  model.addAttribute("sysRole", service.findById(id));	
		  	
          return "sysRole/edit";	
    }	
		
	@ApiOperation(value = "删除SysRole数据")	
	@PostMapping("/delete")	
	@ResponseBody	
    public String delete(@RequestBody List<SysRoleSearch> entitys){
			
          return Result.ok(	
        		  service.deleteBatchByEntitys(null)
          );	
    }	
		
	@ApiOperation(value = "新增SysRole数据")	
	@PostMapping("/add")	
	@ResponseBody	
    public String add(@ModelAttribute  SysRoleSearch sysRoleSearch, MultipartFile file){
			
		  	
          return Result.ok(	
        		  service.saveOrUpdate(null)
          );	
    }	
		
	@ApiOperation(value = "编辑SysRole数据")	
	@PostMapping("/edit")	
	@ResponseBody	
    public String edit(@ModelAttribute  SysRoleSearch sysRoleSearch, MultipartFile file){
			
		  	
          return Result.ok(	
        		  service.saveOrUpdateIgnoreNull(null)
          );	
    }	
		
	@ApiOperation(value = "通过条件查询SysRole列表数据")	
	@PostMapping("/search")	
	@ResponseBody	
	public String search(Ipage ipage, SysRoleSearch search) throws Exception{	
	    	
		  return Result.ok(	
				  service.findAllToPage(ipage.of(search))	
		  );	
	}	
	
}	
