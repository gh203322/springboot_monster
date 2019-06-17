package com.monster.model.entity.system;

import com.monster.base.annotation.CreatureAno;
import com.monster.model.entity.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;

/**
 * @author Administrator
 *  系统异常
 */
@CreatureAno
@Entity
@Data
@Table(name = "sys_error")
@org.hibernate.annotations.Table(appliesTo = "sys_error", comment = "系统异常表")
public class SysError  extends BaseEntity {

		@Column(columnDefinition = "varchar(10) comment '操作人员'")
		private String name;

		@Column(columnDefinition = "varchar(64) comment '请求路径'")
		private String url;

		@Column(columnDefinition = "text comment '异常信息'")
		private String msg;
}
