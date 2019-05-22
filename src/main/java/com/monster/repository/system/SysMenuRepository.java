package com.monster.repository.system;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.monster.model.entity.system.SysMenu;

public interface SysMenuRepository extends
			Serializable,
			JpaRepository<SysMenu, Integer>,
			JpaSpecificationExecutor<SysMenu>{
	
	

}
