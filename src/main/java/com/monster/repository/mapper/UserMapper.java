package com.monster.repository.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.monster.model.entity.User;
import com.monster.model.entityVo.UserVo;

@Component
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Map<String,Object> selectByPrimaryKeyToMap(Integer id);

    List<Map<String,Object>> selectAllToMap();
    
    List<UserVo> selectAll();
}