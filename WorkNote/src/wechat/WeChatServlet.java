package wechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeChatServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sig = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String token = "geg4ff2fda1das"; // 这里填写自己的 token
		List<String> list = new ArrayList<String>();
		list.add(nonce);
		list.add(token);
		list.add(timestamp);
		Collections.sort(list);
		String hash = getHash(list.get(0) + list.get(1) + list.get(2), "SHA-1");
		if (sig.equals(hash)) { // 验证下签名是否正确
			response.getWriter().println(echostr);
		} else {
			response.getWriter().println("");
		}
	}

	public String getHash(String source, String hashType) {
		StringBuilder sb = new StringBuilder();
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance(hashType);
			md5.update(source.getBytes());
			for (byte b : md5.digest()) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    String sig =  request.getParameter("signature");
//	    System.out.println("sig : "+sig);
//	    String timestamp =  request.getParameter("timestamp");
//	    String nonce =  request.getParameter("nonce");
//	    String echostr =  request.getParameter("echostr");
//	    String token = "nero";
//	    String responseContent = defaultStr;
//	    List<String> list = new ArrayList<String>();
//	    list.add(nonce);
//	    list.add(token);
//	    list.add(timestamp);
//	    Collections.sort(list);
//	    String hash = getHash(list.get(0)+list.get(1)+list.get(2), "SHA-1").toLowerCase();
//	    if(sig.equals(hash)){
//	        if(request.getMethod().equals("POST")){
//	            Map<String,String> map = XMLParse.extract(convertStreamToString(request.getInputStream()));
//	            if(map.get("Content").equals("hello")){
//	                responseContent = "Hello,This message from SinaCloud";
//	            }
//	            if(map.get("Content").equals("time")){
//	                sf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//	                responseContent = sf.format(new Date());
//	            }
//	            responseMsg = formatResponseMsg(responseContent, map);
//	        }
//	        response.setCharacterEncoding("utf-8");
//	        response.getWriter().println(responseMsg);
//	    }else{
//	        response.getWriter().println("success");
//	    }
//	}
//
//	public String convertStreamToString(InputStream is) {
//	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//	    StringBuilder sb = new StringBuilder();
//	    String line = null;
//	    try {
//	        while ((line = reader.readLine()) != null) {
//	            sb.append(line);
//	        }
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    } finally {
//	        try {
//	            is.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	    return sb.toString();
//	}
//
//	public String formatResponseMsg(String content,Map map){
//	    String responseMsg = "<xml>"
//	            + "<ToUserName><![CDATA[%1$s]]></ToUserName>"
//	            + "<FromUserName><![CDATA[%2$s]]></FromUserName>"
//	            + "<CreateTime>%3$s</CreateTime>"
//	            + "<MsgType><![CDATA[%4$s]]></MsgType>"
//	            + "<Content><![CDATA[%5$s]]></Content>"
//	            + "<MsgId>%6$s</MsgId>"
//	            + "</xml>";
//	    return String.format(responseMsg, map.get("FromUserName"),map.get("ToUserName"),map.get("CreateTime"),map.get("MsgType"),content,map.get("MsgId"));
//	}
}
