package note;

import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

public class OutJson {
	
	private void outJsons(HttpServletResponse response, String json) {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(json);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
