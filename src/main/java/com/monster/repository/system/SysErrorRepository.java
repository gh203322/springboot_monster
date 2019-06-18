package com.monster.repository.system;	
	
import com.monster.model.entity.system.SysError;	
import com.monster.repository.base.BaseRepository;	
import java.util.List;	
import java.util.Map;	
import org.springframework.data.jpa.repository.Query;	
	
public interface SysErrorRepository extends BaseRepository<SysError>{	
		
	@Query(	
			nativeQuery = true,	
			value = "select * from sysError"	
	)	
	List<Map<String, Object>> findAllToMap();	
}	
