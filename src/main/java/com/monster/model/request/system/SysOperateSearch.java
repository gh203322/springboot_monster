package com.monster.model.request.system;	
	
import com.monster.model.entity.system.SysOperate;
import lombok.Data;
	
/**	
 * @author Administrator	
 *  实体对应的查询数据封装类	
 */	
@Data	
public class SysOperateSearch extends SysOperate {	

    //接收创建时间的字符串
    private String createTimeStr;

}
