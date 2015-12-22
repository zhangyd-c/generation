/**
 * 显示模态框
 * 
 * @param content
 */
function showDialog(content) {
	$("#data_alertMsg").text(content);
	$("#modal_alert").modal("show");
}

function showDialogHtml(content) {
	$("#data_alertMsg").html(content);
	$("#modal_alert").modal("show");
}

/**
 * 关闭模态框
 * 
 * @param content
 */
function closeDialog(content) {
	$("#data_alertMsg").text("");
	$("#modal_alert").modal("hide");
}

/**
 * 初始化参数
 */
function init() {

}

/**
 * 验证form表单的参数是否为空， 为空返回false
 * 
 * @param params
 * @returns {Boolean}
 */
function validParam(params) {
	var arr = params.split("&");
	for (var i = 0; i < arr.length; i++) {
		var paArr = arr[i].split("=");
		if (paArr.length < 2) {
			return false;
		}
		if (paArr[1] == null || paArr[1] == "") {
			return false;
		}
	}
	return true;
}

/**
 * @author zhangyd-c
 * @date 2015-12-03 17:59:00
 * @param obj：需要操作的对象元素
 * @param text：提示的内容（如果isHtml为true，则text可带html样式）
 * @param isHtml：是否显示html格式
 * @param direction：left、top、bottom、right
 * @param title：标签的title，如果有title，则会显示成类似双层结构的提示框
 */
function showPopover(obj, text, isHtml, direction, title) {
	$(obj).attr("data-container", "body").attr("data-toggle", "popover").attr(
			"data-html", isHtml).attr("data-placement", direction).attr(
			"data-content", text).attr("title", title);
	$(obj).popover('show');
	// 延迟2秒消失
	setTimeout(function() {
		$(obj).popover('hide');
	}, 2000);
}

/**
 * @author zhangyd-c
 * @date 2015-12-04 16:33:00
 * @param obj：需要操作的对象元素
 * @param text：提示的内容（如果isHtml为true，则text可带html样式）
 * @param isHtml：是否显示html格式
 * @param direction：left、top、bottom、right
 * @param title：标签的title，如果有title，则会显示成类似双层结构的提示框
 */
function showPopoverOnly(obj, text, isHtml, direction, title) {
//	$(obj).popover({
//		trigger:'hover',
//        placement : direction, 
//        title : title, 
//        html: isHtml, 
//        content : text,
//    });
	$(obj).attr("data-container", "body").attr("data-toggle", "popover").attr(
			"data-html", isHtml).attr("data-placement", direction).attr(
			"data-content", text).attr("title", title);
	$(obj).popover('show');
}

function isEmpty(param){
	if(param == "" || param == null){
		return true;
	}
	return false;
}

function isNotEmpty(param){
	return !isEmpty(param);
}

