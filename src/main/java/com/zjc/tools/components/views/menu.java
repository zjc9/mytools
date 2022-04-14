package com.zjc.tools.components.views;

import com.zjc.tools.components.actions.MenuActions;
import com.zjc.tools.components.config.proxy.MyProxy;
import com.zjc.tools.components.config.proxy.ProxyConfig;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @Author zhangjiucheng
 * @create 2022/4/14 01:58
 */
public class menu {
    public JMenuBar proxyMenu() {
        MenuActions menuAction = new MenuActions();
        ProxyConfig proxyConfig = ProxyConfig.getP();
        List<MyProxy> proxies = proxyConfig.getProxies();

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("代理");
        jMenu.setPreferredSize(new Dimension(100,25));
        JRadioButton c1 = new JRadioButton("关闭",true);//只传了两个参数
        c1.addActionListener(menuAction.chooseProxy());
        JMenuItem item1_1 = new JMenuItem();
        item1_1.setPreferredSize(new Dimension(100,25));
        item1_1.add(c1);
        jMenu.add(item1_1);
        ButtonGroup group = new ButtonGroup();
        for (MyProxy m:proxies) {
            JMenuItem jMenuItem = new JMenuItem();
            jMenuItem.setPreferredSize(new Dimension(100,25));
            JRadioButton c = new JRadioButton(m.getProxy().toString().split("/")[1], m.isUsed());
            c.addActionListener(menuAction.chooseProxy());
            jMenuItem.add(c);
            group.add(c);
            jMenu.add(jMenuItem);
            System.out.println(m.getProxy().toString());
        }
        group.add(c1);
        JMenuItem item1_2 = new JMenuItem();
        JMenuItem item1_3 = new JMenuItem("添加代理");
        item1_3.addActionListener(menuAction.addProxy());
        item1_2.setPreferredSize(new Dimension(100,25));

        jMenu.add(item1_2);
        jMenu.addSeparator();
        jMenu.add(item1_3);
        jMenuBar.add(jMenu);
        return jMenuBar;
    }
}
