package com.monster.repository.system;	
	
import com.monster.model.entity.system.SysRole;	
import com.monster.repository.base.BaseRepository;	
import java.util.List;	
import java.util.Map;	
import org.springframework.data.jpa.repository.Query;	
	
public interface SysRoleRepository extends BaseRepository<SysRole>{	
		
	@Query(	
			nativeQuery = true,	
			value = "select * from sysRole"	
	)	
	List<Map<String, Object>> findAllToMap();	
}	
