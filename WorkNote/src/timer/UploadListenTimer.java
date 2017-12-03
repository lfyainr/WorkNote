package timer;

import java.util.TimerTask;

public class UploadListenTimer extends TimerTask {

	public static ConcurrentStack<SomeThread> csThread = new ConcurrentStack<SomeThread>();

	@Override
	public void run() {
		// TODO Auto-generated method stub

		System.out.println("1");
		
		while(true){
			if (csThread.size() <= 10) {//如果少于10个，继续启动.
				
				SomeThread sThread = new SomeThread();
				csThread.push(sThread);
			}
			
			for(SomeThread s : csThread){	
				if(!s.isAlive()){
					s.start();
				}
			}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
