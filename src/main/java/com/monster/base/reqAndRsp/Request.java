package com.monster.base.reqAndRsp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author Administrator
 *  通用请求类
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)//忽略未知属性
public class Request<T> {
	  //前端通过json传值,批量传递主键 key 必须与之对应为 ids
	  private List<T> ids;
	  
}
