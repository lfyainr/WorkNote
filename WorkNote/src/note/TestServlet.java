package note;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;

public class TestServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String mark = req.getHeader("MARK");
		
		InputStream is = req.getInputStream();// 获取流
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		String line = "";
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			if (line.length() > 0) {
				sb.append(line.trim());
			}
		}

		List<Bean> json = JSON.parseArray(sb.toString(), Bean.class);
		for (int i = 0; i < json.size(); i++) {
			Bean b = json.get(i);
			// 业务
		}
		
		/*
		 * 发送给 请求端 的值
		 * 简单参数使用这种方式，在客户端 使用 conn.getHeader("MARKS") 获取返回参数
		 */
		res.setHeader("MSG","xxxxx");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, res);
	}

	/**
	 * 发送返回值
	 * @author liufengyuan
	 * @date 2017年1月11日 下午4:02:12
	 */
	private void outJson(HttpServletResponse response, String json) {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(URLEncoder.encode(json,"UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
