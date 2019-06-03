package com.monster.userTest;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.monster.repository.system.SysMenuRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.monster.mapper")
public class CmmonTest {

    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Test
    public void contextLoads() {

    	List<Map<String, Object>>  list = sysMenuRepository.findBootNode();
    	System.out.println(list);
    	
    	System.out.println(sysMenuRepository.findChildNum(1L));
    }
}

