package com.monster.controller.system;

import com.monster.base.annotation.OperateLogAno;
import com.monster.base.reqAndRsp.Ipage;
import com.monster.base.reqAndRsp.Result;
import com.monster.model.entity.system.SysOperate;
import com.monster.model.request.system.SysOperateSearch;
import com.monster.service.system.SysOperateService;
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
	
	
@Api(tags = "SysOperate")	
@Controller(value = "SysOperateController")	
@RequestMapping("/auth/sysOperate")	
@Validated	
public class SysOperateController {	
	
	@Autowired	
	private SysOperateService service;


	@OperateLogAno("测试测试")
	@ApiOperation(value = "加载SysOperate主页面")	
	@PostMapping("/view")	
    public String view(){	
			
          return "sysOperate/view";	
    }

	@ApiOperation(value = "获取SysOperate列表")
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
			
          return "sysOperate/add";	
    }	
		
	@ApiOperation(value = "加载编辑页面")	
	@GetMapping("/editView")	
    public String editView(@NotNull(message = "id不能为空")@RequestParam(name = "id") Long id, Model model){	
			
		  model.addAttribute("sysOperate", service.findById(id));	
		  	
          return "sysOperate/edit";	
    }	
		
	@ApiOperation(value = "删除SysOperate数据")	
	@PostMapping("/delete")	
	@ResponseBody	
    public String delete(@RequestBody List<SysOperate> entitys){	
			
          return Result.ok(	
        		  service.deleteBatchByEntitys(entitys)	
          );	
    }	
		
	@ApiOperation(value = "新增SysOperate数据")	
	@PostMapping("/add")	
	@ResponseBody	
    public String add(@ModelAttribute  SysOperate sysOperate, MultipartFile file){	
			
		  	
          return Result.ok(	
        		  service.saveOrUpdate(sysOperate)	
          );	
    }	
		
	@ApiOperation(value = "编辑SysOperate数据")	
	@PostMapping("/edit")	
	@ResponseBody	
    public String edit(@ModelAttribute  SysOperate sysOperate, MultipartFile file){	
			
		  	
          return Result.ok(	
        		  service.saveOrUpdateIgnoreNull(sysOperate)	
          );	
    }

	@OperateLogAno("测试测试")
	@ApiOperation(value = "通过条件查询SysOperate列表数据")	
	@PostMapping("/search")	
	@ResponseBody	
	public String search(Ipage ipage, SysOperateSearch search) throws Exception{	
	    	
		  return Result.ok(	
				  service.findAllToPage(ipage.of(search))	
		  );	
	}	
	
}	
