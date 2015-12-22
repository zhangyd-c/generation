
/**   
 * @Title: IGenerationMapperService.java 
 * @Package: com.maven.web.service.generation 
 * @Description: TODO
 * @author zhangyd
 * @date 2015年12月9日 上午10:45:09 
 * @version 1.0 
 */

package com.generation.service;

import java.util.Map;

import com.generation.entity.TableForm;

/**
 * @Description
 * @author zhangyd
 * @date 2015年12月9日 上午10:45:09
 * @version V1.0
 * @since JDK ： 1.7
 * @modify
 * @Review
 */

public interface IGenerationMapperService {
	/**
	 * @Description 获取mybatis文件用到的元素
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:45:10
	 * @param mybatisSqlForm
	 * @return
	 */
	public Map<String, Object> getMybatisSql(TableForm mybatisSqlForm);
}
