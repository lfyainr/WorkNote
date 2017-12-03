package com;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServlet;

import socket.SocketIoHelper;

public class SystemStrat extends HttpServlet {
	
	public void init() {
		System.out.println("这样在web容器启动的时候，就会执行这句话了！");
//		ZkClient zk = new ZkClient();
//		zk.init();
		try {
			SocketIoHelper.start();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//   http://localhost:8081/ChatServlet
	}
	
	public static void main(String[] args) {
//		try {
//			SocketIoHelper.start();
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
		
		String a = "abc,cc";
		System.out.println(a.split(",").length);
		String c = "\\|";
		String b = "abc|cc";
		System.out.println(b.split(c).length);
		
		if(c.equals("|")){
			c = "\\|";
		}
		
	}
	
}
