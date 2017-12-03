package note;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试 两个 线程池 内套
 * pool1 里 启动 另一个线程，并不会 占用 pool1。而释放的pool1 会去执行另外一个 线程。 
 * @author lfy.xys
 * @Date 2016年12月22日 下午3:16:19
 */
public class TestMorePool {
    
    public static ExecutorService pool1 = Executors.newFixedThreadPool(1);
    public static ExecutorService pool2 = Executors.newFixedThreadPool(1);
    
    public void test() {
        pool1.execute(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                pool2.execute(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        try {
                            System.out.println("111");
                            Thread.sleep(5000);
                            System.out.println("222");
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        
        pool1.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("123");
                pool2.execute(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        try {
                            System.out.println("asd");
                            System.out.println("zxc");
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        
    }
    
    public static void main(String[] args) {
        TestMorePool t = new TestMorePool();
        t.test();
    }
    
}
