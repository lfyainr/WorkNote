package heartbeat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 客户端 socket心跳 监听
 * 
 * @author lfy.xys
 * @Date 2017年4月12日 上午11:39:42
 */
public class ThreadPoolClient {
	public static void main(String[] args) {
		Socket socket = null;
		ExecutorService es = null;
		final int nThreads = 2;
		try {
			socket = new Socket("127.0.0.1", 38885);
		} catch (UnknownHostException e) {
			System.out.println("无法识别服务器的主机名");
		} catch (ConnectException e) {
			System.out.println("没有服务器监听指定的端口或者服务器拒绝连接");
		} catch (IOException e) {
			e.printStackTrace();
		}
		WriteTask wt = new WriteTask(socket);
		ReadTask rt = new ReadTask(socket);
		es = Executors.newFixedThreadPool(nThreads);
		es.submit(wt);
		es.submit(rt);
	}
}

class WriteTask implements Runnable {
	BufferedReader br;
	PrintWriter pw;
	Socket socket;

	public WriteTask(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			pw = new PrintWriter(socket.getOutputStream(), true);
			String str = br.readLine();
			while (str != null) {
				pw.println(str);
				str = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class ReadTask implements Runnable {
	Socket socket;
	BufferedReader br;

	public ReadTask(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str = br.readLine();
			while (str != null) {
				System.out.println(str);
				str = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}