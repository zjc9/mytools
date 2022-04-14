package com.zjc.tools.components.actions;

import com.zjc.tools.components.config.proxy.ProxyConfig;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class AddProxyAction {
    public ActionListener addProxy(JPanel jPanel) {
        return e ->{
           JTextField ipt= (JTextField) jPanel.getComponent(1);
            JTextField portt= (JTextField) jPanel.getComponent(3);
            String ip = ipt.getText();
            String port = portt.getText();
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, Integer.parseInt(port)));
            ProxyConfig proxyConfig = ProxyConfig.getP();
            proxyConfig.add(proxy);
            System.out.println(ip + port);
        };
    }
}
