package com.monster.model.entity.system;

import java.util.List;

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

@Entity
@Data
@Table(name = "sys_user")
@org.hibernate.annotations.Table(appliesTo = "sys_user", comment = "系统用户表")
public class SysUser {

	    @GeneratedValue
	    @Id
	    private Long id;
	    
	    @Column(columnDefinition = "varchar(10) comment '用户名'")
	    private String name;
	    
	    @Column(columnDefinition = "varchar(15) comment '手机号'")
	    private String phone;
	    
	    @Column(columnDefinition = "varchar(128) comment '密码'")
	    private String pass;
	    
	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	          name="mp_user_role",
	          joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
	          inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id")
	    )
	    private List<SysRole> sysRoles;
}
