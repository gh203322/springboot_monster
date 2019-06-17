package com.monster.model.entity.system;

import com.monster.model.entity.base.BaseEntity;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

/**
 * @author Administrator
 *  系统菜单
 */
@Entity
@Data
@Table(name = "sys_menu")
@org.hibernate.annotations.Table(appliesTo = "sys_menu", comment = "系统菜单表")
public class SysMenu  extends BaseEntity {

	  @Column(nullable = false, columnDefinition = "varchar(15) comment '菜单名称'" )
	  private String name;
	  
	  @Column(nullable = true, columnDefinition = "int(1) default 0 comment '是否是叶子节点'")
	  private Integer isLeaf;
	  
	  @Column(nullable = true, columnDefinition = "varchar(255) comment '菜单class样式'" )
	  private String css;
      
	  @Column(nullable = true, columnDefinition = "varchar(50) comment '对应的html页面'" )
	  private String href;
	  
	  @Column(nullable = true, columnDefinition = "int(1) default 0 comment '是否默认展开'")
	  private Integer isOpen;
	  
	  @Column(nullable = true, columnDefinition = "int(1) default 1 comment '是否默认显示'")
	  private Integer isShow;
	  
	  @Column(nullable = true, columnDefinition = "int(3) default 0 comment '自定义排序'")
	  private Integer sort;
	  
	  @ManyToMany
	  @JoinTable(
			  name = "mp_menu_role",
	          joinColumns = @JoinColumn(name="menuId",referencedColumnName = "id"),
	          inverseJoinColumns = @JoinColumn(name = "roleId",referencedColumnName = "id"))
	  private List<SysRole> sysRoles;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "parentId")
	  private SysMenu sysMenu;
	  
	  @OneToMany(
			  fetch = FetchType.LAZY,
			  mappedBy = "sysMenu" 
	   )
	  private List<SysMenu> sysMenus;
}
