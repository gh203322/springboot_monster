package com.monster.utils;

import java.util.*;

/**
 * @author Administrator
 * 自定义数据工具
 */
public class DataUtil {

	 /**   
	 * @Title: isNotEmptyObj   
	 * @Description: 是否不是空对象
	 * @param: @param o
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean isNotEmptyObj(Object o) {
		 
		   if(null == o) {
			   return false;
		   }
		   String classType = o.getClass().toString();
		   if(classType.contains("java.lang.String")) {
			    if("".equals(String.valueOf(o).trim())
			    		|| "null".equals(String.valueOf(o).trim())
			    		     || "undefined".equals(String.valueOf(o).trim())
			    		    	   || "NaN".equals(String.valueOf(o).trim())) {
			    	return false;
			    }
		   }else if(classType.contains("java.util.")){
			   if(classType.contains("HashMap")
					   || classType.contains("TreeMap")) {
				   if(((Map)o).isEmpty()) {
					   return false;
				   }
			   }else if(classType.contains("ArrayList")
					   || classType.contains("LinkedList")) {
				   if(((List)o).isEmpty()) {
					   return false;
				   }
			   }else if(classType.contains("HashSet")
					   || classType.contains("TreeSet")) {
				   if(((Set)o).isEmpty()) {
					   return false;
				   }
			   }
		   }else if(classType.contains("collection")) {
	    	   if(isEmptyCollection((Collection)o)) {
	    		   return false;
	    	   }
	       }else if(classType.contains("Ljava.lang.")){
		   	   if(classType.contains("String")){
				   if(((String[])o).length<1){
				   	  return false;
				   }
			   }else if(classType.contains("Long")){
				   if(((Long[])o).length<1){
					   return false;
				   }
			   }else if(classType.contains("Float")){
				   if(((String[])o).length<1){
					   return false;
				   }
			   }else if(classType.contains("Double")){
				   if(((Double[])o).length<1){
					   return false;
				   }
			   }else if(classType.contains("Integer")){
				   if(((Integer[])o).length<1){
					   return false;
				   }
			   }else if(classType.contains("Byte")){
				   if(((Byte[])o).length<1){
					   return false;
				   }
			   }else if(classType.contains("Short")){
				   if(((Short[])o).length<1){
					   return false;
				   }
			   }
		   }else if(classType.contains("java.lang.Long")
		    		|| classType.contains("java.lang.Float")
	    		     || classType.contains("java.lang.Double")
	    		    	   || classType.contains("java.lang.Integer")
	    		    	        || classType.contains("java.lang.Byte")
	    		    	              || classType.contains("java.lang.Short")) {
		        if(new Double(0).equals(Double.parseDouble(o.toString()))) {
		        	return false;
		        }
            }
		   return true;
	 }
	 
	 /**   
	 * @Title: isEmptyObj   
	 * @Description: 是否是空对象
	 * @param: @param o
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	public static boolean isEmptyObj(Object o) {
		 return !isNotEmptyObj(o);
	 }
	
	 /**   
	 * @Title: isNotEmptyObj   
	 * @Description: 是否不是空对象组
	 * @param: @param o
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	public static boolean isNotEmptyObjs(Object... os) {
		 
		   for(Object o: os) {
			   if(isEmptyObj(o)) {
				   return false; 
			   }
		   }
		   
		  return true;
	}
	
	 /**   
	 * @Title: isNotEmptyObj   
	 * @Description: 是否是空对象组
	 * @param: @param o
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	public static boolean isEmptyObjs(Object... os) {
		 
		   for(Object o: os) {
			   if(isNotEmptyObj(o)) {
				   return false; 
			   }
		   }
		   
		  return true;
	}
		
	/**   
	 * @Title: isEmptyCollection   
	 * @Description: 不是空集合  
	 * @param: @param <T>
	 * @param: @param datas
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	private static <T> boolean isEmptyCollection(Collection<T> datas) {
        return datas == null || datas.isEmpty();
    }
}
