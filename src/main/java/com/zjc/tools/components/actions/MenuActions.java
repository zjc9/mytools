package com.zjc.tools.components.actions;

import com.zjc.tools.components.views.AddNewProxy;
import com.zjc.tools.utils.http;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class MenuActions {
    http h =http.getInstance();
    public ActionListener addProxy() {
        return e -> {
//            JMenuItem source = (JMenuItem) e.getSource();
//            JRootPane pane = source.getRootPane();
//            System.out.println(pane);
//            AddNewProxy addNewProxy = new AddNewProxy();
            AddNewProxy addNewProxy = new AddNewProxy();
            addNewProxy.setVisible(true);
        };
    }

    public ActionListener chooseProxy() {
        return e -> {
            System.out.println(e);
            if (e.getSource() instanceof JRadioButton) {

                JRadioButton j = (JRadioButton) e.getSource();
                String s = j.getText();
                if (s.equals("关闭")) {
                    h.closeGlobalProxy();
                } else {
                    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(s.split(":")[0], Integer.parseInt(s.split(":")[1])));
                    h.setGlobalProxy(proxy);
                }
                System.out.println(j.getText());
            }

        };
    }
}
