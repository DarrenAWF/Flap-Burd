package com.mycompany.finalproject_flappybird_darren;

import java.awt.*;
import javax.swing.*;
import java.util.Random;

/* Name: Darren Drew
 * Date: June 8th, 2022
 * Desc: Flap Burd 2: The main menu
 */
public class menu extends javax.swing.JFrame {
    
    public menu() {
        initComponents();
        setLocationRelativeTo(null);
        Random random = new Random();
        //RANDOM SUBTITLE
        int bgMenu = random.nextInt(1,8);
        switch (bgMenu){
            case 1:
                bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FlapBurd_Menu.png")));
                break;
            case 2:
                bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FlapBurd_Menu2.png")));
                break;
            case 3:
                bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FlapBurd_Menu3.png")));
                break;
            case 4:
                bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FlapBurd_Menu4.png")));
                break;
            case 5:
                bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FlapBurd_Menu5.png")));
                break;
            case 6:
                bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FlapBurd_Menu6.png")));
                break;
            case 7:
                bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FlapBurd_Menu7.png")));
                break;
        }
        //BUTTON COLOURS
        Color titleColor = new Color(247, 247, 165);
        Color skyColor = new Color(110, 190, 201);
        start.setBackground(skyColor);
        start.setForeground(titleColor);
        start.setBorder(BorderFactory.createLineBorder(titleColor));
        start.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        exit.setBackground(skyColor);
        exit.setForeground(titleColor);
        exit.setBorder(BorderFactory.createLineBorder(titleColor));
        exit.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        controls.setBackground(skyColor);
        controls.setForeground(titleColor);
        controls.setBorder(BorderFactory.createLineBorder(titleColor));
        controls.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        start = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        controls = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        start.setText("Play");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });
        getContentPane().add(start, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 440, 200, 80));

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 550, 200, 80));

        controls.setText("Controls");
        controls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controlsActionPerformed(evt);
            }
        });
        getContentPane().add(controls, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 550, 200, 80));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FlapBurd_Menu2.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        main open = new main();
        //open.setVisible(true);
        dispose();
    }//GEN-LAST:event_startActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void controlsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_controlsActionPerformed
        controls open = new controls();
        open.setVisible(true);
        open.addKeyListener(open);
        open.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_controlsActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton controls;
    private javax.swing.JButton exit;
    private javax.swing.JButton start;
    // End of variables declaration//GEN-END:variables
}
