package com.monster.service.system;	
	
import com.monster.base.reqAndRsp.Ipage;	
import com.monster.model.entity.system.SysRole;	
import com.monster.service.base.baseService;	
import java.util.List;	
import java.util.Map;	
import org.springframework.data.domain.Page;	
import org.springframework.data.domain.Pageable;	
	
public interface SysRoleService extends baseService<SysRole>{	
	
	 List<Map<String, Object>> findAllToMap();	
	 	
	 Page<SysRole> findAllToPage(Pageable pageable);	
	 	
	 Page<SysRole> findAllToPage(Ipage ipage) throws Exception;	
}	
