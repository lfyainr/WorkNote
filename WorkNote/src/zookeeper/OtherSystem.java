package zookeeper;

import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class OtherSystem {
	private static String PORT = "32782";
	private static String HOST = "127.0.0.1:" + PORT;
	
	public static void main(String[] args) throws Exception {
//		ConnWatcher watcher = new ConnWatcher();
//		ZooKeeper zk = new ZooKeeper(HOST, 100, watcher);
//		Stat stat = zk.exists("/zk1/a1", true);
//		
//		boolean flag = false;//判断是否 存在节点
//		if(stat == null ){//null 不存在节点
//			flag = true;
//		}else{
//			flag = false;
//		}
//		
//		if(flag){
//			zk.delete("/zk1/a1", -1);
//		}
		System.out.println(EventType.NodeDataChanged);
		System.out.println(EventType.NodeDeleted);
		
	}
	
}
