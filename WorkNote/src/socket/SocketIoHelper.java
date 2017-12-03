package socket;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketIoHelper {

	public static void start() throws URISyntaxException {
		final Socket socket = IO.socket("http://localhost:3000");
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			public void call(Object... args) {
				socket.emit("JavaToJs", "Hi,Im JAVA --javatojs");
				socket.emit("msg", "Hi,Im JAVA --javatojs");
				socket.disconnect();
			}
		}).on("JsToJava", new Emitter.Listener() {
			public void call(Object... args) {
				System.out.println("JsToJava");
				for (Object obj : args) {
					System.out.println("who:" + obj);
				}
			}
		}).on("msg", new Emitter.Listener() {
			public void call(Object... args) {
				for (Object obj : args) {
					System.out.println("who:" + obj);
				}
			}
		}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
			public void call(Object... args) {
			}
		});
		socket.connect();
	}

	public static void main(String[] args) throws Exception {
		SocketIoHelper.start();
		while(true){
			Thread.sleep(5000);
		}
		
	}
}
