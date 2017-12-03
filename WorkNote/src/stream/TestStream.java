package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * jdk1.8新特性
 * Stream 泛型接口
 * 代表数据流，不是io流
 * Stream 通过管道操作数据
 * @author lfy.xys
 *
 */
public class TestStream {
	public static void main(String[] args) {
		List<People> list = new ArrayList<People>();
		list.add(new People("1",11));
		list.add(new People("2",12));
		list.add(new People("3",13));
		System.out.println(list);
		
		Stream<People> stream = list.stream();
		stream = stream.filter(p -> p.getAge()==11);//过滤 年龄是 11的
		//输出 过滤后的
		stream.forEach(
				p->System.out.println(p.getAge())
				);
	}
}
