package com.monster.repository.base;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.monster.utils.DataUtil;
import com.monster.utils.UpdateUtil;

@SuppressWarnings("deprecation")
@NoRepositoryBean
public interface BaseRepository<T> extends
								Serializable,
								JpaRepository<T, Integer>,
								JpaSpecificationExecutor<T> {

	
    /**   
     * @Title: saveOrUpdate   
     * @Description:   保存方法，空字段不更新，如果要更新某字段为空，则将字段名写入可变参数
     * @param: @param <T>
     * @param: @param entity
     * @param: @return      
     * @return: T      
     * @throws   
     */
	@SuppressWarnings("unchecked")
	default <S extends T> S saveOrUpdateIgnoreNull(S entity, String...params) {
		
    	try {
			PropertyDescriptor pd = new PropertyDescriptor("id", entity.getClass());  
			Method rM = pd.getReadMethod();//获得读方法  
		    Integer id = Integer.parseInt(String.valueOf(rM.invoke(entity)));
		    if(DataUtil.isNotEmptyObj(id)) {
		    	S oldEntity = (S) findById(id).get();
		    	UpdateUtil.copyNullProperties(entity, oldEntity, params);
		    	saveAndFlush(oldEntity);
		    	return oldEntity;
		    }else {
		    	saveAndFlush(entity);
		    }
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	return entity;
	}

	/**   
	 * @Title: createNativeQuery   
	 * @Description: 通过sql查询对象
	 * @param: @param em
	 * @param: @param sql
	 * @param: @param params
	 * @param: @return      
	 * @return: Query      
	 * @throws   
	 */
	default Query createNativeQuery(EntityManager em, String sql, Object... params) {
        Query q = em.createNativeQuery(sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                q.setParameter(i + 1, params[i]); // 与Hiberante不同,jpa query从位置1开始
            }
        }
        return q;
    }
	
	/**   
	 * @Title: nativeQueryList   
	 * @Description: 通过sql查询对象集合
	 * @param: @param <T>
	 * @param: @param em
	 * @param: @param nativeSql
	 * @param: @param params
	 * @param: @return      
	 * @return: List<T>      
	 * @throws   
	 */
	@SuppressWarnings({ "hiding", "unchecked" })
	default <T> List<T> nativeQueryList(EntityManager em, String nativeSql, Object... params) {
        Query q = createNativeQuery(em, nativeSql, params);
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.TO_LIST);
        return q.getResultList();
    }

	/**   
	 * @Title: nativeQueryListModel   
	 * @Description: 通过sql查询集合，返回指定的对象类型
	 * @param: @param <T>
	 * @param: @param em
	 * @param: @param resultClass
	 * @param: @param nativeSql
	 * @param: @param params
	 * @param: @return      
	 * @return: List<T>      
	 * @throws   
	 */
	@SuppressWarnings({ "hiding", "unchecked" })
	default <T> List<T> nativeQueryListModel(EntityManager em, Class<T> resultClass,String nativeSql, Object... params) {
        Query q = createNativeQuery(em, nativeSql, params);;
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(resultClass));
        return q.getResultList();
    }

	/**   
	 * @Title: nativeQueryListMap   
	 * @Description: 通过sql查询map集合
	 * @param: @param <T>
	 * @param: @param em
	 * @param: @param nativeSql
	 * @param: @param params
	 * @param: @return      
	 * @return: List<T>      
	 * @throws   
	 */
	@SuppressWarnings({ "hiding", "unchecked" })
    default <T> List<T> nativeQueryListMap(EntityManager em, String nativeSql, Object... params) {
        Query q = createNativeQuery(em, nativeSql, params);
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return q.getResultList();
    }
}
