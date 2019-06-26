package com.monster.serviceImpl.system;	
	
import com.github.wenhao.jpa.Specifications;	
import com.monster.base.reqAndRsp.Ipage;	
import com.monster.model.entity.system.SysRole;	
import com.monster.model.request.system.SysRoleSearch;	
import com.monster.repository.system.SysRoleRepository;	
import com.monster.service.system.SysRoleService;	
import com.monster.utils.DataUtil;	
import java.util.List;	
import java.util.Map;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.data.domain.*;	
import org.springframework.data.jpa.domain.Specification;	
import org.springframework.stereotype.Service;	
	
@Service	
public class SysRoleServiceImpl implements SysRoleService {	
	
	@Autowired	
    private SysRoleRepository repository;	
    	
	@Override	
	public SysRole save(SysRole entity) {	
			
		return repository.save(entity);	
	}	
	
	@Override	
	public SysRole saveOrUpdate(SysRole entity) {	
	
		return repository.saveAndFlush(entity);	
	}	
	
	@Override	
	public boolean saveAll(Iterable<SysRole> entities) {	
	
		try {	
			repository.saveAll(entities);	
		} catch (Exception e) {	
			return false;	
		}	
			
		return true;	
	}	
	
	@Override	
	public boolean delete(SysRole entity) {	
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
	public boolean deleteBatchByEntitys(Iterable<SysRole> entities) {	
			
		try {	
			repository.deleteInBatch(entities);	
		} catch (Exception e) {	
			return false;	
		}	
			
		return true;	
	}	
	
	@Override	
	public SysRole update(SysRole entity) {	
	
		return repository.saveAndFlush(entity);	
	}	
	
	@Override	
	public List<SysRole> findAll() {	
	
		return repository.findAll();	
	}	
	
	@Override	
	public SysRole findById(Long id) {	
	
		return repository.findById(id).get();	
	}	
	
	@Override	
	public List<SysRole> findAllByExample(Example<SysRole> example) {	
	
		return repository.findAll(example);	
	}	
	
	@Override	
	public List<SysRole> findAllByExampleAndSort(Example<SysRole> example, Sort sort) {	
	
		return repository.findAll(example,sort);	
	}	
	
	@Override	
	public List<Map<String, Object>> findAllToMap() {	
			
		return repository.findAllToMap();	
	}	
	
	@Override	
	public Page<SysRole> findAllToPage(Pageable pageable) {	
			
		return repository.findAll(pageable);	
	}	
	
	@Override	
	public SysRole saveOrUpdateIgnoreNull(SysRole entity, String...params)	
	{	
	
		return repository.saveOrUpdateIgnoreNull(entity, params);	
	}	
	
	@Override	
	public Page<SysRole> findAllToPage(Ipage ipage) throws Exception {	
			
			if(DataUtil.isNotEmptyObj(ipage)) {	
				SysRoleSearch search = (SysRoleSearch) ipage.getParams();	
				 if(DataUtil.isNotEmptyObj(search)) {	
					 Specification<SysRole> specification = Specifications.<SysRole>and()	
					            .eq(DataUtil.isNotEmptyObj(search.getId()), "id", search.getId())
					            .build();	
					 	
					 return repository.findAll(specification, ipage.of());	
				 }	
			}	
			
        return null;			
	}	
	
		
}	
