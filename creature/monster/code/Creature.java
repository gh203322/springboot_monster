package monster.code;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.monster.utils.DataUtil;
import com.monster.utils.PropertiesUtil;

/**
 * @author Administrator
 *  简单的CRUD 模板创造器
 *  使用方式，在本项目固定的路径下，先在model内添加一个实体，并添加临时注解，只有添加了临时注解的实体才会生成相关的crud类和页面
 *  运行本类的main方法，完成后刷新工程，结束后删除掉临时注解
 */
public class Creature {

	
	   public static void main(String[] args) {
		   System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		   findCreateEntity();
	   }
	   
	   /**   
	 * @Title: findCreateEntity   
	 * @Description: 查找需要生成crud代码的实体类
	 * @param: @return      
	 * @return: File[]      
	 * @throws   
	 */
	public static File[] findCreateEntity() {
		   
			   String classPath = Creature.class.getResource("").getPath();
			   String projectPath = classPath.substring(0, classPath.indexOf("target"));
			   PropertiesUtil propertiesUtil = new PropertiesUtil(projectPath +"creature"+File.separator+"monster"+File.separator+"config.properties");
			   String value = propertiesUtil.readProperty("entityPath");
			   
			   if(DataUtil.isNotEmptyObj(value)) {
				   File entityPath = new File(projectPath+value);
				   if(DataUtil.isEmptyObj(entityPath)) {
					   return null;
				   }
				   Map<String, List<File>> files = getPathFiles(entityPath, new HashMap<String, List<File>>());
				   
				   //files.stream().forEach(file -> System.out.println(FileUtil.strExistsOfContent(file, "@Creature")));
			   }
		   return null;
	   }
	   
	   /**   
	 * @Title: getPathFiles   
	 * @Description: 获取某个路径下的所有文件
	 * @param: @param path
	 * @param: @return      
	 * @return: List<File>      
	 * @throws   
	 */
	 public static Map<String, List<File>> getPathFiles(File unknownFile, Map<String, List<File>> res) {
		   
		     if(null == unknownFile || !unknownFile.exists()) {
		    	 return null;
		     }
		     if(unknownFile.isFile()) {
		    	 List<File> list = new ArrayList<File>();
		    	 list.add(unknownFile);
		    	 res.put("", list);
		     }else if(unknownFile.isDirectory()) {
		    	 File[] files = unknownFile.listFiles();
			     for(File file: files) {
			    	   if(!file.exists()) {
						   continue;
					   }
					   if(file.isFile()) {
						     List<File> list = new ArrayList<File>();
					    	 list.add(unknownFile);
					    	 String key = getFileDirectoryName(unknownFile);
					    	 res.put(key, list);
					   }else {
						   getPathFiles(file, res);
					   }
				   }
		     }
		     
		   return res;
	   }
	 
	 /**   
	 * @Title: getParentName   
	 * @Description: 获取文件所在文件夹名称
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	 public static String getFileDirectoryName(File directory) {
		 
		  if(DataUtil.isEmptyObj(directory) || !directory.isDirectory()) {
			  return null;
		  }
		  String path = directory.getPath();
		  
		return path.substring(path.lastIndexOf("\\")+1, path.length());
	 }
	 
}
