package com.generation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.generation.entity.TableForm;
import com.generation.entity.Variable;

/**
 * @Description 获取数据库操作
 * @author zhangyd
 * @date 2015年12月9日 上午10:17:22
 * @version V1.0
 * @since JDK ： 1.7
 * @modify
 * @Review
 */
public class GenerationMapperDao {

	/**
	 * @Description 获取数据库列表
	 * @author zhangyd
	 * @date 2015年12月9日 上午10:17:33
	 * @param mybatisSqlForm
	 * @return
	 */
	public List<String> getDbNameList(TableForm mybatisSqlForm) {
		List<String> list = new ArrayList<String>();
		ResultSet rs = null;
		Connection conn = null;
		Statement statement = null;
		try {
			String sql = "show databases";
			rs = getResultSet(conn, statement, mybatisSqlForm.getDbAddress(), mybatisSqlForm.getDbLoginName(),
					mybatisSqlForm.getDbPassword(), sql.toString());
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @Description 获取数据库表列表
	 * @author zhangyd
	 * @date 2015年12月9日 上午10:17:46
	 * @param mybatisSqlForm
	 * @return
	 */
	public List<String> getTableNameList(TableForm mybatisSqlForm) {
		List<String> list = new ArrayList<String>();
		ResultSet rs = null;
		Connection conn = null;
		Statement statement = null;
		try {
			String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '"
					+ mybatisSqlForm.getSelectedDbNames() + "'";
			rs = getResultSet(conn, statement, mybatisSqlForm.getDbAddress(), mybatisSqlForm.getDbLoginName(),
					mybatisSqlForm.getDbPassword(), sql.toString());
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @Description 获取字段名 列表
	 * @author zhangyd
	 * @date 2015年12月9日 上午10:18:07
	 * @param mybatisSqlForm
	 * @return
	 */
	public List<String> getColumnNameList(TableForm mybatisSqlForm) {
		List<String> columnNames = new ArrayList<String>();
		ResultSet rs = null;
		Connection conn = null;
		Statement statement = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT DISTINCT column_name cloumnName,data_type type,column_comment comment,column_key FROM Information_schema.COLUMNS ");
			sql.append("WHERE TABLE_NAME = '" + mybatisSqlForm.getSelectedTableNames() + "' and TABLE_SCHEMA = '"
					+ mybatisSqlForm.getSelectedDbNames() + "'");
			rs = getResultSet(conn, statement, mybatisSqlForm.getDbAddress(), mybatisSqlForm.getDbLoginName(),
					mybatisSqlForm.getDbPassword(), sql.toString());
			while (rs.next()) {
				columnNames.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return columnNames;
	}

	/**
	 * @Description 获取数据表详情
	 * @author zhangyd
	 * @date 2015年12月9日 上午10:19:19
	 * @param mybatisSqlForm
	 * @return
	 */
	public List<Variable> getTableInfo(TableForm mybatisSqlForm) {
		List<Variable> tableInfo = new ArrayList<Variable>();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT DISTINCT column_name cloumnName,data_type type,column_comment comment,column_key FROM Information_schema.COLUMNS ");
			sql.append("WHERE TABLE_NAME = '" + mybatisSqlForm.getSelectedTableNames() + "' and TABLE_SCHEMA = '"
					+ mybatisSqlForm.getSelectedDbNames() + "'");
			rs = getResultSet(conn, statement, mybatisSqlForm.getDbAddress(), mybatisSqlForm.getDbLoginName(),
					mybatisSqlForm.getDbPassword(), sql.toString());
			while (rs.next()) {
				Variable variable = new Variable();
				variable.setColumnName(rs.getString("cloumnName"));
				variable.setType(rs.getString("type"));
				variable.setComment(rs.getString("comment"));
				variable.setColumnKey(rs.getString("column_key"));
				tableInfo.add(variable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tableInfo;
	}

	/**
	 * @Description 请求数据库链接，获取返回结果集
	 * @author zhangyd
	 * @date 2015年12月9日 上午10:32:22
	 * @param dbAddress
	 * @param userName
	 * @param passWord
	 * @param sql
	 * @return
	 */
	public ResultSet getResultSet(Connection conn, Statement statement, String dbAddress, String userName,
			String passWord, String sql) {
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(getDbUrl(dbAddress), userName, passWord);
			statement = conn.createStatement();
			rs = statement.executeQuery(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public String getDbUrl(String dbAddress) {
		return "jdbc:mysql://" + dbAddress + "?useUnicode=true&characterEncoding=utf-8";
	}

}
