package com.zjc.tools.components.actions;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EventListener;

public class TreeActions {

    public TreeSelectionListener treeAction(JTree tree,JFrame jf) {
        return e ->{
            Component[] components = jf.getContentPane().getComponents();
            int length = components.length;
            if (length > 1) {
                jf.remove(components[length - 1]);
            }
//            JPanel jPanel = new JPanel();
//            JLabel jl = new JLabel("123");
//            jl.setBounds(200, 10, 50, 25);
//            jPanel.add(jl);
//            jPanel.setVisible(true);
//            jFrame.add(jPanel);
//            jFrame.revalidate();
            Object[] path = tree.getSelectionPath().getPath();
            int l = path.length;
            if (l == 3) {
                for (int i = 0; i < l; i++) {
                    System.out.println(path[i]);
                }
                String s1 = path[1].toString();
                String s2 = path[2].toString();
                String name = "com.zjc.tools.components.views."+ s2+"_views";
                //com.zjc.tools.components.views.CVE_2022_22965_views
                //com.zjc.tools.components.views.spring.CVE_2022_22965_views
                System.out.println(name);
                try {
                    Class<?> aClass = Class.forName(name);
                    Method test = aClass.getMethod("show");
                    JPanel invoke = (JPanel) test.invoke(aClass.newInstance());
                    invoke.setVisible(true);
                    jf.add(invoke);
                    jf.revalidate();
                    System.out.println(invoke);

                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                }


//                jf.remove(components[components.length-1]);

            } else {
//                jf.getComponentAt(new Point());
//                component.setVisible(false);
//                jf.revalidate();
//                System.out.println(component);
//                System.out.println("当前不是根节点");
            }

            System.out.println();
        };
    }
}
