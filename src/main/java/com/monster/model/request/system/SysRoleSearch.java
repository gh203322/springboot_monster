package com.monster.model.request.system;

import com.monster.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author Administrator	
 *  实体对应的查询数据封装类	
 */
@Data
public class SysRoleSearch extends BaseEntity {

        private String name;

        private String code;

        private Long[] sysMenus;

        private Long[] sysUsers;

        private Boolean addAuth;

        private Boolean deleteAuth;

        private Boolean modifyAuth;

        private Boolean queryAuth;
}	
