package constructor;

public class Test1 {

	
	public static void main(String[] args) {
		
		int i = 1 ;
		Test2 ss = new Test2(i);
		
		i = 2;
		
		/*
		 *  输出 1，则 对象不一样
		 *  输出 2，则 对象一样 
		 */
		System.out.println(ss.i);
	}
}
