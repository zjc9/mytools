package com.zjc.tools.components.views;

import javax.swing.*;

public class CVE_2022_22963_views implements CVEviews {

    @Override
    public JPanel show() {
        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel("123");
        jPanel.add(jLabel);
        jPanel.setVisible(true);
        return jPanel;
    }
}
