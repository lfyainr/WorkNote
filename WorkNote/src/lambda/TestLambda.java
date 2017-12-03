package lambda;

/**
 * jdk1.8新特性：lambda 的 写法 ()->
 * 多个抽象方法不能使用Lambda。
 * 并不是所有接口都可以使用Lambda表达式，只有函数式接口可以。
 * 按照Java8函数式接口的定义，其只能有一个抽象方法，否则就不是函数时接口，就无法用Lambda表达式。
 * @author lfy.xys
 *
 */
public class TestLambda {
	public static void main(String[] args) {
		int i = 1;
		Runnable r = () -> {
			System.out.println(i);
			//i++;不允许修改外部变量的值。
		};
		r.run();
		
		new Action() {
			@Override
			public void exec(String content) {
				System.out.println(content);
			}
		}.exec("jdk1.8之前的方法。");		
		
		Action action = (String content)->{
			System.out.println(content);
		};
		action.exec("jdk1.8之后的方法。");
	}

	static interface Action {
		void exec(String content);
	}

}
