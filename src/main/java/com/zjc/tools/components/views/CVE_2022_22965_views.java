package com.zjc.tools.components.views;

import com.zjc.tools.components.actions.acions;

import javax.swing.*;

public class CVE_2022_22965_views implements CVEviews {
    public JPanel show() {
        System.out.println("调用成功");
        acions acions = new acions();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        //label
        JLabel jl=new JLabel("类型");    //创建一个标签
        jl.setBounds(10, 10, 50, 25);
        jPanel.add(jl);
        //下拉列表
        JComboBox cmb=new JComboBox();    //创建JComboBox
        cmb.addItem("POC");
        cmb.addItem("EXP");
        cmb.addActionListener(acions.cmb(jPanel));
        cmb.setBounds(60, 10, 80, 25);
        jPanel.add(cmb);
        //命令
        JLabel jlcmd=new JLabel("命令");    //创建一个标签
        jlcmd.setBounds(140, 10, 30, 25);
        jlcmd.setVisible(false);
        jPanel.add(jlcmd);
        //命令文本域
        JTextField userText1 = new JTextField(20);
        userText1.setBounds(170, 10, 80, 25);
        userText1.setVisible(false);
        jPanel.add(userText1);
        //url
        //命令
        JLabel url=new JLabel("url");    //创建一个标签
        url.setBounds(250, 10, 20, 25);
        jPanel.add(url);
        //文本域
        JTextField userText = new JTextField(20);
        userText.setText("http://192.168.199.229:8080");
        userText.setBounds(270, 10, 300, 25);
        jPanel.add(userText);
        //按钮
        JButton button = new JButton("发送");
        button.setBounds(570, 10, 80, 25);
        button.addActionListener(acions.sendReuest(jPanel));
        jPanel.add(button);
        //文本域
        JTextArea jTextArea = new JTextArea("result");
        jTextArea.setBounds(10, 35, 650, 60);
        jPanel.add(jTextArea);
        return jPanel;
    }

    public void test() {
        System.out.println("测试成功");
    }
}
