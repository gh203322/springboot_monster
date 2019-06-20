package com.monster.model.entity.system;

import com.monster.base.annotation.CreatureAno;
import com.monster.model.entity.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;

/**
 * @author Administrator
 *  系统操作记录
 */
@CreatureAno
@Entity
@Data
@Table(name = "sys_operate")
@org.hibernate.annotations.Table(appliesTo = "sys_operate", comment = "系统操作表")
public class SysOperate extends BaseEntity {

		@Column(columnDefinition = "varchar(10) comment '操作人员'")
		private String name;

		@Column(columnDefinition = "varchar(128) comment '请求路径'")
		private String url;

	    @Column(columnDefinition = "varchar(128) comment '层或方法名称'")
		private String method;

		@Column(columnDefinition = "varchar(10) comment '操作类型'")
		private String type;

		@Column(columnDefinition = "varchar(10) comment '操作结果'")
		private String result;

		@Column(columnDefinition = "text comment '请求参数'")
		private String requestContext;

		@Column(columnDefinition = "text comment '返回结果'")
		private String responseContext;

		@Column(columnDefinition = "varchar(15)  comment 'ip地址'")
		private String ip;
}
