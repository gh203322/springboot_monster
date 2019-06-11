package com.monster.model.entity.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "car")
@org.hibernate.annotations.Table(appliesTo = "car", comment = "车辆表")
public class Car {

		@Id
		@GeneratedValue
	    private Integer id;

		@Column(columnDefinition = "varchar(10) comment '车牌号'")
		private String carNo;
		
		@Column(columnDefinition = "varchar(6) comment '省编码'")
		private String province;
		
		@Column(columnDefinition = "varchar(6) comment '市编码'")
		private String city;
		
		@Column(columnDefinition = "double(9,6) comment '经度'")
		private Double longitude;
		
		@Column(columnDefinition = "double(9,6) comment '纬度'")
		private Double latitude;
		
		@Column(columnDefinition = "date default sysdate() comment '车辆登记时间'")
		@DateTimeFormat(pattern  = "yyyy-MM-dd")
		private Date signDate;
		
		@ManyToOne(fetch = FetchType.LAZY, optional = true)//optional = true 可为空
		@JoinColumn(name = "userId")
		private CarUser carUser;

	    /* 解决序列化循环引用问题,在这里截断关系 */
		public CarUser getCarUser() {
			if(null != carUser) {
				carUser.setCars(null);
			}
			return carUser;
		}

}
