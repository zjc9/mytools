package com.zjc.tools;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import com.zjc.tools.components.actions.acions;

import java.awt.*;

public class start extends JFrame {
    public  start()
    {
        JFrame main = new JFrame();
        main.setLayout(new BorderLayout());
        acions acions = new acions();
        main.setIconImage(new ImageIcon("1.png").getImage());
        main.setBackground(Color.white);
        main.setTitle("tools");    //设置显示窗口标题
        main.setSize(800,400);    //设置窗口显示尺寸
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //置窗口是否可以关闭
        main.add(westJPanel(), BorderLayout.WEST);

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


        main.add(jPanel);
        main.setVisible(true);    //设置窗口是否可见
        main.setLocationRelativeTo(null);
        main.setResizable(false);
    }
    private  JPanel westJPanel(){
        JPanel panel = new JPanel();
        panel.setSize(300,400);
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("工具包");
        String software[][]=new String[3][];
        software[0]=new String[]{"CVE-2022-22965","CVE-2022-22963","CVE-2022-22947"};
        software[1]=new String[]{"nginx_parsing_vulnerability","insecure-configuration"};
        software[2]=new String[]{"1.2.47-rce","1.2.24-rce"};
        String zjj[]={"spring","nginx","fastjon"};//中间件名字
        DefaultMutableTreeNode node=null;
        DefaultMutableTreeNode childNode=null;
        int length=0;
        for(int i=0;i<3;i++)
        {
            length=software[i].length;
            node=new DefaultMutableTreeNode(zjj[i]);
            for (int j=0;j<length;j++)
            {
                childNode=new DefaultMutableTreeNode(software[i][j]);
                node.add(childNode);
            }
            root.add(node);
        }
        JTree tree=new JTree(root);
        tree.setPreferredSize(new Dimension(150,400));
        panel.setPreferredSize(new Dimension(150,400));
        panel.add(tree);
        panel.setVisible(true);
        return panel;
    }
    public static void main(String[] agrs)
    {
        new start();    //创建一个实例化对象
    }
}
