package com.zjc.tools.components.actions;

import com.zjc.tools.poc.spring_rce;
import com.zjc.tools.utils.http;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class acions {
    public  int pocorexp=0;
    public ActionListener sendReuest(JPanel jp){
        return e -> {

//            int componentCount1 = jp.getComponentCount();
      //      JLabel jl = (JLabel) jp.getComponent(3);
          JTextField jt= (JTextField) jp.getComponent(3);
            JTextField jt1= (JTextField) jp.getComponent(5);
            JTextArea jt2= (JTextArea) jp.getComponent(7);
            String cmd = jt.getText().trim();
            String url = jt1.getText().trim();
            String result = jt2.getText();
            System.out.println(cmd+url+result);


//            System.out.println(text);
           String pa="^http(s)?.*/$";
          boolean matches = Pattern.matches(pa, url);
            System.out.println(matches);
           if(!matches){
               url += "/";
           }
            spring_rce spring_rce = new spring_rce();
            String result1="";
            if (pocorexp == 1) {
               result1 = spring_rce.exp(url, cmd);
            }else {
                result1 = spring_rce.poc(url);
            }
            System.out.println("result="+result1);
            System.out.println(url);
            jt2.setText(result1);
//            int i = Integer.parseInt(jl.getText());
//            i++;
//            jl.setText(String.valueOf(i));
//            System.out.println("click");
        };
    }
    public ActionListener cmb(JPanel jp) {
        return e ->{
            if (e.getSource() instanceof JComboBox) {
                JTextField jt= (JTextField) jp.getComponent(3);
                JLabel jLabel= (JLabel) jp.getComponent(2);
               JComboBox<String>  comboBox= (JComboBox<String>) e.getSource();
                String s = comboBox.getSelectedItem().toString();
                if (s.equals("POC")) {
                    pocorexp = 0;

                    jt.setVisible(false);
                    jLabel.setVisible(false);
                }else {
                    pocorexp = 1;
                    jt.setVisible(true);
                    jLabel.setVisible(true);
                }
                System.out.println(s);
            }
        };
    }

}
