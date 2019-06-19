package com.monster.service.system;	
	
import com.monster.base.reqAndRsp.Ipage;	
import com.monster.model.entity.system.SysOperate;	
import com.monster.service.base.baseService;	
import java.util.List;	
import java.util.Map;	
import org.springframework.data.domain.Page;	
import org.springframework.data.domain.Pageable;	
	
public interface SysOperateService extends baseService<SysOperate>{	
	
	 List<Map<String, Object>> findAllToMap();	
	 	
	 Page<SysOperate> findAllToPage(Pageable pageable);	
	 	
	 Page<SysOperate> findAllToPage(Ipage ipage) throws Exception;	
}	
