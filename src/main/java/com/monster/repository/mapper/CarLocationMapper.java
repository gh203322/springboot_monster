package com.monster.repository.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.monster.model.entity.car.CarUser;
import com.monster.model.entityVo.UserVo;

@Component
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CarUser record);

    int insertSelective(CarUser record);

    CarUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarUser record);

    int updateByPrimaryKey(CarUser record);

    Map<String,Object> selectByPrimaryKeyToMap(Integer id);

    List<Map<String,Object>> selectAllToMap();
    
    List<UserVo> selectAll();
}