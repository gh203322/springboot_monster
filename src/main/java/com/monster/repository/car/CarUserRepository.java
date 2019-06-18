package com.monster.repository.car;	
	
import com.monster.model.entity.car.CarUser;	
import com.monster.repository.base.BaseRepository;	
import java.util.List;	
import java.util.Map;	
import org.springframework.data.jpa.repository.Query;	
	
public interface CarUserRepository extends BaseRepository<CarUser>{	
		
	@Query(	
			nativeQuery = true,	
			value = "select * from carUser"	
	)	
	List<Map<String, Object>> findAllToMap();	
}	
