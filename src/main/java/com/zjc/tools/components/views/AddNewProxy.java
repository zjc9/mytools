package com.zjc.tools.components.views;

import com.zjc.tools.components.actions.AddProxyAction;

import javax.swing.*;
import java.awt.*;

public class AddNewProxy extends JFrame {
    public AddNewProxy() {
        super("添加代理");
        this.setSize(200, 100);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3,3));
        JLabel ipLabel= new JLabel("ip");
        JTextField ip = new JTextField();
        ip.setColumns(2);
        JLabel portLabel= new JLabel("port");
        JTextField port = new JTextField();
        port.setColumns(2);
        jPanel.add(ipLabel);
        jPanel.add(ip);
        jPanel.add(portLabel);
        jPanel.add(port);
        JButton button = new JButton("添加");
        AddProxyAction addProxyAction = new AddProxyAction();
        button.addActionListener(addProxyAction.addProxy(jPanel));
        jPanel.add(button);
        this.add(jPanel);
        this.setVisible(true);    //设置窗口是否可见
        this.setLocationRelativeTo(null);
        this.setResizable(false);

    }
}