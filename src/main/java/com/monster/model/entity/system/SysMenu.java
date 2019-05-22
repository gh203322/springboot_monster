package com.monster.model.entity.system;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Administrator
 *  系统菜单
 */
@Entity
@Data
@Table(name = "sys_menu")
@org.hibernate.annotations.Table(appliesTo = "sys_menu", comment = "系统菜单表")
public class SysMenu {

	  @GeneratedValue
	  @Id
	  private Long id;
	  
	  @Column(nullable = false, columnDefinition = "varchar(15) comment '菜单名称'" )
	  private String name;
	  
	  @Column(nullable = true, columnDefinition = "int(1) default 0 comment '是否是叶子节点'")
	  private Integer isLeaf;
	  
	  @Column(nullable = true, columnDefinition = "int(11) comment '上级菜单id'")
	  private Long parentId;
      
	  @ManyToMany
	  @JoinTable(
			  name = "mp_menu_role",
	          joinColumns = @JoinColumn(name="menuId",referencedColumnName = "id"),
	          inverseJoinColumns = @JoinColumn(name = "roleId",referencedColumnName = "id"))
	  private List<SysRole> sysRoles;
}
