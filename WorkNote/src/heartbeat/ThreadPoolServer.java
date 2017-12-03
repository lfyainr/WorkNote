package heartbeat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadPoolServer {
	private ServerSocket ss;
	private ExecutorService es;
	private boolean isConnect;
	private final int nThread = 2;
	private ArrayList<Task> ay;
	private Socket socket;

	public ThreadPoolServer() {
		try {
			ss = new ServerSocket(38885);
			isConnect = true;
			ay = new ArrayList<Task>();
			es = Executors.newFixedThreadPool(nThread);
		} catch (BindException e) {
			System.out.println("端口已经被其他服务器占用");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ThreadPoolServer().connect();
	}

	public void connect() {
		try {
			while (isConnect) {
				socket = ss.accept();
				Task task = new Task(socket);
				es.execute(task);
				ay.add(task);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class Task implements Runnable {
		BufferedReader br;
		PrintWriter pw;
		String str = null;
		Socket socket;

		public Task(Socket socket) {
			try {
				this.socket = socket;
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			try {
				str = br.readLine();
				while (str != null) {
					for (int i = 0; i < ay.size(); i++) {
						Task task = ay.get(i);
						pw = new PrintWriter(task.socket.getOutputStream(), true);
						pw.println(Thread.currentThread().getName() + ":" + str);
					}
					str = br.readLine();
				}
			} catch (SocketException e) {
				ay.remove(this);
				System.out.println("一个客户端退出了");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}