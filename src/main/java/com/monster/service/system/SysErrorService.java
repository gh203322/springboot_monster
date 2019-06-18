package com.monster.service.system;	
	
import com.monster.base.reqAndRsp.Ipage;	
import com.monster.model.entity.system.SysError;	
import com.monster.service.base.baseService;	
import java.util.List;	
import java.util.Map;	
import org.springframework.data.domain.Page;	
import org.springframework.data.domain.Pageable;	
	
public interface SysErrorService extends baseService<SysError>{	
	
	 List<Map<String, Object>> findAllToMap();	
	 	
	 Page<SysError> findAllToPage(Pageable pageable);	
	 	
	 Page<SysError> findAllToPage(Ipage ipage) throws Exception;	
}	
