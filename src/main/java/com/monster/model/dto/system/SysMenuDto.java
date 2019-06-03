package com.monster.model.dto.system;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author Administrator
 * 菜单实体数据处理
 */
@Data
public class SysMenuDto {
   
	  private Long id;
	  private String name;
	  private Integer isLeaf;
	  private String css;
	  private String href;
	  private Integer isOpen;
	  private Integer isShow;
	  private Integer sort;
	  private Integer childNum;
	  private Integer isBoot;
      private List<SysMenuDto> childs;
      
}
