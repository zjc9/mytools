package com.zjc.tools.components.config.proxy;

import com.zjc.tools.utils.http;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProxyConfig {
    public List<MyProxy> proxies;
    private final static ProxyConfig p = new ProxyConfig();
    private ProxyConfig() {
        this.proxies = new ArrayList<>();
        inPut();
    }


    public static ProxyConfig getP() {
        return p;
    }
    public List<MyProxy> getProxies() {
        return proxies;
    }

    public void setProxies(List<MyProxy> proxies) {
        this.proxies = proxies;
    }

    public ProxyConfig(List<MyProxy> proxies) {
        this.proxies = proxies;
    }

    public void add(Proxy proxy) {
        int l = proxies.size();
        for (int i = 0; i < l; i++) {
            MyProxy proxy1 = proxies.get(i);
            proxy1.setUsed(false);
        }
        MyProxy myproxy = new MyProxy(proxy, true);
        proxies.add(myproxy);
        outPut();
    }
    public  void outPut(){
        File file=new File("proxy.config");
        try {
            FileWriter out = new FileWriter(file);
            for (MyProxy m:proxies) {
                out.write(m.toString()+"\n");
            }


        out.close();
            System.out.println("config文件已导出");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void inPut(){
        File file=new File("proxy.config");
        try {
            if(file.exists()) {
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String str;
                ArrayList<String> strs = new ArrayList<>();
                while ((str = bufferedReader.readLine()) != null) {
                    strs.add(str);
                }
                System.out.println(strs);
                resolve(strs);
            }else {
                System.out.println("文件不存在");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resolve(List<String> list){
        String pa_str_proxy="proxy.*[0-9]";
        String pa_str_used="true|false";
        Pattern compile = Pattern.compile(pa_str_proxy);
        Pattern compile1 = Pattern.compile(pa_str_used);
        for (String s : list) {
            Matcher matcher = compile.matcher(s);
            Matcher matcher1 = compile1.matcher(s);
            if (matcher.find()&&matcher1.find()) {
                String hostname = matcher.group(0).split(":")[0].split("=")[1];
                String port = matcher.group(0).split(":")[1];
                boolean userd= Boolean.parseBoolean(matcher1.group(0));
                System.out.println("hostname=" + hostname + "  " + "port=" + port);
               System.out.println("userd=" +userd);;
                MyProxy myproxy = new MyProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, Integer.parseInt(port))),userd);
                proxies.add(myproxy);
                if (userd) {
                    http.getInstance().setGlobalProxy(myproxy.getProxy());
                }
                System.out.println();
            } else {
                System.out.println("not found");
            }

        }
    }

    public static void main(String[] args) {
        InetSocketAddress addr = new InetSocketAddress("127.0.0.6", 88);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,addr);
       ArrayList<MyProxy> m = new ArrayList<>();
       ProxyConfig proxyConfig = new ProxyConfig();
        proxyConfig.add(proxy);
       // proxyConfig.inPut();
      // proxyConfig.outPut();

    }
}
