package note;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试线程池
 * @author lfy.xys
 * @Date 2016年12月22日 下午2:23:51
 */
public class TestPool {
    public static ExecutorService pool = Executors.newFixedThreadPool(2);

    public void test() {
        System.out.println("a");
        pool.execute(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    System.out.println(1);
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                System.out.println("-----");
                                Thread.sleep(15000);
                                System.out.println("-----");
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    System.out.println(11);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        System.out.println("b");
        pool.execute(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    System.out.println(2);
                    Thread.sleep(5000);
                    System.out.println(22);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        System.out.println("c");
        pool.execute(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    System.out.println(3);
                    Thread.sleep(5000);
                    System.out.println(33);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        pool.execute(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    System.out.println(4);
                    Thread.sleep(5000);
                    System.out.println(44);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        System.out.println("d");
    }

    public static void main(String[] args) {
        TestPool t = new TestPool();
        t.test();
    }
}
