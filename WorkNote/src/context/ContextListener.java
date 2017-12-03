package context;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

	/** 字段分隔符号 */
	public static final String SEPARATOR = ",";
	/** 文件分隔符号 */
	public static final String PATH_SEPARATOR = "/";
	/** 字符集 */
	public static final String ENCODING = "UTF-8";
	/** 回车符 */
	public static final String STR_ENTER = "\r\n";
	/** CODE_PATH字段内容分隔符 */
	public static final String CODE_PATH_SEPERATOR = "^";
	/** 主键项 */
	public static final String KEY_ID = "_PK_";
	/** 是 */
	public static final String YES = "1";
	/** 否 */
	public static final String NO = "2";
	/** 是 */
	public static final int YES_INT = 1;
	/** 否 */
	public static final int NO_INT = 2;


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		
		System.out.println(".........................................................");
		System.out.println("正在启动系统 ... ...");
		ServletContext sc = sce.getServletContext();
		// 获取系统真实路径
		String systemPath = sc.getRealPath("/");
		System.out.println("系统真实路径1:" + systemPath);

		if (!systemPath.endsWith(File.separator)) {
			systemPath += PATH_SEPARATOR;
		}
		System.out.println("系统真实路径2:" + systemPath);

		String contextPath = sc.getContextPath();
		if (contextPath.equals("/")) {
			contextPath = "";
		} else if (contextPath.endsWith("/")) {
			contextPath = contextPath.substring(0, contextPath.length() - 1);
		}
		System.out.println("系统服务路径: " + contextPath);

		Enumeration<String> names = sc.getInitParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			System.out.println(name);
			
			String param = sc.getInitParameter(name);
			System.out.println(param);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("stop");
	}
}
