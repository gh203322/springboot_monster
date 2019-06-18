package com.monster.serviceImpl.car;	
	
import com.github.wenhao.jpa.Specifications;	
import com.monster.base.reqAndRsp.Ipage;	
import com.monster.model.entity.car.CarUser;	
import com.monster.model.request.car.CarUserSearch;	
import com.monster.repository.car.CarUserRepository;	
import com.monster.service.car.CarUserService;	
import com.monster.utils.DataUtil;	
import java.util.List;	
import java.util.Map;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.data.domain.*;	
import org.springframework.data.jpa.domain.Specification;	
import org.springframework.stereotype.Service;	
	
@Service	
public class CarUserServiceImpl implements CarUserService {	
	
	@Autowired	
    private CarUserRepository repository;	
    	
	@Override	
	public CarUser save(CarUser entity) {	
			
		return repository.save(entity);	
	}	
	
	@Override	
	public CarUser saveOrUpdate(CarUser entity) {	
	
		return repository.saveAndFlush(entity);	
	}	
	
	@Override	
	public boolean saveAll(Iterable<CarUser> entities) {	
	
		try {	
			repository.saveAll(entities);	
		} catch (Exception e) {	
			return false;	
		}	
			
		return true;	
	}	
	
	@Override	
	public boolean delete(CarUser entity) {	
        try {	
        	repository.delete(entity);	
		} catch (Exception e) {	
			return false;	
		}	
			
        return true;	
	}	
	
	@Override	
	public boolean deleteById(Long id) {	
	
		try {	
			repository.deleteById(Long.parseLong(String.valueOf(id)));	
		} catch (Exception e) {	
			return false;	
		}	
			
		return true;	
	}	
	
	@Override	
	public boolean deleteBatchByEntitys(Iterable<CarUser> entities) {	
			
		try {	
			repository.deleteInBatch(entities);	
		} catch (Exception e) {	
			return false;	
		}	
			
		return true;	
	}	
	
	@Override	
	public CarUser update(CarUser entity) {	
	
		return repository.saveAndFlush(entity);	
	}	
	
	@Override	
	public List<CarUser> findAll() {	
	
		return repository.findAll();	
	}	
	
	@Override	
	public CarUser findById(Long id) {	
	
		return repository.findById(id).get();	
	}	
	
	@Override	
	public List<CarUser> findAllByExample(Example<CarUser> example) {	
	
		return repository.findAll(example);	
	}	
	
	@Override	
	public List<CarUser> findAllByExampleAndSort(Example<CarUser> example, Sort sort) {	
	
		return repository.findAll(example,sort);	
	}	
	
	@Override	
	public List<Map<String, Object>> findAllToMap() {	
			
		return repository.findAllToMap();	
	}	
	
	@Override	
	public Page<CarUser> findAllToPage(Pageable pageable) {	
			
		return repository.findAll(pageable);	
	}	
	
	@Override	
	public CarUser saveOrUpdateIgnoreNull(CarUser entity, String...params)	
	{	
	
		return repository.saveOrUpdateIgnoreNull(entity, params);	
	}	
	
	@Override	
	public Page<CarUser> findAllToPage(Ipage ipage) throws Exception {	
			
			if(DataUtil.isNotEmptyObj(ipage)) {	
				CarUserSearch search = (CarUserSearch) ipage.getParams();	
				 if(DataUtil.isNotEmptyObj(search)) {	
					 Specification<CarUser> specification = Specifications.<CarUser>and()	
					            .eq(DataUtil.isNotEmptyObj(search.getId()), "id", search.getId())	
					            .build();	
					 	
					 return repository.findAll(specification, ipage.of());	
				 }	
			}	
			
        return null;			
	}	
	
		
}	
