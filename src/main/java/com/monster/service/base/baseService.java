package com.monster.service.base;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

/**
 * @author Administrator
 *  基本service
 * @param <T>
 */
public interface baseService<T> {

    T save(T entity);
    
    T saveOrUpdate(T entity);
    
    boolean saveAll(Iterable<T> entities);
    
    boolean delete(T entity);
    
    boolean deleteById(Long id);
    
    //void deleteBatchByIds(Integer[] ids);
    
    boolean deleteBatchByEntitys(Iterable<T> entities);
    
    T update(T entity);
    
	List<T> findAll();

	//List<T> findByIds(Integer[] ids);
	
	T findById(Long id);

	List<T> findAllByExample(Example<T> example);

	List<T> findAllByExampleAndSort(Example<T> example, Sort sort);
	
	//Map<String,Object> findByIdToMap(Long id);
	
	T saveOrUpdateIgnoreNull(T entity, String...params);
}
