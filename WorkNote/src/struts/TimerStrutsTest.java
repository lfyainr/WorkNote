package struts;

import com.opensymphony.xwork2.ActionSupport;

public class TimerStrutsTest extends ActionSupport {
	
	@Override
	public String execute() {
		//http://localhost:8080/timer/timer.action
		try {
			// 模拟耗时的操作
			System.out.println(1);
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void start(){
		//http://localhost:8080/timer/timer!start.action
		System.out.println("start");
	}
}
