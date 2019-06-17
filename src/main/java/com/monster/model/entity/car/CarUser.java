package com.monster.model.entity.car;

import com.monster.base.annotation.CreatureAno;
import com.monster.model.entity.base.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@CreatureAno
@Entity
@Data
@Table(name = "car_user")
@org.hibernate.annotations.Table(appliesTo = "car_user", comment = "车辆用户表")
public class CarUser  extends BaseEntity {

    @Column(columnDefinition = "varchar(10) comment '用户名'")
    private String name;
    
    @Column(columnDefinition = "varchar(15) comment '手机号'")
    private String phone;
    
    @Column(columnDefinition = "varchar(128) comment '密码'")
    private String pass;
    
    @OneToMany(
    		fetch = FetchType.LAZY,
    		mappedBy = "carUser" 
    )
    private List<Car> cars;

    /* 解决序列化循环引用问题,在这里截断关系 */
	public List<Car> getCars() {
		if(null != cars) {
			cars.stream().forEach(car -> car.setCarUser(null));
		}else {
			cars = new ArrayList<Car>();
		}
		return cars;
	}

	/*
	* 拥有车辆数
	* */
    public Integer getCarNums() {
        return getCars().size();
    }
	
}
