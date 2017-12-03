package zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

public class ZkClient {
	private static String PORT = "32782";
	private static String HOST = "127.0.0.1:" + PORT;

	public void init() {
		ConnWatcher watcher = new ConnWatcher();
		try {
			ZooKeeper zk = new ZooKeeper(HOST, 30, watcher);

			Stat stat = zk.exists("/zk1", false);
			boolean flag = false;// 判断是否 存在节点
			if (stat == null) {// null 不存在节点
				flag = true;
			} else {
				flag = false;
			}

			System.out.println("flag:" + flag);
			if (flag) {
				zk.create("/zk1", "zk".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				zk.create("/zk1/a1", "a1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
			
			List<String> list = zk.getChildren("/zk1", true);
			System.out.println(list);
			
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
