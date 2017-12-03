<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>测试页面page/index</title>
</head>
<%
	String path = request.getContextPath();
	System.out.println("path:"+path);
%>
<base href="<%=path%>">
<script type="text/javascript" src="<%=path %>/js/ajax-pushlet-client.js"></script>
<body>
	<h4>page/index</h4>
	<div id="aa"></div>
	<script type="text/javascript">
		//初始化pushlet客户端
		PL._init();
		//设定运行时显示调试信息，不需要时，直接删掉即可
		PL.setDebug(true);
		//设定监听主题：/guoguo/helloworld，与服务器端的主题完全一致
		PL.joinListen('helloworld的_BBB33B');
		//接收到事件后，显示服务器信息
		function onPushData(event) {
			//guoguo.innerText = (event.get("hw"));
			aa.innerText = (event.get("key1"));
		}
	</script>
	<p1>Pushlet Test</p1>
</body>
</html>