
/**   
 * @Title: IGenerationMainService.java 
 * @Package: com.maven.web.service.generation 
 * @Description: TODO
 * @author zhangyd
 * @date 2015年12月9日 上午10:43:42 
 * @version 1.0 
 */

package com.generation.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.generation.entity.TableForm;

/**
 * @Description
 * @author zhangyd
 * @date 2015年12月9日 上午10:43:42
 * @version V1.0
 * @since JDK ： 1.7
 * @modify
 * @Review
 */

public interface IGenerationMainService {
	/**
	 * @Description 获取数据库名字集合
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:43:55
	 * @param mybatisSqlForm
	 * @return
	 */
	public List<String> getDbNameList(TableForm mybatisSqlForm);

	/**
	 * @Description 获取数据表集合
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:44:53
	 * @param mybatisSqlForm
	 * @return
	 */
	public List<String> getTableNameList(TableForm mybatisSqlForm);

	/**
	 * @Description 接受前台传递的参数，并且返回实体
	 * @author zhangyd
	 * @date 2015年12月22日 下午5:13:08 
	 * @param request
	 * @return
	 */
	public TableForm getTableForm(HttpServletRequest request);
}
