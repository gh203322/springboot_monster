package com.monster.userTest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.monster.model.entity.system.SysUser;
import com.monster.model.entityVo.UserVo;
import com.monster.repository.mapper.UserMapper;
import com.monster.repository.system.SysUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.monster.repository.mapper")
public class UserTest1 {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SysUserRepository userRepository;

    @Test
    public void contextLoads() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
        List<SysUser> userList1 = userRepository.findAll();
        System.out.println("jpa:"+userList1.get(0).toString());
        
        PageHelper.startPage(1, 2);
        @SuppressWarnings("rawtypes")
		List<UserVo> userList2= userMapper.selectAll();
        PageInfo pageInfo = new PageInfo<>(userList2);
        System.out.println("mybatis:"+pageInfo.toString());
        
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}

