package com.monster.repository.system;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.monster.model.entity.system.SysUser;

public interface SysUserRepository extends
			Serializable,
			JpaRepository<SysUser, Integer>,
			JpaSpecificationExecutor<SysUser>{
	
	

}
