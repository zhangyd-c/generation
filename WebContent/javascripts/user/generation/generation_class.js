$(function(){
	var DOM_TYPE_INPUT = "input";
	var DOM_TYPE_INPUT = "select";
	
	// 点击生成
	$("#generatClass").click(function(){
		var params = $("#form").serialize();
		if (!validParam(params)) {
			showDialogHtml("参数不为空!");
			return;
		}
		$.ajax({
			type:"post",
			url:"/GenerationClassServlet",
			data:$("#form").serialize(),
			cache:false,
			success:function(data){
				data = jQuery.parseJSON(data);
				var html = template('mybatisCode',data);
				$("#showCode").html("");
				$("#showCode").append(html);
				SyntaxHighlighter.config.stripBrs = true;  // 是否过滤html标签，显示格式化信息
				SyntaxHighlighter.config.bloggerMode = true; // 格式化样式
				SyntaxHighlighter.highlight();// 高亮显示
				$("#selectTableNames").hide();
				$("#showCode").show();
				$(".copyButton").click(function(event) {
					$(this).parents(".cnblogs_code_toolbar").next().find(".code").click();
				});
				
				$("#generationJava").show();
				$("#generationZip").show();
			},
			error:function(){
				showDialogHtml("<font color='red'>服务器异常</font>");
			}
		});
	})
	
	// 点击生成
	$("#generatMapper").click(function(){
		var params = $("#form").serialize();
		if (!validParam(params)) {
			showDialogHtml("参数不为空!");
			return;
		}
		$.ajax({
			type:"post",
			url:"/GenerationMapperServlet",
			data:$("#form").serialize(),
			cache:false,
			success:function(data){
				data = jQuery.parseJSON(data);
				var html = template('mybatisCode',data);
				$("#showCode").html("");
				$("#showCode").append(html);
//				SyntaxHighlighter.defaults['toolbar'] = true;  //去掉右上角问号图标
//				SyntaxHighlighter.config.tagName = 'pre';       //可以更改解析的默认Tag。
				SyntaxHighlighter.config.bloggerMode = true; 
				SyntaxHighlighter.config.stripBrs = true;  
				SyntaxHighlighter.highlight();
				$("#selectTableNames").hide();
				$("#showCode").show();
				$(".copyButton").click(function(event) {
					$(this).parents(".cnblogs_code_toolbar").next().find(".code").click();
				});
				
				$("#generationXml").show();
				$("#generationXmlZip").show();
			},
			error:function(){
				showDialogHtml("<font color='red'>服务器异常</font>");
			}
		});
		
	})
	
	// 数据库地址 、用户名、密码验证
	$("#next1").click(function(){
		var dbAddress = $("#dbAddress").val();
		var dbLoginName = $("#dbLoginName").val();
		var dbPassword = $("#dbPassword").val();
		if(isEmpty(dbAddress) || isEmpty(dbLoginName) ||isEmpty(dbPassword)){
			if(isEmpty(dbAddress)){
				changeDomColor($("#dbAddress"), DOM_TYPE_INPUT)
			}
			if(isEmpty(dbLoginName)){
				changeDomColor($("#dbLoginName"), DOM_TYPE_INPUT)
			}
			if(isEmpty(dbPassword)){
				changeDomColor($("#dbPassword"), DOM_TYPE_INPUT)
			}
			return ;
		}
		
		var params = $("#form").serialize();
		var url = "/GenerationOfGetDBNamesServlet";
		$.ajax({
			type:"post",
			url:url,
			data:params,
			cache:false,
			success:function(data){
				data = jQuery.parseJSON(data);
				if(isNotEmpty(data)){
					$("#step2").show();
					$("#step1").hide();
					$("#selectedDbNames").empty();
					var option = "";
					for(var i = 0 , len = data.length ; i < len ; i ++){
						option += "<option value="+data[i]+">"+data[i]+"</option>";
					}
					$("#selectedDbNames").append(option);
				}else{
					showDialogHtml("<font color='red'>获取数据库失败，请检查数据库地址、用户名或者密码是否正确。<p>如依然不行，请稍后再试...</p></font>");
					$("#step2").hide();
				}
			},
			error:function(){
				showDialogHtml("<font color='red'>服务器异常</font>");
			}
		});
	})  
	
	$("#next2").click(function(){
		$.ajax({
			type:"post",
			url:"/GenerationOfTablesServlet",
			data:$("#form").serialize(),
			cache:false,
			success:function(data){
				data = jQuery.parseJSON(data);
				if(isNotEmpty(data)){
					$("#step3").show();
					$("#step2").hide();
					$("#step1").hide();
					$("#selectedTableNames").empty();
					var option = "";
					for(var i = 0 , len = data.length ; i < len ; i ++){
						option += "<option value="+data[i]+">"+data[i]+"</option>";
					}
					$("#selectedTableNames").append(option);
				}else{
					showDialogHtml("<font color='red'>获取数据库表失败，请检查数据库地址、用户名或者密码是否正确。<p>如依然不行，请稍后再试...</p></font>");
					$("#step3").hide();
				}
			},
			error:function(){
				showDialogHtml("<font color='red'>服务器异常</font>");
			}
		});
	})
	
	$("#goHistory1").click(function(){
		$("#showCode").html("");
		$("#step3").hide();
		$("#step2").hide();
		$("#step1").show();
		
	})
	$("#goHistory2").click(function(){
		$("#showCode").html("");
		$("#step3").hide();
		$("#step2").show();
		$("#step1").hide();
		
	})
	
	$("#dbAddress,#dbLoginName,#dbPassword").focus(function(){
		init1();
	})
	
	function init1(){
		$("#dbAddress").css("border", "");
		$("#dbLoginName").css("border", "");
		$("#dbPassword").css("border", "");
	}
	
	function changeDomColor(obj,objType){
		//obj.attr("style","border-color: red"); --input
		//obj.css("border", "1px solid red"); --select
		if(objType == "select"){
			$(obj).attr("style","border-color: red");
		}else if(objType == "input"){
			$(obj).css("border", "1px solid red");
		}
	}
})