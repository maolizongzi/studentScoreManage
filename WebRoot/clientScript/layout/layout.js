function getURL(scriptName) {
	var url = "";
	if (scriptName.indexOf("home") > -1) {
		if (scriptName == "home_homePage") {
			url = "../login/toHome.do";
		}
		//http://localhost:8080/cooperation_manager/user/toLogonPage.do
	} else if (scriptName.indexOf("linkFlag") > -1) {
		if (scriptName == "linkFlag_in") {
			url = "../link/toLinkPage.do";
		} 
	} else if (scriptName.indexOf("userManager") > -1) {
		if (scriptName == "userManager_user") {
			url = "../user/toUserList.do";
		}else if (scriptName == "userManager_role") {
			url = "../user/toPurviewGtoupList.do";
		} 
	} else if (scriptName.indexOf("channelConfigManager") > -1) {
		if (scriptName == "channelConfigManager_company") {
			url = "../dataInfo/toDataInfoListPage.do";
		}
	}else if(scriptName.indexOf("dataInfoManager")>-1){
		if (scriptName == "dataInfoManager_book") {
			url = "../bookReport/toBookReportList.do";
		}
	} else if(scriptName.indexOf("task")>-1){
		if (scriptName == "task_data") {
			url = "../task/dataList.do";
		}
	} else if (scriptName.indexOf("userRate") > -1){
		if (scriptName == "userRate_registration") {
			url = "../userRate/toUserRateListPage.do";
		}
		if (scriptName == "userRate_login") {
			url = "../userRate/toUserLoginRateListPage.do";
		} 
	}
	return url;
}

function toPage(scriptName) {
	var url = getURL(scriptName);
	if (url != "") {
		$("#pageFrame").attr("src", url);
	}
}

function showTabPage(id) {
	//获取  class="active" 的 name
	var scriptNames = id + "functionScriptName";
	var functionScriptName = $("input[name='" + scriptNames + "']");
		$(".nav-tabs").hide();
		var tabPage = document.getElementById(id + "TabPage");
		var $tabPage = $(tabPage);
		$tabPage.show();
		$(".active").removeClass("active");
		$("input[name='" + scriptNames + "']")[0].click(function () {
			toPage(functionScriptName);
		});
		$("input[name='" + scriptNames + "']").eq(0).parent().addClass("active");
}

function fixedParentTitle() {
	var parentTitles = $("span[class='parentTitle']");
	$(parentTitles).each(function (i, o) {
		var $o = $(o);
		var ulClass = $o.parent().parent().parent().attr("class");
		if (ulClass == "treeview-menu") {
			$o.css({
				"font-size": 10
			});
		}
	});
}

function loginOut() {
	location.href = "/login/loginOut.do";
}

function freamPageHeight() {
//	var height = $(window).height();
//	$("#pageFrame").parent().css({
//		"min-height": height * 0.8
//	});
//	$("#pageFrame").css({
//		"height": height * 0.8
//	});
//	$("#page-containe-left").css({
//		"min-height" : height * 0.8
//	});
}