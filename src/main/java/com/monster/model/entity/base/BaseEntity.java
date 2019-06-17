package com.monster.model.entity.base;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)  //开启时间类型字段设置默认值
public class BaseEntity {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

		@Column(columnDefinition = "int(1) comment '数据是否有效,用于逻辑删除'")
		private Boolean valid = true;

		@CreatedDate
		@Column(columnDefinition = "date comment '创建时间'",updatable = true, insertable = true)
		private Date createTime;

	    @LastModifiedDate
		@Column(columnDefinition = "date comment '更新时间'",updatable = true, insertable = true)
	    private Date modifyTime;
}
