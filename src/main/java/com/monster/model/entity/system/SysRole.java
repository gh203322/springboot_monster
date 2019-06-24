package com.monster.model.entity.system;

import com.monster.base.annotation.CreatureAno;
import com.monster.model.entity.base.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

/**
 * @author Administrator
 *  系统角色
 */

@CreatureAno
@Entity
@Data
@Table(name = "sys_role")
@org.hibernate.annotations.Table(appliesTo = "sys_role", comment = "系统角色表")
public class SysRole  extends BaseEntity {

	  @Column(nullable = false, columnDefinition = "varchar(15) comment '角色名称'" )
	  private String name;
	  
	  @Column(columnDefinition = "varchar(15) comment '角色编码'")
	  private String code;

	  @Column(nullable = false, columnDefinition = "int(1) default 0 comment '新增权限'" )
	  private Boolean addAuth;

	 @Column(nullable = false, columnDefinition = "int(1) default 0 comment '删除权限'" )
	 private Boolean deleteAuth;

	 @Column(nullable = false, columnDefinition = "int(1) default 0 comment '编辑权限'" )
	 private Boolean modifyAuth;

	 @Column(nullable = false, columnDefinition = "int(1) default 0 comment '查询权限'" )
	 private Boolean queryAuth;

	  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sysRoles")
	  private List<SysMenu> sysMenus;

	  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sysRoles")
	  private List<SysUser> sysUsers;

	/* 解决序列化循环引用问题,在这里截断关系 */
	public List<SysMenu> getSysMenus() {
		if(null != sysMenus) {
			sysMenus.stream().forEach(menu -> menu.setSysRoles(null));
		}else{
			sysMenus = new ArrayList<SysMenu>();
		}
		return sysMenus;
	}

	/* 解决序列化循环引用问题,在这里截断关系 */
	public List<SysUser> getSysUsers() {
		if(null != sysUsers) {
			sysUsers.stream().forEach(user -> user.setSysRoles(null));
		}else{
			sysUsers = new ArrayList<SysUser>();
		}
		return sysUsers;
	}
}
