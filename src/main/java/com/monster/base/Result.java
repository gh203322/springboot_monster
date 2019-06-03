package com.monster.base;

import com.alibaba.fastjson.JSON;
import com.monster.utils.DataUtil;

import lombok.Data;

/**
 * @author Administrator
 * 通用结果返回类
 */
@Data
public class Result {

	  private boolean state; //状态 true-成功 false-失败
	  private String msg;  //返回消息
	  private Object data;  //返回数据内容
	  private String exeptionMsg;  //异常信息
	  
	  Result(){
		  
	  }
	  
	  Result(Object o){
		  this.data = o;
		  if(DataUtil.isNotEmptyObj(o)) {
			  this.state = true;
			  this.msg = "操作成功";
		  }else {
			  this.state = false;
			  this.msg = "操作失败"; 
		  }
		  
	  }	  
	  
      Result(boolean state, String msg){
		  this.state = state;
		  this.msg = msg;
	  }

	  Result(boolean state, Object o){
		  this.state = state;
		  this.data = o;
		  if(state) {
			  this.msg = "操作成功";
		  }else {
			  this.msg = "操作失败";
		  }
	  }
	  
	  Result(boolean state, Object o, String msg){
		  this.state = state;
		  this.msg = msg;
		  this.data = o;
	  }
	  
      Result(Exception e){
    	  this.state = false;
    	  this.msg = "操作异常";
		  this.exeptionMsg = "异常信息：" + JSON.toJSONString(e);
	  }
	  
	  public static String ok(Object o) {
		  return JSON.toJSONString(new Result(o));
	  }
	  
	  public static String ok(boolean state, String msg) {
		  return JSON.toJSONString(new Result(state, msg));
	  }
	  
	  public static String ok(boolean state, Object o) {
		  return JSON.toJSONString(new Result(state, o));
	  }
	  
	  public static String ok(boolean state, Object o, String msg) {
		  return JSON.toJSONString(new Result(state, o, msg));
	  }
	  
	  public static String error(Exception e) {
		  return JSON.toJSONString(new Result(e));
	  }
}
