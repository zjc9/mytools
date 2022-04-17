package com.zjc.tools;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import com.zjc.tools.components.actions.TreeActions;
import com.zjc.tools.components.actions.acions;
import com.zjc.tools.components.views.menu;

import java.awt.*;

public class start extends JFrame {
    public  start()
    {
        JFrame main = new JFrame();
        main.setLayout(new BorderLayout());

        menu menu = new menu();
        main.setJMenuBar(menu.proxyMenu());
        main.setIconImage(new ImageIcon("1.png").getImage());
        main.setBackground(Color.white);
        main.setTitle("tools");    //设置显示窗口标题
        main.setSize(800,400);    //设置窗口显示尺寸
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //置窗口是否可以关闭
        main.setVisible(true);    //设置窗口是否可见
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        main.add(westJPanel(main), BorderLayout.WEST);
    }
    private  JPanel westJPanel(JFrame jp){
        TreeActions treeActions = new TreeActions();
        JPanel panel = new JPanel();
        panel.setSize(300,400);
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("工具包");
        String software[][]=new String[3][];
        software[0]=new String[]{"CVE_2022_22965","CVE_2022_22963","CVE-2022-22947"};
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
        tree.addTreeSelectionListener(treeActions.treeAction(tree,jp));
        panel.add(tree);
        panel.setVisible(true);
        return panel;
    }
    public static void main(String[] agrs)
    {
        new start();    //创建一个实例化对象
    }
}
