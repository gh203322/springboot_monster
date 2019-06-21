package com.monster.serviceImpl.system;	
	
import com.github.wenhao.jpa.Specifications;	
import com.monster.base.reqAndRsp.Ipage;
import com.monster.model.entity.base.BaseDate;
import com.monster.model.entity.system.SysError;
import com.monster.model.request.system.SysErrorSearch;	
import com.monster.repository.system.SysErrorRepository;	
import com.monster.service.system.SysErrorService;	
import com.monster.utils.DataUtil;	
import java.util.List;	
import java.util.Map;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.data.domain.*;	
import org.springframework.data.jpa.domain.Specification;	
import org.springframework.stereotype.Service;	
	
@Service	
public class SysErrorServiceImpl implements SysErrorService {	
	
	@Autowired	
    private SysErrorRepository repository;	
    	
	@Override	
	public SysError save(SysError entity) {	
			
		return repository.save(entity);	
	}	
	
	@Override	
	public SysError saveOrUpdate(SysError entity) {	
	
		return repository.saveAndFlush(entity);	
	}	
	
	@Override	
	public boolean saveAll(Iterable<SysError> entities) {	
	
		try {	
			repository.saveAll(entities);	
		} catch (Exception e) {	
			return false;	
		}	
			
		return true;	
	}	
	
	@Override	
	public boolean delete(SysError entity) {	
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
	public boolean deleteBatchByEntitys(Iterable<SysError> entities) {	
			
		try {	
			repository.deleteInBatch(entities);	
		} catch (Exception e) {	
			return false;	
		}	
			
		return true;	
	}	
	
	@Override	
	public SysError update(SysError entity) {	
	
		return repository.saveAndFlush(entity);	
	}	
	
	@Override	
	public List<SysError> findAll() {	
	
		return repository.findAll();	
	}	
	
	@Override	
	public SysError findById(Long id) {	
	
		return repository.findById(id).get();	
	}	
	
	@Override	
	public List<SysError> findAllByExample(Example<SysError> example) {	
	
		return repository.findAll(example);	
	}	
	
	@Override	
	public List<SysError> findAllByExampleAndSort(Example<SysError> example, Sort sort) {	
	
		return repository.findAll(example,sort);	
	}	
	
	@Override	
	public List<Map<String, Object>> findAllToMap() {	
			
		return repository.findAllToMap();	
	}	
	
	@Override	
	public Page<SysError> findAllToPage(Pageable pageable) {	
			
		return repository.findAll(pageable);	
	}	
	
	@Override	
	public SysError saveOrUpdateIgnoreNull(SysError entity, String...params)	
	{	
	
		return repository.saveOrUpdateIgnoreNull(entity, params);	
	}	
	
	@Override	
	public Page<SysError> findAllToPage(Ipage ipage) throws Exception {	
			
			if(DataUtil.isNotEmptyObj(ipage)) {	
				SysErrorSearch search = (SysErrorSearch) ipage.getParams();
				BaseDate baseDate = BaseDate.of(search.getCreateTimeStr());
				 if(DataUtil.isNotEmptyObj(search)) {	
					 Specification<SysError> specification = Specifications.<SysError>and()	
					            .eq(DataUtil.isNotEmptyObj(search.getId()), "id", search.getId())
							    .like(DataUtil.isNotEmptyObj(search.getName()), "name", "%"+search.getName()+"%")
							    .like(DataUtil.isNotEmptyObj(search.getUrl()), "url", "%"+search.getUrl()+"%")
							    .ge(DataUtil.isNotEmptyObj(baseDate.getLastYmdH()),"createTime",baseDate.getLastYmdH())   /*大于等于*/
							    .lt(DataUtil.isNotEmptyObj(baseDate.getNextYmdH()), "createTime", baseDate.getNextYmdH())  /*小于*/
					            .build();	
					 	
					 return repository.findAll(specification, ipage.of());	
				 }	
			}	
			
        return null;			
	}	
	
		
}	
