package com.monster.serviceImpl.system;	
	
import com.github.wenhao.jpa.Specifications;
import com.monster.base.reqAndRsp.Ipage;
import com.monster.model.entity.base.BaseDate;
import com.monster.model.entity.system.SysOperate;
import com.monster.model.request.system.SysOperateSearch;
import com.monster.repository.system.SysOperateRepository;
import com.monster.service.system.SysOperateService;
import com.monster.utils.DataUtil;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;	
	
@Service	
public class SysOperateServiceImpl implements SysOperateService {	
	
	@Autowired	
    private SysOperateRepository repository;	
    	
	@Override	
	public SysOperate save(SysOperate entity) {	
			
		return repository.save(entity);	
	}	
	
	@Override	
	public SysOperate saveOrUpdate(SysOperate entity) {	
	
		return repository.saveAndFlush(entity);	
	}	
	
	@Override	
	public boolean saveAll(Iterable<SysOperate> entities) {	
	
		try {	
			repository.saveAll(entities);	
		} catch (Exception e) {	
			return false;	
		}	
			
		return true;	
	}	
	
	@Override	
	public boolean delete(SysOperate entity) {	
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
	public boolean deleteBatchByEntitys(Iterable<SysOperate> entities) {	
			
		try {	
			repository.deleteInBatch(entities);	
		} catch (Exception e) {	
			return false;	
		}	
			
		return true;	
	}	
	
	@Override	
	public SysOperate update(SysOperate entity) {	
	
		return repository.saveAndFlush(entity);	
	}	
	
	@Override	
	public List<SysOperate> findAll() {	
	
		return repository.findAll();	
	}	
	
	@Override	
	public SysOperate findById(Long id) {	
	
		return repository.findById(id).get();	
	}	
	
	@Override	
	public List<SysOperate> findAllByExample(Example<SysOperate> example) {	
	
		return repository.findAll(example);	
	}	
	
	@Override	
	public List<SysOperate> findAllByExampleAndSort(Example<SysOperate> example, Sort sort) {	
	
		return repository.findAll(example,sort);	
	}	
	
	@Override	
	public List<Map<String, Object>> findAllToMap() {	
			
		return repository.findAllToMap();	
	}	
	
	@Override	
	public Page<SysOperate> findAllToPage(Pageable pageable) {	
			
		return repository.findAll(pageable);	
	}	
	
	@Override	
	public SysOperate saveOrUpdateIgnoreNull(SysOperate entity, String...params)	
	{	
	
		return repository.saveOrUpdateIgnoreNull(entity, params);	
	}	
	
	@Override	
	public Page<SysOperate> findAllToPage(Ipage ipage) throws Exception {	
			
			if(DataUtil.isNotEmptyObj(ipage)) {	
				SysOperateSearch search = (SysOperateSearch) ipage.getParams();
				BaseDate baseDate = BaseDate.of(search.getCreateTimeStr());
				 if(DataUtil.isNotEmptyObj(search)) {	
					 Specification<SysOperate> specification = Specifications.<SysOperate>and()	
					            .eq(DataUtil.isNotEmptyObj(search.getId()), "id", search.getId())
							    .like(DataUtil.isNotEmptyObj(search.getName()), "name", "%"+search.getName()+"%")
							    .like(DataUtil.isNotEmptyObj(search.getMethod()), "method", "%"+search.getMethod()+"%")
							    .ge(DataUtil.isNotEmptyObj(baseDate.getLastYmdH()),"createTime",baseDate.getLastYmdH())   /*大于等于*/
							    .lt(DataUtil.isNotEmptyObj(baseDate.getNextYmdH()), "createTime", baseDate.getNextYmdH())  /*小于*/
					            .build();
					 	
					 return repository.findAll(specification, ipage.of());	
				 }	
			}	
			
        return null;			
	}	
	
		
}	
