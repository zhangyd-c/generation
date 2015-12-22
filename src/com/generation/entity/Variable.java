/**
 * glodon.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 * com.xjyh.entity
 */
package com.generation.entity;

import com.generation.util.StringUtil;

/**
 * @Description java实体属性
 * @author zhangyd
 * @date 2015年12月18日 下午5:19:35
 * @version V1.0
 * @since JDK ： 1.7
 * @modify
 * @Review
 */
public class Variable {
	// 字段名称
	private String columnName;
	// 字段对应实体属性名
	private String propertyNames;
	// 字段类型
	private String type;
	// 数据库中对字段的备注
	private String comment;
	// 是否特殊字段，例如某些唯一约束、主键约束或者外键约束限制的字段
	private String columnKey;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
		String[] temps = columnName.split("_");
		columnName = temps[0];
		for (int i = 1; i < temps.length; i++) {
			columnName += StringUtil.initialtoUpper(temps[i]);
		}
		setPropertyNames(columnName);
	}

	public String getPropertyNames() {
		return propertyNames;
	}

	private void setPropertyNames(String propertyNames) {
		this.propertyNames = propertyNames;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

}
