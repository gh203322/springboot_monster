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

    //系统操作类型(对应 contralloer 层基本的CRUD操作 需要规范命名以匹配关键字)
    enum SysOperate implements EnumContant {
        VIEW("view", "打开主页面"),
        List("list", "刷新列表"),
        ADDVIEW("addView", "打开新增页面"),
        EDITVIEW("editView", "打开修改页面"),
        DELETE("delete", "删除数据"),
        ADD("add", "新增数据"),
        EDIT("edit", "修改数据"),
        SEARCH("search", "查询数据"),
        OTHER("other", "其他操作");

        SysOperate(String value, String name) {
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
