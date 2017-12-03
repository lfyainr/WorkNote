package pushlet;

import java.io.Serializable;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;

public class HelloWorldPushlet implements Serializable {

	private static final long serialVersionUID = -8940934044114406724L;

	public static class HWPushlet extends EventPullSource {

		@Override
		protected long getSleepTime() {
			return 3000;// 每3秒钟自动执行一次
		}

		@Override
		protected Event pullEvent() {
			// 注意，以下是设定消息的主题/helloworld，号称主题是可以继承的 但是笔者的测试是失败的，也许方法不对，呵呵
			Event event = Event.createDataEvent("/helloworld");
			String data = "hello,world! 大陆时间: " + System.currentTimeMillis();
			// try {
			// data = new String(data.getBytes("UTF-8"), "UTF-8");
			// } catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			// }
			event.setField("hw", data);
			System.out.println("HelloWorldPushlet启动就会执行。");
			return event;
		}
	}
}
