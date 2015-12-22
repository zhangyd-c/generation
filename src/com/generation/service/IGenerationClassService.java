
/**   
 * @Title: IGenerationClassService.java 
 * @Package: com.maven.web.service.generation 
 * @Description: TODO
 * @author zhangyd
 * @date 2015年12月9日 上午10:38:28 
 * @version 1.0 
 */

package com.generation.service;

import java.util.List;
import java.util.Map;

import com.generation.entity.TableForm;
import com.generation.entity.Variable;

/**
 * @Description
 * @author zhangyd
 * @date 2015年12月9日 上午10:38:28
 * @version V1.0
 * @since JDK ： 1.7
 * @modify
 * @Review
 */

public interface IGenerationClassService {

	/**
	 * @Description 生成java实体
	 * @author zhangyd
	 * @date 2015年12月9日 上午10:11:17
	 * @param tableForm
	 * @return
	 */
	public Map<String, Object> getJava(TableForm tableForm);

	/**
	 * @Description 创建java实体字符串
	 * @author zhangyd
	 * @date 2015年12月9日 上午10:11:22
	 * @param tableInfo
	 * @param tableForm
	 * @return
	 */
	public String createJava(List<Variable> tableInfo, TableForm tableForm);

	/**
	 * @Description 创建包信息
	 * @author zhangyd
	 * @date 2015年12月9日 上午10:11:28
	 * @param packagePath
	 * @return
	 */
	public String createPackage(String packagePath);

	/**
	 * @Description 创建导入信息
	 * @author zhangyd
	 * @date 2015年12月9日 上午10:11:33
	 * @return
	 */
	public String createImport();

	/**
	 * @Description 创建class名
	 * @author zhangyd
	 * @date 2015年12月9日 上午10:11:39
	 * @param tableName
	 * @return
	 */
	public String createClass(String tableName);

	/**
	 * @Description 创建变量
	 * @author zhangyd
	 * @date 2015年12月9日 上午10:11:55
	 * @param tableInfo
	 * @return
	 */
	public String createVariables(List<Variable> tableInfo);

	/**
	 * @Description 创建set、get方法
	 * @author zhangyd
	 * @date 2015年12月9日 上午10:12:02
	 * @param tableInfo
	 * @return
	 */
	public String createSetAndGet(List<Variable> tableInfo);

	/**
	 * @Description 创建class尾部
	 * @author zhangyd
	 * @date 2015年12月9日 上午10:12:09
	 * @return
	 */
	public String createTail();

}
