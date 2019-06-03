package com.monster.repository.base;

import java.util.List;

public interface BaseRepository {

	public void save(Object entity);

    public void update(Object entity);

    public <T> void delete(Class<T> entityClass, Object entityid);

    public <T> void delete(Class<T> entityClass, Object[] entityids);

    <T> List<T> nativeQueryList(String nativeSql, Object... params);

    <T> List<T> nativeQueryListMap(String nativeSql,Object... params);

    <T> List<T> nativeQueryListModel(Class<T> resultClass, String nativeSql, Object... params);
}
