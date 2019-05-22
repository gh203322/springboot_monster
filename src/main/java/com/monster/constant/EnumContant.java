package com.monster.constant;

/**
 * @author Administrator
 * 系统常量枚举
 */
public interface EnumContant {
	
	//系统默认角色和编码
	enum SysRoleCode implements EnumContant {
        ADMIN("ADMIN", "超级管理员"),
        NORMAL("NORMAL", "普通用户");

		SysRoleCode(String value, String name) {
            this.value = value;
            this.name = name;
        }

        private String value;
        private String name;

        public String getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }
    }
	
	//系统默认用户
	enum SysUser implements EnumContant {
        ADMIN("admin", "admin");

		SysUser(String value, String name) {
            this.value = value;
            this.name = name;
        }

        private String value;
        private String name;

        public String getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }
    }
	   
}
