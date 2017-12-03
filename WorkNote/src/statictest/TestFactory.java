package statictest;

public class TestFactory {
	static String a;
	public TestFactory(String a){
		this.a = a;
	}
	
	public void mm(){
		System.out.println(a);
	}
}
