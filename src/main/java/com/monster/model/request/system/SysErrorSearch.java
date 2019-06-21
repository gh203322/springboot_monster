package com.monster.model.request.system;	
	
import com.monster.model.entity.system.SysError;
import lombok.Data;
	
/**	
 * @author Administrator	
 *  实体对应的查询数据封装类	
 */	
@Data	
public class SysErrorSearch extends SysError {	

        //创建时间的字符串形式接收(配合BaseDate 可变长和格式)
      	private String createTimeStr;
}	
