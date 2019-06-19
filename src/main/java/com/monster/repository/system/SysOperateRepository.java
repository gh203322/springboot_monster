package com.monster.repository.system;	
	
import com.monster.model.entity.system.SysOperate;	
import com.monster.repository.base.BaseRepository;	
import java.util.List;	
import java.util.Map;	
import org.springframework.data.jpa.repository.Query;	
	
public interface SysOperateRepository extends BaseRepository<SysOperate>{	
		
	@Query(	
			nativeQuery = true,	
			value = "select * from sysOperate"	
	)	
	List<Map<String, Object>> findAllToMap();	
}	
