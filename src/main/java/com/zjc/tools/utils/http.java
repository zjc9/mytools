package com.zjc.tools.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class http {



    public String sendRequest(String u,String requestType)  {
        String result="";
        try {
            URL url= new URL(u);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            result = this.send(urlConnection, requestType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    public String sendRequest(String u, String requestType, Map headers)  {
        String result="";
        try {
            URL url= new URL(u);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Set<String> set = headers.keySet();
            for (String key:set) {
                urlConnection.setRequestProperty(key, (String) headers.get(key));
            }
            result = this.send(urlConnection, requestType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String sendRequest(String url, String requestType, Proxy proxy){
        String result="";
        try {
            URL url1 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection(proxy);
            result = this.send(urlConnection, requestType);
        } catch (IOException e) {

        }

        return result;
    }
    public String sendRequest(String u, String requestType, Map headers,Proxy proxy)  {
        String result="";
        try {
            URL url= new URL(u);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(proxy);
            Set<String> set = headers.keySet();
            for (String key:set) {
                urlConnection.setRequestProperty(key, (String) headers.get(key));
            }
            result = this.send(urlConnection, requestType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    private String send(HttpURLConnection h, String requestType) {
        BufferedReader bufferedReader = null;
        try {
            h.setRequestMethod(requestType);
            Object content = h.getContent().toString();
            System.out.println("content="+content);
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
        Proxy proxy = new Proxy(Proxy.Type.HTTP,addr);
        String get =http.sendRequest("http://127.0.0.1:8080/?name=Bob&age=25", "GET");
        String get1 = http.sendRequest("http://127.0.0.1:8080/?name=Bob&age=25", "GET", proxy);
        System.out.println(get1);
        System.out.println(get);
    }
}
