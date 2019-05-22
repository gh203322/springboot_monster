package com.monster.model.entity.system;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Administrator
 *  系统角色
 */
@Entity
@Data
@Table(name = "sys_role")
@org.hibernate.annotations.Table(appliesTo = "sys_role", comment = "系统角色表")
public class SysRole {

	  @GeneratedValue
	  @Id
	  private Long id;
	  
	  @Column(nullable = false, columnDefinition = "varchar(15) comment '角色名称'" )
	  private String name;
	  
	  @Column(nullable = true, columnDefinition = "varchar(15) comment '角色编码'")
	  private String code;
	  
	  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sysRoles")
	  private List<SysMenu> sysMenus;
	  
	  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sysRoles")
	  private List<SysUser> sysUsers;
}
