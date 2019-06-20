package com.monster.model.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)  //开启时间类型字段设置默认值
public class BaseEntity {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

		@Column(columnDefinition = "int(1) comment '数据是否有效,用于逻辑删除'")
		private Boolean valid = true;

		@CreatedDate  //自动创建时间
		@Column(columnDefinition = "datetime comment '创建时间'", updatable = true, insertable = true)
		@DateTimeFormat(pattern  = "yyyy-MM-dd HH:mm:ss")  //传入格式化
		@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
		private Date createTime;

	    @LastModifiedDate  //自动更新记录时间
		@Column(columnDefinition = "datetime comment '更新时间'", updatable = true, insertable = true)
		@DateTimeFormat(pattern  = "yyyy-MM-dd HH:mm:ss")  //传出格式化
		@JsonFormat(timezone = "GMT+8", pattern = " yyyy-MM-dd HH:mm:ss")
	    private Date modifyTime;
}
