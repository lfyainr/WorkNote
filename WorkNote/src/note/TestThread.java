package note;

/**
 * 测试 线程里 启动另一个线程
 * 父线程 会 一直开启，知道 里面的 子线程 都结束
 * @author lfy.xys
 * @Date 2016年12月22日 下午3:05:30
 */
public class TestThread {
    public void test() {

        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("-----1");
                    
                    new Thread() {
                        public void run() {
                            try {
                                System.out.println("sleep1");
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            System.out.println("sleep2");
                        };
                    }.start();
                    
                    System.out.println("-----2");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();

    }
    
    public static void main(String[] args) {
        TestThread t = new TestThread();
        t.test();
    }
}
