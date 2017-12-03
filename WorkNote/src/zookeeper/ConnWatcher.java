package zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class ConnWatcher implements Watcher {

	@Override
	public void process(WatchedEvent event) {
		// 连接建立, 回调process接口时, 其event.getState()为KeeperState.SyncConnected
		if (event.getState() == KeeperState.SyncConnected) {
			
			System.out.println("回调watcher实例： 路径" + event.getPath() + " 类型：" + event.getType());
			
			if(event.getType() == EventType.None && event.getPath() == null){
				System.out.println("zk connected!");
			}
			if (event.getType() == EventType.NodeDataChanged) {
				System.out.println("change");
			}
			if (event.getType() == EventType.NodeDeleted) {
				System.out.println("delete");
			}
			if (event.getType() == EventType.NodeCreated) {
				System.out.println("create");
			}
			
		}
		
		if(event.getState() == KeeperState.Disconnected){
			System.out.println("zk Disconnected!");
		}
		
		if(event.getState() == KeeperState.Expired){
			System.out.println("zk Expired.");
		}
		
		
	}

}
