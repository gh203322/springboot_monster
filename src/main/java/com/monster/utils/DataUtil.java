package com.monster.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		   }else if(o.getClass().toString().contains("String")) {
			    if("".equals(String.valueOf(o).trim())
			    		|| "null".equals(String.valueOf(o).trim())
			    		     || "undefined".equals(String.valueOf(o).trim())
			    		    	   || "NaN".equals(String.valueOf(o).trim())) {
			    	return false;
			    }
		   }else if(o.getClass().toString().contains("HashMap")
				          || o.getClass().toString().contains("TreeMap")) {
			        if(((Map)o).isEmpty()) {
			        	return false;
			        }
		   }else if(o.getClass().toString().contains("ArrayList")
			          || o.getClass().toString().contains("LinkedList")) {
		        if(((List)o).isEmpty()) {
		        	return false;
		        }
	       }else if(o.getClass().toString().contains("HashSet")
			          || o.getClass().toString().contains("TreeSet")) {
		        if(((Set)o).isEmpty()) {
		        	return false;
		        }
	       }else if(o.getClass().toString().contains("collection")) {
	    	   if(isEmptyCollection((Collection)o)) {
	    		   return false;
	    	   }
	       }else if(o.getClass().toString().contains("Long")
		    		|| o.getClass().toString().contains("Float")
	    		     || o.getClass().toString().contains("Double")
	    		    	   || o.getClass().toString().contains("Integer")
	    		    	        || o.getClass().toString().contains("Byte")
	    		    	              || o.getClass().toString().contains("Short")) {
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
