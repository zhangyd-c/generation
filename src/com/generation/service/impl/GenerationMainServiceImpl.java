
/**   
 * @Title: GenerationClassController.java 
 * @Package: com.maven.web.controller.generation 
 * @Description: TODO
 * @author zhangyd
 * @date 2015年12月8日 下午12:55:23 
 * @version 1.0 
 */

package com.generation.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.generation.dao.GenerationMapperDao;
import com.generation.entity.TableForm;
import com.generation.service.IGenerationMainService;

/**
 * @Description 生成java、mapper.xml文件通用业务实现
 * @author zhangyd
 * @date 2015年12月18日 下午4:54:13
 * @version V1.0
 * @since JDK ： 1.7
 * @modify
 * @Review
 */
public class GenerationMainServiceImpl implements IGenerationMainService {

	private GenerationMapperDao generationMapperDao = new GenerationMapperDao();

	/**
	 * @Description 获取数据库名字集合
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:43:55
	 * @param mybatisSqlForm
	 * @return
	 */
	@Override
	public List<String> getDbNameList(TableForm mybatisSqlForm) {
		return generationMapperDao.getDbNameList(mybatisSqlForm);
	}

	/**
	 * @Description 获取数据表集合
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:44:53
	 * @param mybatisSqlForm
	 * @return
	 */
	@Override
	public List<String> getTableNameList(TableForm mybatisSqlForm) {
		return generationMapperDao.getTableNameList(mybatisSqlForm);
	}
	
	/**
	 * @Description 接受前台传递的参数，并且返回实体
	 * @author zhangyd
	 * @date 2015年12月22日 下午5:13:08 
	 * @param request
	 * @return
	 */
	@Override
	public TableForm getTableForm(HttpServletRequest request){
		String dbAddress = request.getParameter("dbAddress");
		String dbLoginName = request.getParameter("dbLoginName");
		String dbPassword = request.getParameter("dbPassword");
		String selectedDbNames = request.getParameter("selectedDbNames");
		String selectedTableNames = request.getParameter("selectedTableNames");
		String daoFilePath = request.getParameter("daoFilePath");
		String entityFilePath = request.getParameter("entityFilePath");
		String entityPackage = request.getParameter("entityPackage");
		TableForm sqlForm = new TableForm(dbAddress, dbLoginName, dbPassword, selectedDbNames, selectedTableNames,
				daoFilePath, entityFilePath, entityPackage);
		return sqlForm;
	}

}
