package com.monster.model.entity.car;

import com.monster.model.entity.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "car_location")
@org.hibernate.annotations.Table(appliesTo = "car_location", comment = "车辆轨迹表")
public class CarLocation  extends BaseEntity {

	   @Column(columnDefinition = "datetime NOT NULL default CURRENT_TIMESTAMP comment '创建时间'")
	   private Date createTime;
	   
	   @Column(columnDefinition = "int(11) NOT NULL comment '创建时间时间戳秒'")
	   private Long createTimeStamp;
	   
	   @Column(columnDefinition = "varchar(10) NOT NULL comment '车牌号'")
	   private String carNo;
	   
	   @Column(columnDefinition = "double(9,6) comment '经度'")
	   private Double longitude;
		
	   @Column(columnDefinition = "double(9,6) comment '纬度'")
	   private Double latitude;
}
