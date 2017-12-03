package reflections;

import java.lang.reflect.Method;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 * 项目启动，开启注解。
 * 需要是有三个jar包：
 * javassist.jar 字节码解释器
 * inceptor-driver.jar
 * reflections-0.9.10.jar
 * 
 * 参考：http://www.thinksaas.cn/kaiyuan/ruanjian/214/
 * @author lfy.xys
 * @Date 2017年2月7日 上午11:10:51
 */
public class AutoStart {
	
	public static void main(String[] args) throws Exception {
		AutoStart as = new AutoStart();
		as.start();
	}
	
	public void start() throws Exception{
		final long b = System.currentTimeMillis();
		
		//配置文件
		ConfigurationBuilder conf = new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(""));
		
		//扫描带注解的方法   new TypeAnnotationsScanner().filterResultsBy(optionalFilter)
		conf.setScanners(new SubTypesScanner(),new TypeAnnotationsScanner());
		
		//创建注解
		Reflections reflections = new Reflections(conf);
		
		//扫描 某个类  举例：扫描AutoStartComponent类
		final Set<Class<?>> classes = reflections.getTypesAnnotatedWith(AutoStartComponent.class);
		
		//遍历每一个类，获取类中的 start函数，并执行
		for (final Class<?> class1 : classes) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					Method[] methods = class1.getMethods();//获取 当前类的所有方法 使用lang包的方法。
					try {
						Object obj = class1.newInstance();//创建类的实例
						for (Method method : methods) {
							if(method.getName().equals("start")){
								//执行 函数名为start  的函数  
								method.invoke(class1.newInstance(), null);
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.err.println("输出错误信息：" + class1.getName() + " error," + e.toString());
					}
				}
			}).start();
		}
		
		System.out.println("消耗的时间：" + (System.currentTimeMillis() - b));
		
	}
}
