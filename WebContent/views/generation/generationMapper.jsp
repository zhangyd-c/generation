<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Generat Mapper.xml</title>
<c:import url="../common.jsp"></c:import>
<link type="text/css" rel="stylesheet" href="/javascripts/syntaxhighlighter/other/104486.css">
<link type="text/css" rel="stylesheet" href="/javascripts/syntaxhighlighter/styles/shCore.css" />
<link type="text/css" rel="stylesheet" href="/javascripts/syntaxhighlighter/styles/shThemeDefault.css" />

</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">生成mybatis 的mapper文件</div>
		<div class="panel-body">
			<form id="form" action="/generation/exportXML" method="get">
				<div id="generatClassOne">
					<div id="step1">
						<div class="form-group">
							<label for="dbAddress">数据库地址<font color="red"><strong>(必填)</strong></font>：</label> 
							<input class="form-control" name="dbAddress" id="dbAddress" value="127.0.0.1">
						</div>
						<div class="form-group">
							<label for="dbLoginName">登录名<font color="red"><strong>(必填)</strong></font>：</label> 
							<input class="form-control" name="dbLoginName" id="dbLoginName" placeholder="请输入登录名" value="root">
						</div>
						<div class="form-group">
							<label for="dbPassword">密码<font color="red"><strong>(必填)</strong></font>：</label> 
							<input class="form-control" name="dbPassword" id="dbPassword" placeholder="请输入密码" type="password" value="root">
						</div>
						<a class="btn btn-info" id="next1">下一步</a>
					</div>
					<div id="step2" style="display: none;">
						<div class="form-group">
							<label for="selectedDbNames">请选择数据库<font color="red"><strong>(必填)</strong></font>：</label> 
							<select name='selectedDbNames' id="selectedDbNames" class="form-control form-control-new">
							</select>
						</div>
						<a class="btn btn-info" id="next2">下一步</a>
						<a class="btn btn-default" id="goHistory1" style="margin-left: 10px">上一步</a>
					</div>
					<div id="step3" style="display: none;">
						<div class="form-group">
							<label for="selectedDbNames">请选择数据表<font color="red"><strong>(必填)</strong></font>：</label> 
							<select name='selectedTableNames' id="selectedTableNames" class="form-control form-control-new">
							</select>
						</div>
						<div class="form-group">
							<label for="entityPackage">DAO层路径：<font color="red"><strong>(必填)</strong></font>：</label> 
							<input class="form-control" name="daoFilePath" value="com.maven.web.dao" id="daoFilePath" placeholder="请输入包名">
							<label for="entityPackage">实体类路径：<font color="red"><strong>(必填)</strong></font>：</label> 
							<input class="form-control" name="entityFilePath" value="com.maven.web.entity" id="entityFilePath" placeholder="请输入包名">
						</div>
						<a class="btn btn-info" id="generatMapper">生成</a> 
						<a class="btn btn-default" id="goHistory2" style="margin-left: 10px">上一步</a>
						<p class="help-block">	可以点“<strong>生成</strong>”后复制</p>
					</div>
					<div id="showCode" style="display: none">
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="/javascripts/syntaxhighlighter/scripts/XRegExp.js"></script>
	<script type="text/javascript" src="/javascripts/syntaxhighlighter/scripts/shCore.js"></script>
	<script type="text/javascript" src="/javascripts/syntaxhighlighter/scripts/shBrushJava.js"></script>
	<script type="text/javascript" src="/javascripts/common/artTemplate.js"></script>
	<c:import var="mybatisCode" url="/templates/javaCode.html" charEncoding="UTF-8"></c:import>
	<script id="mybatisCode" type="text/html">
		${mybatisCode }
	</script>
	<script type="text/javascript" src="/javascripts/user/generation/generation_class.js"></script>
	</body>
</html>