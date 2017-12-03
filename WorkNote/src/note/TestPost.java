package note;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class TestPost {

	/**
	 * 需要引入 import com.alibaba.fastjson.JSON 
	 * url : http://192.168.1.1:8081/servlet.do bean : 一个集合
	 */
	public static String sendPost(String url, List<Bean> bean) {
		// 把集合 解析成 json
		String beanStr = JSON.toJSONString(bean);

		HttpURLConnection conn = null;
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Mark", "XXX");//如果是中文需要转换
			conn.setRequestMethod("POST");
			
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(beanStr);
			// flush输出流的缓冲
			out.flush();

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			
			String msg = conn.getHeaderField("MSG");//返回值
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result.toString();
	}
}
