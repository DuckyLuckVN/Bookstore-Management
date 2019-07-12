/*
 * Created by JFormDesigner on Thu Jul 11 22:30:15 ICT 2019
 */

package com.ducky.view;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class test extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Nguyễn Đại Hào
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public test() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nguyễn Đại Hào
        textField1 = new JTextField();
        textField2 = new JTextField();
        button1 = new JButton();

        //======== this ========
        setTitle("Day la title");
        setBackground(new Color(231, 58, 58));
        setForeground(Color.orange);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- button1 ----
        button1.setText("tester");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(textField2, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                        .addComponent(textField1, GroupLayout.Alignment.TRAILING)
                        .addComponent(button1, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
                    .addGap(24, 24, 24))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public static void main(String[] args) {
        test t = new test();
        t.setVisible(true);
    }

}
