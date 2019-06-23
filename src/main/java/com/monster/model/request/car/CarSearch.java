package com.monster.model.request.car;

import com.monster.model.entity.car.Car;
import lombok.Data;

/**
 * @author Administrator
 *  实体对应的查询数据封装类
 */
@Data
public class CarSearch extends Car {
   
    //多个车主接收参数
    private Long[] carUsers;
}
