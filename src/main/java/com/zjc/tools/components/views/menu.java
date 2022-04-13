package com.zjc.tools.components.views;

import javax.swing.*;

/**
 * @Author zhangjiucheng
 * @create 2022/4/14 01:58
 */
public class menu {
    public JMenuBar addMeun() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("代理");
        JRadioButton c1 = new JRadioButton("关闭",true);//只传了两个参数
        JRadioButton c2 = new JRadioButton("127.0.0.1:8888");
        ButtonGroup group = new ButtonGroup();
        group.add(c1);
        group.add(c2);

        JMenuItem item1_1 = new JMenuItem();
        item1_1.add(c1);
        JMenuItem item1_2 = new JMenuItem();
        JMenuItem item1_3 = new JMenuItem("添加代理");
        item1_2.add(c2);

        jMenu.add(item1_1);
        jMenu.add(item1_2);
        jMenu.addSeparator();
        jMenu.add(item1_3);
        jMenuBar.add(jMenu);
        return jMenuBar;
    }
}
