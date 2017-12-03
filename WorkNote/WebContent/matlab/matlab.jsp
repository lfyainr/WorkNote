<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" style="height: 100%;">
<head style="height: 100%;">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<script type="text/javascript" src="jquery-1.8.2.min.js"></script>
<body class="pageBody" style="height: 100%;">
	<div  style="text-align:center;z-index:1;margin:100px auto 0 auto ;" >
		<div>
			<input id='start' value='开始流程' type='button' />
		</div>
</body>
<script type="text/javascript" >

	function RunMatLab(){  
		var strPath = "file:///C:/MATLAB/R2012b/bin/matlab.exe";
		strPath = strPath.replace(/ /g, "%20").replace(/\\\\/g, "/").replace(/\\/g, "/");
		
		var wsh = new ActiveXObject('WScript.Shell');
		 wsh.Run(strPath);
		
		/* try{      
			 var wsh = new ActiveXObject('WScript.Shell');
			 wsh.Run(strPath);
		}catch(e){
			alert('找不到文件"'+strPath+'"(或它的组件之一)。请确定路径和文件名是否正确.');     
	    }      */
	};

	jQuery("#start").bind("click",function(){
		RunMatLab();
		//后台开始执行
		jQuery.ajax({
			type : "post",
			url : "startMatLab.jsp",
			dataType : "json",
			cache : false,
			async : true,//是否异步
			timeout : 60000,
			success : function(data) {
			},
			error : function(err) {
			}
		});
	});
	
	//字典弹出选择回调的方法
	function dictCallBack(id,name) {
		var path = JSON.stringify(id).split('"')[1];
		path = path.replace(/ /g, "%20").replace(/\\\\/g, "/").replace(/\\/g, "/");
		if (path.indexOf("exe") >= 0) {
			path = "file:///" + path;
		}
		Run(path);
	};
	
	function Run(strPath){  
		try{      
			 var wsh = new ActiveXObject('WScript.Shell');
			 wsh.Run(strPath);
		}catch(e){
			alert('找不到文件"'+strPath+'"(或它的组件之一)。请确定路径和文件名是否正确.');     
	    }     
	};
</script>

</html>