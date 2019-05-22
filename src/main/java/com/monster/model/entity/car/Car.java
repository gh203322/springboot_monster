package com.monster.model.entity.car;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "car")
@org.hibernate.annotations.Table(appliesTo = "car", comment = "车辆表")
public class Car {

		@Id
		@GeneratedValue
	    private Integer id;

		@Column(columnDefinition = "varchar(10) comment='车牌号'")
		private String carNo;
		
		@Column(columnDefinition = "varchar(6) comment='省编码'")
		private String province;
		
		@Column(columnDefinition = "varchar(6) comment='市编码'")
		private String city;
		
		@Column(columnDefinition = "varchar(6) comment='经度'")
		private Double longitude;
		
		@Column(columnDefinition = "varchar(6) comment='纬度'")
		private String latitude;
		
		@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private CarUser carUser;
}