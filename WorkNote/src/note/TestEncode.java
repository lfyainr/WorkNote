package note;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TestEncode {
    
    public static void main(String[] args) {
        test();
    }
    
    public static void test1(){
        String[] str = "0^11".split("^");
        System.out.println(str[0]);
        
        str = "0,11".split(",");
        System.out.println(str[0]);
        
        
    }

    public static void test() {

        String url = "http://11.1.1.1:8080/servlet.do?fileName=asd.jar,&aa=dfsf_f,";
        String a = "";
        try {
            a = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(a);
    }
}
