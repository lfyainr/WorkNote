package trycatch;

public class TestCatch {
	public static void main(String[] args) {
		TestCatch TestCatch = new TestCatch();
		try {
			TestCatch.A();
			System.out.println("zxczxczx");
		} catch (Exception e) {
			System.out.println("AAA");
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void A(){
		B();
	}

	public void B() {
		C();
	}

	public void C() {
		String a = null;
		System.out.println(a.getBytes());
	}
}
