package com.monster.base;

import org.springframework.ui.Model;

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
	  
	  /**   
	 * @Title: ok   
	 * @Description: 正常返回结果  
	 * @param: @param o
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
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
	  
	  /**   
	 * @Title: error   
	 * @Description: 错误或者异常 
	 * @param: @param e
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String error(Exception e) {
		  return JSON.toJSONString(new Result(e));
	  }
	  

	/**   
	 * @Title: redirect_to_view   
	 * @Description: 重定向到页面
	 * @param: @param pageUrl  页面路径
	 * @param: @param model  参数
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String redirect_to_view (String pageUrl, Model model) {
		  
		    return "redirect:" + pageUrl;
	  }
	
	/**   
	 * @Title: redirect_to_view   
	 * @Description: 重定向到控制层
	 * @param: @param pageUrl  页面路径
	 * @param: @param model  参数
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String redirect_to_control (String requestUrl, Model model) {
		     
		     return "redirect:" + requestUrl;
	  }
	  
	/**   
	 * @Title: redirect_to_view   
	 * @Description: 转发到页面
	 * @param: @param pageUrl  页面路径
	 * @param: @param model  参数
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String forward_to_view(String pageUrl, Model model) {
		  
		   return "forward:" + pageUrl;
	  }
	
	/**   
	 * @Title: redirect_to_view   
	 * @Description: 转发到控制层
	 * @param: @param pageUrl  页面路径
	 * @param: @param model  参数
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String forward_to_control(String requestUrl, Model model) {

		   return "forward:" + requestUrl;
	  }
}
