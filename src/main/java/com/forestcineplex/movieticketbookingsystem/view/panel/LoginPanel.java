/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.view.panel;

import com.forestcineplex.movieticketbookingsystem.data.DataConnection;
import com.forestcineplex.movieticketbookingsystem.model.User;
import com.forestcineplex.movieticketbookingsystem.view.frame.LobbySystem;
import com.forestcineplex.movieticketbookingsystem.view.frame.MainSystem;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author LENOVO
 */
public class LoginPanel extends javax.swing.JPanel {

    /**
     * Creates new form LoginPanel
     */
    
    private boolean buttonVisible = false;
    private LobbySystem lobbySystem;
    private User user;
    
    public LoginPanel(LobbySystem lobbySystem) {
        this.lobbySystem = lobbySystem;
        initComponents();
    }
    
    public LoginPanel() {
        initComponents();
    }
    
    public void login(String role) {
        this.lobbySystem.setVisible(false);
        MainSystem mainSystem = new MainSystem(user);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelUser = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabelVisible = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabelPassword = new javax.swing.JLabel();
        jLabelUsers = new javax.swing.JLabel();
        jLabelLogin = new javax.swing.JLabel();
        jLabelMess = new javax.swing.JLabel();
        jLabelShape = new javax.swing.JLabel();
        javax.swing.JLabel jLabelBackground = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User.png"))); // NOI18N
        add(jLabelUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 160, -1, -1));

        jTextField1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 340, 300, 35));

        jLabelVisible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VisibleOff.png"))); // NOI18N
        jLabelVisible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelVisibleMouseClicked(evt);
            }
        });
        add(jLabelVisible, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 420, 40, 40));

        jPasswordField1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jPasswordField1.setActionCommand("<Not Set>");
        jPasswordField1.setAlignmentX(0.4F);
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 420, 300, 35));

        jLabelPassword.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelPassword.setForeground(new java.awt.Color(0, 102, 51));
        jLabelPassword.setText("Password");
        add(jLabelPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, -1, -1));

        jLabelUsers.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelUsers.setForeground(new java.awt.Color(0, 102, 51));
        jLabelUsers.setText("Username");
        add(jLabelUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, -1, -1));

        jLabelLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LoginButton.png"))); // NOI18N
        jLabelLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLoginMouseClicked(evt);
            }
        });
        add(jLabelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 520, -1, -1));

        jLabelMess.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabelMess.setForeground(new java.awt.Color(153, 0, 0));
        jLabelMess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(jLabelMess, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 480, 400, -1));

        jLabelShape.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shape1.png"))); // NOI18N
        add(jLabelShape, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 60, 430, 640));

        jLabelBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Background.jpg"))); // NOI18N
        add(jLabelBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelVisibleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelVisibleMouseClicked
        if (buttonVisible == false) {
            buttonVisible = true;
            jPasswordField1.setEchoChar((char)0);
            jLabelVisible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VisibleOn.png")));
        } else {
            buttonVisible = false;
            jPasswordField1.setEchoChar('\u2022');
            jLabelVisible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VisibleOff.png")));
        }
    }//GEN-LAST:event_jLabelVisibleMouseClicked
    private void checkUser() {
        String user = jTextField1.getText();
        String passwordHash = jPasswordField1.getText();
        
        String sqlQuery = "SELECT * FROM user WHERE User = ?";
        
        try (ResultSet resultSet = DataConnection.executeQuery(sqlQuery, user)) {
            if (resultSet != null && resultSet.next()) {
                String user1 = resultSet.getString("User");
                String passwordHash1 = resultSet.getString("PasswordHash");
                String role = resultSet.getString("Role");
                
                String fullName = resultSet.getString("FullName");
                String gender = resultSet.getString("Gender");
                Date birthDate = resultSet.getDate("BirthDate");
                String address = resultSet.getString("Address");
                String phone = resultSet.getString("Phone");
                String mail = resultSet.getString("Mail");
                double salary = resultSet.getDouble("Salary");
                if (BCrypt.checkpw(passwordHash, passwordHash1)) {
                    this.user = new User(user1,passwordHash1,role,fullName,gender,salary,address,phone,mail,birthDate);
                    this.jLabelLogin.setVisible(false);
                    login(role);
                } else {
                    jLabelMess.setText("Incorrect password");
                }
   
            } else {
                jLabelMess.setText("User not found");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc log lỗi
        }
        
    }
    private void jLabelLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLoginMouseClicked
        checkUser();
    }//GEN-LAST:event_jLabelLoginMouseClicked

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            checkUser();
    }//GEN-LAST:event_jPasswordField1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelMess;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelShape;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JLabel jLabelUsers;
    private javax.swing.JLabel jLabelVisible;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}