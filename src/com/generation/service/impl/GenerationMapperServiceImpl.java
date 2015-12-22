package com.generation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generation.dao.GenerationMapperDao;
import com.generation.entity.TableForm;
import com.generation.service.IGenerationMapperService;
import com.generation.util.StringUtil;

/**
 * @Description 生成mapper.xml文件业务实现
 * @author zhangyd
 * @date 2015年12月18日 下午4:54:13
 * @version V1.0
 * @since JDK ： 1.7
 * @modify
 * @Review
 */
public class GenerationMapperServiceImpl implements IGenerationMapperService {
	private GenerationMapperDao generationMapperDao = new GenerationMapperDao();

	/**
	 * @Description 获取mybatis文件用到的元素
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:45:10
	 * @param mybatisSqlForm
	 * @return
	 */
	@Override
	public Map<String, Object> getMybatisSql(TableForm mybatisSqlForm) {
		List<String> cloumnNames = generationMapperDao.getColumnNameList(mybatisSqlForm);
		Map<String, Object> mybatisSqlMap = new HashMap<String, Object>();
		String mybatisSqlString = createMybatisSql(cloumnNames, mybatisSqlForm);
		mybatisSqlMap.put("list", mybatisSqlString);
		return mybatisSqlMap;
	}

	/**
	 * @Description 创建mapper文件
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:45:51
	 * @param cloumnNames
	 * @param mybatisSqlForm
	 * @return
	 */
	private String createMybatisSql(List<String> cloumnNames, TableForm mybatisSqlForm) {
		String TableName = mybatisSqlForm.getSelectedTableNames();
		String daoFilePath = mybatisSqlForm.getDaoFilePath();
		String entityFilePath = mybatisSqlForm.getEntityFilePath();
		List<String> propertyNames = cloumnNames2propertyNames(cloumnNames);
		StringBuilder mybatisSql = new StringBuilder();
		// 创建mybatis 头部
		StringBuilder mybatisSqlTop = createMybatisSqlTop(daoFilePath);
		mybatisSql.append(mybatisSqlTop + "<br>");
		// 创建mybatis resultMap
		StringBuilder resultMap = createResultMap(cloumnNames, propertyNames, TableName, entityFilePath);
		mybatisSql.append(resultMap + "<br><br>");
		// 创建mybatis insert
		StringBuilder insertSql = createInsertSql(cloumnNames, propertyNames, TableName);
		mybatisSql.append(insertSql + "<br><br>");
		// 创建mybatis delete
		StringBuilder deleteSql = createDeleteSql(cloumnNames, propertyNames, TableName);
		mybatisSql.append(deleteSql + "<br><br>");
		// 创建mybatis update
		StringBuilder updateSql = createUpdateSql(cloumnNames, propertyNames, TableName);
		mybatisSql.append(updateSql + "<br><br>");
		// 创建mybatis queryById
		StringBuilder queryById = createQuerySql(cloumnNames, propertyNames, TableName);
		mybatisSql.append(queryById + "<br>");
		// 创建mybatis queryAllEntityPage
		StringBuilder queryAllEntityPage = createQueryAllSql(cloumnNames, propertyNames, TableName);
		mybatisSql.append(queryAllEntityPage + "<br>");
		// 创建mybatis 尾部
		StringBuilder mybatisSqlTail = createMybatisSqlTail();
		mybatisSql.append(mybatisSqlTail);
		return mybatisSql.toString();
	}

	/**
	 * @Description 生成mybatis 文件头
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:46:10
	 * @param daoFilePath
	 * @return
	 */
	private StringBuilder createMybatisSqlTop(String daoFilePath) {
		StringBuilder sql = new StringBuilder();
		sql.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><br>");
		sql.append(
				"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" ><br>");
		sql.append("<mapper namespace=\"" + daoFilePath + "\">");
		return sql;
	}

	/**
	 * @Description 生成mybatis parameterMap
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:46:10
	 * @param cloumnNames
	 *            数据库对应的字段名集合
	 * @param propertyNames
	 *            实体类对应的字段名集合
	 * @param TableName
	 *            表名
	 * @return parameterMap
	 */
	@SuppressWarnings("unused")
	private StringBuilder createParameterMap(List<String> cloumnNames, List<String> propertyNames, String TableName,
			String entityFilePath) {
		StringBuilder sql = new StringBuilder();
		sql.append("	<parameterMap id=\"" + TableName + "ParameterMap\" type=\"" + entityFilePath + "\"><br>");
		for (int i = 0; i < cloumnNames.size(); i++) {
			sql.append("		<parameter property=\"" + propertyNames.get(i) + "\" resultMap=\"" + cloumnNames.get(i)
					+ "\"/><br>");
		}
		sql.append("	</parameterMap>");
		return sql;
	}

	/**
	 * @Description 生成mybatis resultrMap
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:46:10
	 * @param cloumnNames
	 *            数据库对应的字段名集合
	 * @param propertyNames
	 *            实体类对应的字段名集合
	 * @param TableName
	 *            表名
	 * @return resultrMap
	 */
	private StringBuilder createResultMap(List<String> cloumnNames, List<String> propertyNames, String TableName,
			String entityFilePath) {
		StringBuilder sql = new StringBuilder();
		sql.append("	<resultMap id=\"" + TableName + "ResultMap\" type=\"" + entityFilePath + "\"><br>");
		for (int i = 0; i < cloumnNames.size(); i++) {
			sql.append("		<result property=\"" + propertyNames.get(i) + "\" column=\"" + cloumnNames.get(i)
					+ "\"/><br>");
		}
		sql.append("	</resultMap>");
		return sql;
	}

	/**
	 * @Description 生成mybatis插入sql
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:46:10
	 * @param cloumnNames
	 *            数据库对应的字段名集合
	 * @param propertyNames
	 *            实体类对应的字段名集合
	 * @param TableName
	 *            表名
	 * @return mybatis插入sql
	 */
	private StringBuilder createInsertSql(List<String> cloumnNames, List<String> propertyNames, String TableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("	<insert id=\"insert\" parameterMap=\"" + TableName + "ParameterMap\"><br>");
		sql.append("		INSERT INTO " + TableName + "<br>");
		sql.append("		<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\"><br>");
		for (int i = 0; i < cloumnNames.size(); i++) {
			sql.append("			<if test=\"" + propertyNames.get(i) + " != null and " + propertyNames.get(i)
					+ " != ''\"><br>");
			sql.append("				" + cloumnNames.get(i) + ",<br>");
			sql.append("			</if><br>");
		}
		sql.append("		</trim><br>");
		sql.append("		<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\"><br>");
		for (String propertyName : propertyNames) {
			sql.append("			<if test=\"" + propertyName + " != null and " + propertyName + " != ''\"><br>");
			sql.append("				#{" + propertyName + "},<br>");
			sql.append("			</if><br>");
		}
		sql.append("		</trim><br>");
		sql.append("	</insert>");
		return sql;
	}

	/**
	 * @Description 生成mybatis更新sql
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:46:10
	 * @param cloumnNames
	 *            数据库对应的字段名集合
	 * @param propertyNames
	 *            实体类对应的字段名集合
	 * @param TableName
	 *            表名
	 * @return mybatis更新sql
	 */
	private StringBuilder createUpdateSql(List<String> cloumnNames, List<String> propertyNames, String TableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("	<update id=\"update\" parameterMap=\"" + TableName + "ParameterMap\"><br>");
		sql.append("		UPDATE " + TableName + "<br>");
		sql.append("		<set><br>");
		for (int i = 0; i < cloumnNames.size(); i++) {
			sql.append("			<if test=\"" + propertyNames.get(i) + " != null\"><br>");
			sql.append("				" + cloumnNames.get(i) + " = #{" + cloumnNames.get(i) + "},<br>");
			sql.append("			</if><br>");
		}
		sql.append("		</set><br>");
		sql.append("		WHERE " + cloumnNames.get(0) + " = #{" + propertyNames.get(0) + "}<br>");
		sql.append("	</update>");
		return sql;
	}

	/**
	 * @Description 生成mybatis删除sql
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:46:10
	 * @param cloumnNames
	 *            数据库对应的字段名集合
	 * @param propertyNames
	 *            实体类对应的字段名集合
	 * @param TableName
	 *            表名
	 * @return mybatis删除sql
	 */
	private StringBuilder createDeleteSql(List<String> cloumnNames, List<String> propertyNames, String TableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("	<delete id=\"delete\" parameterType=\"map\"><br>");
		sql.append("		DELETE FROM " + TableName + "<br>");
		sql.append("		WHERE " + cloumnNames.get(0) + " = #{" + propertyNames.get(0) + "}<br>");
		sql.append("	</delete>");
		return sql;
	}

	/**
	 * @Description 生成mybatis查询sql
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:46:10
	 * @param cloumnNames
	 *            数据库对应的字段名集合
	 * @param propertyNames
	 *            实体类对应的字段名集合
	 * @param TableName
	 *            表名
	 * @return mybatis查询sql
	 */
	private StringBuilder createQuerySql(List<String> cloumnNames, List<String> propertyNames, String TableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("	<select id=\"queryById\" resultMap=\"" + TableName + "ResultMap\"><br>");
		sql.append("		SELECT <br>");
		sql.append("		<trim suffixOverrides=\",\"><br>");
		for (int i = 0; i < cloumnNames.size(); i++) {
			sql.append("			" + cloumnNames.get(i) + ",<br>");
		}
		sql.append("		</trim><br>");
		sql.append("		FROM<br>");
		sql.append("		" + TableName + "<br>");
		sql.append("		WHERE " + cloumnNames.get(0) + " = #{" + propertyNames.get(0) + "}<br>");
		sql.append("	</select>");
		return sql;
	}

	/**
	 * @Description 生成mybatis查询sql
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:46:10
	 * @param cloumnNames
	 *            数据库对应的字段名集合
	 * @param propertyNames
	 *            实体类对应的字段名集合
	 * @param TableName
	 *            表名
	 * @return mybatis查询sql
	 */
	private StringBuilder createQueryAllSql(List<String> cloumnNames, List<String> propertyNames, String TableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("	<select id=\"queryAllEntityPage\"  parameterType=\"map\" resultMap=\"" + TableName
				+ "ResultMap\"><br>");
		sql.append("		SELECT <br>");
		sql.append("		<trim suffixOverrides=\",\"><br>");
		for (int i = 0; i < cloumnNames.size(); i++) {
			sql.append("			" + cloumnNames.get(i) + ",<br>");
		}
		sql.append("		</trim><br>");
		sql.append("		FROM<br>");
		sql.append("		" + TableName + "<br>");
		sql.append("	</select>");
		return sql;
	}

	/**
	 * @Description 生成mybatis 文件尾
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:46:10
	 * @return
	 */
	private StringBuilder createMybatisSqlTail() {
		StringBuilder sql = new StringBuilder();
		sql.append("</mapper>");
		return sql;
	}

	/**
	 * @Description 将数据库字段转化为实体类字段
	 * @author zhangyd
	 * @date 2015年12月9日 上午9:46:10
	 * @param cloumnNames
	 *            数据库字段集合
	 * @return 实体类字段集合
	 */
	private List<String> cloumnNames2propertyNames(List<String> cloumnNames) {
		List<String> propertyNames = new ArrayList<String>();
		for (String cloumnName : cloumnNames) {
			if (cloumnName.indexOf('_') > 0) {
				String[] temps = cloumnName.split("_");
				cloumnName = temps[0];
				for (int i = 1; i < temps.length; i++) {
					cloumnName += StringUtil.initialtoUpper(temps[i]);
				}
			}
			propertyNames.add(cloumnName);
		}
		return propertyNames;
	}
}
