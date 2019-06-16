package com.monster.creature;

import com.monster.utils.*;
import java.io.File;
import java.util.*;

/**
 * @author Administrator
 *  简单的CRUD 模板创造器
 *  使用方式，在本项目固定的路径下，先在model内添加一个实体，并添加临时注解，只有添加了临时注解的实体才会生成相关的crud类和页面
 *  运行本类的main方法，完成后刷新工程，结束后删除掉临时注解
 */
public class Creature {

		public static void main(String[] args) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>创建前端代码文件开始>>>>>>>>>>>>>>>>>>>>>>>");
			 createWebContent();
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>创建前端代码文件结束>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>创建后端代码文件开始>>>>>>>>>>>>>>>>>>>>>>>");
			 createServicContent();
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>创建后端代码文件结束>>>>>>>>>>>>>>>>>>>>>>>");
		}

	/**
	 * 生成后端文件
	 */
	public static void createServicContent() {

		try {
			PropertiesUtil propertiesUtil = new PropertiesUtil("creature.properties");
			Map<String, List<File>> target = findCreateEntity();
			Iterator<Map.Entry<String, List<File>>> iteratorMap = target.entrySet().iterator();
			while(iteratorMap.hasNext()){
				Map.Entry<String, List<File>> entry = iteratorMap.next();
				List<File> list = entry.getValue();
				Iterator<File> iteratorList = list.listIterator();
				while (iteratorList.hasNext()){
					File file = iteratorList.next();

					//controller
					String controllerPathIn = propertiesUtil.readProperty("controllerPathIn");
					String controllerPathOut = propertiesUtil.readProperty("controllerPathOut");
					createCore(controllerPathIn,controllerPathOut, entry.getKey(),file,"Controller");

					//service
					String servicePathIn = propertiesUtil.readProperty("servicePathIn");
					String servicePathOut = propertiesUtil.readProperty("servicePathOut");
					createCore(servicePathIn,servicePathOut, entry.getKey(),file,"Service");

					//serviceImpl
					String serviceImplPathIn = propertiesUtil.readProperty("serviceImplPathIn");
					String serviceImplPathOut = propertiesUtil.readProperty("serviceImplPathOut");
					createCore(serviceImplPathIn,serviceImplPathOut, entry.getKey(),file,"ServiceImpl");

					//repository
					String repositoryPathIn = propertiesUtil.readProperty("repositoryPathIn");
					String repositoryPathOut = propertiesUtil.readProperty("repositoryPathOut");
					createCore(repositoryPathIn,repositoryPathOut, entry.getKey(),file,"Repository");

					//search
					String searchPathIn = propertiesUtil.readProperty("searchPathIn");
					String searchPathOut = propertiesUtil.readProperty("searchPathOut");
					createCore(searchPathIn,searchPathOut, entry.getKey(),file,"Search");

					//vo
					String voPathIn = propertiesUtil.readProperty("voPathIn");
					String voPathOut = propertiesUtil.readProperty("voPathOut");
					createCore(voPathIn,voPathOut, entry.getKey(),file,"Vo");

					com.monster.model.entity.car.CarUser carUser = new com.monster.model.entity.car.CarUser();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成前端文件
	 */
	public static void createWebContent() {

		try {
			Map<String, List<File>> target = findCreateEntity();
			Iterator<Map.Entry<String, List<File>>> iteratorMap = target.entrySet().iterator();
			while(iteratorMap.hasNext()){
				Map.Entry<String, List<File>> entry = iteratorMap.next();
				List<File> list = entry.getValue();
				Iterator<File> iteratorList = list.listIterator();
				while (iteratorList.hasNext()){
					File file = iteratorList.next();
					//获取模板html模板集合
					PropertiesUtil propertiesUtil = new PropertiesUtil("creature.properties");
					String leafPathIn = propertiesUtil.readProperty("leafPathIn");
					String leafPathOut = propertiesUtil.readProperty("leafPathOut");
					//如果键为空或者特殊标记，不用创建上层文件夹
					File[] leafs = new File(getProjectPath() + leafPathIn).listFiles();
					File folder = null;
					if(DataUtil.isNotEmptyObj(entry.getKey())){
						folder = new File(getProjectPath() + leafPathOut + File.separator+FileUtil.getLowCaseFileName(file)+File.separator);
						if(!folder.exists()){
							folder.mkdirs();
						}
					}
					final File pointFolder = folder;
					if(DataUtil.isNotEmptyObj(folder)){
						Arrays.stream(leafs).forEach(f->{
									//System.out.println(f.getName());
									File leafNew = new File(pointFolder.getPath()+File.separator+f.getName().replaceAll("mdl", "html"));
									if(leafNew.exists()){
										 return;
									}else{
										try {
											leafNew.createNewFile();
											FileUtil.createHtmlContent(f,leafNew,FileUtil.getLowCaseFileName(file),"\\$\\{(.*?)\\}");
										}catch (Exception e){}
									}
								}
						);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	   /**   
	 * @Title: findCreateEntity   
	 * @Description: 查找需要生成crud代码的实体类
	 * @param: @return      
	 * @return: File[]      
	 * @throws   
	 */
	public static Map<String, List<File>> findCreateEntity() {
		   
		       PropertiesUtil propertiesUtil = new PropertiesUtil("creature.properties");
			   String value = propertiesUtil.readProperty("entityPath");
			   
			   if(DataUtil.isNotEmptyObj(value)) {
				   File entityPath = new File(getProjectPath()+value);
				   if(DataUtil.isEmptyObj(entityPath)) {
					   return null;
				   }
				   Map<String, List<File>> files = getPathFiles(entityPath, new HashMap<String, List<File>>());

				   Iterator<Map.Entry<String, List<File>>> iteratorMap = files.entrySet().iterator();
				   while(iteratorMap.hasNext()){
					   Map.Entry<String, List<File>> entry = iteratorMap.next();
					   List<File> list = entry.getValue();
					   Iterator<File> iteratorList = list.listIterator();
					   while (iteratorList.hasNext()){
						   File file = iteratorList.next();
						   if(!FileUtil.strExistsOfContent(file, "@CreatureAno")){
							   iteratorList.remove();
						   }
					   }
				   }
				   return files;
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
						     String key = getFileDirectoryName(unknownFile);
						     List<File> list = res.get(key);
						     if(DataUtil.isEmptyObj(list)){
								 list = new ArrayList<File>();
							 }
					    	 list.add(file);
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

	/**
	 *
	 * @return
	 */
	public static String getProjectPath(){
		 String classPath = Creature.class.getResource("").getPath();;
		 String projectPath =  classPath.substring(0, classPath.indexOf("target"));

		 return projectPath;
	 }

	/**
	 * 根据配置文件创建文件
	 */
	public static void createCore(String pathIn, String patOut, String folderName, File file, String floor){
		File fileIn = new File(pathIn);
		File fileOut = null;
		//如果键为空或者特殊标记，不用创建上层文件夹
		File folder = null;
		if(DataUtil.isNotEmptyObj(folderName)){
			folder = new File(getProjectPath() + patOut + File.separator+FileUtil.getLowCaseFileName(folderName)+File.separator);
			if(!folder.exists()){
				folder.mkdirs();
			}
		}
		fileOut = new File(folder.getPath()+File.separator+FileUtil.getUperCaseFileName(file)+floor+".java");
		if(fileOut.exists()){
			return;
		}else{
			try {
				fileOut.createNewFile();
				FileUtil.createServiceContent(fileIn,fileOut,FileUtil.getLowCaseFileName(folderName),FileUtil.getUperCaseFileName(file));
			}catch (Exception e){}
		}
	}

}
