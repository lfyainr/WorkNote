package activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码
//	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址
	private static final String BROKEURL = "tcp://192.168.145.133:61616";
	
	public static void main(String[] args) {
		// ConnectionFactory ：连接工厂，JMS 用它创建连接
		ConnectionFactory connectionFactory;
		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection connection = null;
		// Session： 一个发送或接收消息的线程
		Session session;
		// Destination ：消息的目的地;消息发送给谁.
		Destination destination;
		// 消费者，消息接收者
		MessageConsumer consumer;
		try {
			connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			// 获取操作连接
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			// 获取session 注意参数值FirstQueue是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createQueue("FirstQueue");
			consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {
					try {
						if (null != message) {
							TextMessage textMessage = (TextMessage) message;
			    			String content = textMessage.getText();
							System.out.println("收到消息2" + content);
						}
						// 设置接收者接收消息的时间，为了便于测试，这里定为100s
//						TextMessage bean = (TextMessage) consumer.receive(100000);
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}
	}
}