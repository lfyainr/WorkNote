package pushlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.SessionManager;

public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("调用servlet一次");
		// myUnicast();
		myMulticast();
		// myBroadcast();
	}

	private void myUnicast() {

		Event event = Event.createDataEvent("/helloworld");
		event.setField("key1", "Unicast msg");
		Dispatcher.getInstance().unicast(event, "piero"); // 向ID为piero的用户推送
		System.out.println("success....");
	}

	private void myMulticast() {
		Event event = Event.createDataEvent("helloworld的_BBB33B");
		// Event event = Event.createDataEvent("/guoguo");
		event.setField("key1", "Multicast msg");
		Dispatcher.getInstance().multicast(event); // 向所有和myevent1名称匹配的事件推送

		System.out.println("wa success....");

	}

	private void myBroadcast() {
		Event event = Event.createDataEvent("/helloworld"); // 向所有的事件推送，不要求和这儿的myevent1名称匹配
		event.setField("key1", "Broadcast msg");
		Dispatcher.getInstance().broadcast(event);

		System.out.println("asw success....");
	}

}