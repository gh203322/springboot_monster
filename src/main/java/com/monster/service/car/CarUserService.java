package com.monster.service.car;	
	
import com.monster.base.reqAndRsp.Ipage;	
import com.monster.model.entity.car.CarUser;	
import com.monster.service.base.baseService;	
import java.util.List;	
import java.util.Map;	
import org.springframework.data.domain.Page;	
import org.springframework.data.domain.Pageable;	
	
public interface CarUserService extends baseService<CarUser>{	
	
	 List<Map<String, Object>> findAllToMap();	
	 	
	 Page<CarUser> findAllToPage(Pageable pageable);	
	 	
	 Page<CarUser> findAllToPage(Ipage ipage) throws Exception;	
}	
