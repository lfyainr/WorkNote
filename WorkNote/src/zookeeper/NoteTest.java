package zookeeper;

import java.io.IOException;
import java.util.List;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class NoteTest {

	private static String PORT = "32782";
	
	public static void main(String[] args) {
		String host = "127.0.0.1:" + PORT;
		ConnWatcher watcher = new ConnWatcher();
		System.out.println(0);
		try {
			/*
			 * 连接zk
			 * ZooKeeper(String connectString, int sessionTimeout, Watcher watcher) throws IOException
			 * connectString  zookeeper server列表, 以逗号隔开. ZooKeeper对象初始化后, 将从server列表中选择一个server, 并尝试与其建立连接. 如果连接建立失败, 则会从列表的剩余项中选择一个server, 并再次尝试建立连接.
			 * sessionTimeout  指定连接的超时时间. 
			 * watcher  事件回调接口.
			 */
			ZooKeeper zk = new ZooKeeper(host, 100, watcher);
			System.out.println(1);
			
			/*
			 * ZooKeeper对象的exists方法用于判断指定znode是否存在.
			 * Stat exists(String path, boolean watch);  
			 * watch参数用于指定是否监听path node的创建, 删除事件, 以及数据更新事件. 如果该node存在, 则返回该node的状态信息, 否则返回null.
			 */
			Stat stat = zk.exists("/zk1", false);
			boolean flag = false;//判断是否 存在节点
			if(stat == null ){//null 不存在节点
				flag = true;
			}else{
				flag = false;
			}
			
			/*
			 * 创建节点
			 * String create(String path, byte[] data, List acl, CreateMode createMode);  
			 * path  创建节点的的路径 ,/ 开头  比如：/zk-test-create
			 * data  与znode关联的数据.
			 * 	字节数组，创建节点初始化内容。使用者需自己进行序列化和反序列化。复杂对象可使用 Hessian或Kryo进行进行序列化和反序列化。
			 * acl  指定权限信息, 如果不想指定权限, 可以传入Ids.OPEN_ACL_UNSAFE. 节点的acl策略
			 * createMode  指定znode类型. CreateMode是一个枚举类, 从中选择一个成员传入即可
			 * 	1）PERSISTENT：持久；创建节点后，不删除就永久存在
			 * 	2）PERSISTENT_SEQUENTIAL：持久顺序; 节点path末尾会追加一个10位数的单调递增的序列
			 * 	3）EPHEMERAL：临时；创建后，回话结束节点会自动删除
			 * 	4）EPHEMERAL_SEQUENTIAL：临时顺序; 节点path末尾会追加一个10位数的单调递增的序列
			 * 	节点只能一层一层创建
			 */
			System.out.println("flag:" + flag);
			if(flag){
				zk.create("/zk1", "zk".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				zk.create("/zk1/a1", "a1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
			System.out.println(2);
			
			
			/*
			 * void delete(final String path, int version);  
			 * ZooKeeper对象的delete方法用于删除znode.
			 * version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败.
			 */
			zk.delete("/root/childone", -1);
			
			/*
			 * Stat setData(final String path, byte data[], int version);
			 * ZooKeeper对象的setData方法用于更新node关联的数据
			 * data为待更新的数据.
			 * version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败. 指定version为-1则忽略版本检查.
			 * 返回path node的状态信息.
			 */
			zk.setData("/zk", "bb".getBytes(), -1);
			
			/*
			 * byte[] getData(String path, boolean watch, Stat stat); 
			 * ZooKeeper对象的getData方法用于获取node关联的数据.
			 * watch参数用于指定是否监听path node的删除事件, 以及数据更新事件, 注意, 不监听path node的创建事件, 因为如果path node不存在, 该方法将抛出KeeperException.NoNodeException异常.
			 * stat参数是个传出参数, getData方法会将path node的状态信息设置到该参数中.
			 */
			zk.getData("/zk",false, null);
			
			/*
			 * 获取子node列表
			 * List<String> getChildren(String path, boolean watch); 
			 * watch参数用于指定是否监听path node的子node的增加和删除事件, 以及path node本身的删除事件.
			 */
			List<String> list = zk.getChildren("/zk", true);
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
