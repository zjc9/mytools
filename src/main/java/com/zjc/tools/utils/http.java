package com.zjc.tools.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Map;
import java.util.Set;


public class http {
    private Proxy p;
    private final static http h = new http();

    private http() {
        this.p = null;
    }

    public static http getInstance() {
        return h;
    }

    public Proxy getGlobalProxy() {
        return p;
    }

    public void setGlobalProxy(Proxy proxy) {
        this.p = proxy;
        System.out.println("global proxy=" + proxy.toString());
    }

    public void closeGlobalProxy() {
        this.p = null;
        System.out.println("close the global proxy");
    }

    public String sendRequest(String u, String requestType) {
        String result = "";
        if (p != null) {
            System.out.println("从代理转发");
            sendRequest(u, requestType, p);
        } else {
            System.out.println("直接");
            try {
                URL url = new URL(u);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                result = this.send(urlConnection, requestType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return result;
    }

    public String sendRequest(String u, String requestType, Map headers) {
        String result = "";
        if (p != null) {
            System.out.println("从代理转发");
            sendRequest(u, requestType,headers, p);
        } else {
            System.out.println("直接");
            try {
            URL url = new URL(u);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Set<String> set = headers.keySet();
            for (String key : set) {
                urlConnection.setRequestProperty(key, (String) headers.get(key));
            }
            result = this.send(urlConnection, requestType);
        } catch (IOException e) {
            e.printStackTrace();
        }}


        return result;
    }

    public String sendRequest(String url, String requestType, Proxy proxy) {
        String result = "";
        try {
            URL url1 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection(proxy);
            result = this.send(urlConnection, requestType);
        } catch (IOException e) {

        }

        return result;
    }

    public String sendRequest(String u, String requestType, Map headers, Proxy proxy) {
        String result = "";
        try {
            URL url = new URL(u);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(proxy);
            Set<String> set = headers.keySet();
            for (String key : set) {
                urlConnection.setRequestProperty(key, (String) headers.get(key));
            }
            result = this.send(urlConnection, requestType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String send(HttpURLConnection h, String requestType) {
        h.setConnectTimeout(1200);
        BufferedReader bufferedReader = null;
        try {
            h.setRequestMethod(requestType);
            Object content = h.getContent().toString();
            System.out.println("content=" + content);
            h.connect();
            if (h.getResponseCode() == 200) {
                InputStream inputStream = h.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String temp;
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp);
                    stringBuilder.append(System.lineSeparator());
                }
                String result = stringBuilder.toString();
                return result;
            }
            h.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "failed";
    }

    public static void main(String[] args) throws IOException {
        http http = new http();
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8888);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
        //  String get =http.sendRequest("http://127.0.0.1:8080/?name=Bob&age=25", "GET");
        String get1 = http.sendRequest("http://192.168.199.229:810", "GET", proxy);
        System.out.println(get1);
        //System.out.println(get);
    }
}
