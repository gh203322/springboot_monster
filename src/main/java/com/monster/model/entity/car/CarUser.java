package com.monster.model.entity.car;

import java.util.List;

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
@Table(name = "car_user")
public class CarUser {

	@GeneratedValue
    @Id
    private Long id;
    
    @Column(columnDefinition = "varchar(10) comment '用户名'")
    private String name;
    
    @Column(columnDefinition = "varchar(15) comment '手机号'")
    private String phone;
    
    @Column(columnDefinition = "varchar(128) comment '密码'")
    private String pass;
    
    @OneToMany(mappedBy = "carUser" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Car> cars;
	
}
