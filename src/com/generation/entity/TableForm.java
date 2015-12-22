/**
 * glodon.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 * com.xjyh.entity.form
 */
package com.generation.entity;

/**
 * @Description 生成java、mapper.xml基本属性类
 * @author zhangyd
 * @date 2015年12月18日 下午5:19:54
 * @version V1.0
 * @since JDK ： 1.7
 * @modify
 * @Review
 */
public class TableForm {

	// 数据库地址
	private String dbAddress;
	// 数据库用户名
	private String dbLoginName;
	// 数据库密码
	private String dbPassword;
	// 选择的数据库
	private String selectedDbNames;
	// 选择的表名
	private String selectedTableNames;
	// dao文件路径
	private String daoFilePath;
	// entity文件路径
	private String entityFilePath;
	// entity包名
	private String entityPackage;

	public TableForm() {
		super();
	}

	public TableForm(String dbAddress, String dbLoginName, String dbPassword, String selectedDbNames,
			String selectedTableNames, String daoFilePath, String entityFilePath, String entityPackage) {
		super();
		this.dbAddress = dbAddress;
		this.dbLoginName = dbLoginName;
		this.dbPassword = dbPassword;
		this.selectedDbNames = selectedDbNames;
		this.selectedTableNames = selectedTableNames;
		this.daoFilePath = daoFilePath;
		this.entityFilePath = entityFilePath;
		this.entityPackage = entityPackage;
	}

	public String getDbAddress() {
		return dbAddress;
	}

	public void setDbAddress(String dbAddress) {
		this.dbAddress = dbAddress;
	}

	public String getDbLoginName() {
		return dbLoginName;
	}

	public void setDbLoginName(String dbLoginName) {
		this.dbLoginName = dbLoginName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getSelectedDbNames() {
		return selectedDbNames;
	}

	public void setSelectedDbNames(String selectedDbNames) {
		this.selectedDbNames = selectedDbNames;
	}

	public String getSelectedTableNames() {
		return selectedTableNames;
	}

	public void setSelectedTableNames(String selectedTableNames) {
		this.selectedTableNames = selectedTableNames;
	}

	public String getDaoFilePath() {
		return daoFilePath;
	}

	public void setDaoFilePath(String daoFilePath) {
		if (null == daoFilePath || "".equals(daoFilePath))
			daoFilePath = "DAO层";
		this.daoFilePath = daoFilePath;
	}

	public String getEntityFilePath() {
		return entityFilePath;
	}

	public void setEntityFilePath(String entityFilePath) {
		if (null == entityFilePath || "".equals(entityFilePath))
			entityFilePath = "实体类";
		this.entityFilePath = entityFilePath;
	}

	public String getEntityPackage() {
		return entityPackage;
	}

	public void setEntityPackage(String entityPackage) {
		if (null == entityPackage || "".equals(entityPackage))
			entityPackage = "com.xjyh.entity";
		this.entityPackage = entityPackage;
	}
}
