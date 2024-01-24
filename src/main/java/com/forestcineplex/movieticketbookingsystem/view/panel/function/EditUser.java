/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.view.panel.function;

import com.forestcineplex.movieticketbookingsystem.data.DataConnection;
import com.forestcineplex.movieticketbookingsystem.model.User;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.border.LineBorder;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author LENOVO
 */
public class EditUser extends javax.swing.JPanel {

    /**
     * Creates new form AddUser
     */
    private User user;
    private Management management;
    private LineBorder errorBorder = new LineBorder(Color.RED,1);
    private LineBorder defaultBorder = new LineBorder(Color.LIGHT_GRAY);
    private boolean buttonVisible = false;
    private boolean checkChangePass = false;
    
    public EditUser() {
        initComponents();
    }
    
    public EditUser(Management management, User user) {
        this.management = management;
        this.user = user;
        initComponents();
        setDefault();
    }
    
    public void setDefaultBorder() {
        jTextFielUsername.setBorder(defaultBorder);
        jTextFieldFullName.setBorder(defaultBorder);
        jTextFieldPhone.setBorder(defaultBorder);
        jTextFieldMail.setBorder(defaultBorder);
        jTextFieldSalary.setBorder(defaultBorder);
        jTextFieldAddress.setBorder(defaultBorder);
        jDateChooserBirthDate.setBorder(defaultBorder);
        jPasswordField1.setBorder(defaultBorder);
    }
    
    public void setDefault() {
        setDefaultBorder();
        this.user = management.getUserSelect();
        this.jTextFielUsername.setText(user.getUser());
        this.jTextFieldFullName.setText(user.getFullname());
        this.jTextFieldMail.setText(user.getMail());
        this.jTextFieldPhone.setText(user.getPhone());
        this.jTextFieldSalary.setText(String.valueOf(user.getSalary()));
        this.jTextFieldAddress.setText(user.getAddress());
        this.jDateChooserBirthDate.setDate(user.getBirthDate());
        this.jPasswordField1.setText("Password");
        this.jComboBoxRole.setSelectedItem(user.getRole());
        this.jComboBoxGender.setSelectedItem(user.getGender());
    }
    
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9_]{3,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    
    public static boolean isRealNumber(String input) {
        try {
            double number = Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\+?[0-9]{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }
    
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$";
        return password.matches(regex);
    }
    
    
    
    public void CheckAddUser() {
        String username = jTextFielUsername.getText();
        if (isValidUsername(username)==false) {
            jTextFielUsername.setText("");
            jTextFielUsername.setBorder(errorBorder);
            jLabelError.setText("Invalid username");
            return;
        }
        
        String fullname = jTextFieldFullName.getText();
        if (fullname.equalsIgnoreCase("")) {
            jTextFieldFullName.setText(user.getFullname());
            jTextFieldFullName.setBorder(errorBorder);
            jLabelError.setText("FullName cannot be empty");
            return;
        }
        
        Date birthDate = jDateChooserBirthDate.getDate();
        if (birthDate == null) {
            jDateChooserBirthDate.setBorder(errorBorder);
            jLabelError.setText("Birth date cannot be empty");
            return;
        }
        
        String Salary = jTextFieldSalary.getText();
        if (isRealNumber(Salary)== false) {
            jTextFieldSalary.setText(user.getSalary()+"");
            jTextFieldSalary.setBorder(errorBorder);
            jLabelError.setText("Invalid salary");
            return;
        }
        
        String Mail = jTextFieldMail.getText();
        if (isValidEmail(Mail) == false) {
            jTextFieldMail.setText(user.getMail());
            jTextFieldMail.setBorder(errorBorder);
            jLabelError.setText("Invalid mail");
            return;
        }
        
        String Phone = jTextFieldPhone.getText();
        if (isValidPhoneNumber(Phone) == false) {
            jTextFieldPhone.setText(user.getPhone());
            jTextFieldPhone.setBorder(errorBorder);
            jLabelError.setText("Invalid phone");
            return;
        }
        
        String Address = jTextFieldAddress.getText();
        if (Address.equalsIgnoreCase("")) {
            jTextFieldAddress.setText(user.getAddress());
            jTextFieldAddress.setBorder(errorBorder);
            jLabelError.setText("Address cannot be empty");
            return;
        }
        String Role = (String)jComboBoxRole.getSelectedItem();
        String Gender = (String)jComboBoxGender.getSelectedItem();
        String passwordHash = "";
        if (checkChangePass) {
            String password = jPasswordField1.getText();
            if (isValidPassword(password)==false) {
                jPasswordField1.setText("");
                jPasswordField1.setBorder(errorBorder);
                jLabelError.setText("At least 6 characters, including at least 1 letter, 1 digit, and 1 special character");
                return;
            } 
            passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        } else { 
            passwordHash = user.getPasswordHash();
        }
        this.user = new User(username, passwordHash, Role, fullname, Gender, Double.valueOf(Salary), Address, Phone, Mail, birthDate);
        //setDefault();
        jLabelSuccess.setVisible(true);
        Edit(user);
        management.loadTable();
        management.loadMainAdmin();
        
    }
    public void changeAvatar() {
        String role = (String)jComboBoxRole.getSelectedItem();
        String gender =(String)jComboBoxGender.getSelectedItem();
        if (role.equalsIgnoreCase("ADMIN")) {
            if (gender.equalsIgnoreCase("MALE")) {
                jLabelAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin.png")));
            } else {
                jLabelAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/womenAdmin.png")));
            }
        } else {
            if (gender.equalsIgnoreCase("MALE")) {
                jLabelAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/man.png")));
            } else {
                jLabelAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/women.png")));
            }
        }
    }
    public void Edit(User user) {
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String day = simpleDateFormat.format(user.getBirthDate());
        String querySQL = "UPDATE `user` SET " +
        "`User`='" + user.getUser() + "', " +
        "`Role`='" + user.getRole() + "', " +
        "`FullName`='" + user.getFullname() + "', " +
        "`Gender`='" + user.getGender() + "', " +
        "`BirthDate`='" + day + "', " +
        "`Address`='" + user.getAddress() + "', " +
        "`Phone`='" + user.getPhone() + "', " +
        "`Mail`='" + user.getMail() + "', " +
        "`Salary`='" + user.getSalary() + "', " +
        "`PasswordHash`='" + user.getPasswordHash() + "' " +
        "WHERE `User`='" + user.getUser() + "'";
        DataConnection.executeUpdate(querySQL);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelAvatar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldAddress = new javax.swing.JTextField();
        jTextFielUsername = new javax.swing.JTextField();
        jTextFieldPhone = new javax.swing.JTextField();
        jTextFieldMail = new javax.swing.JTextField();
        jComboBoxGender = new javax.swing.JComboBox<>();
        jComboBoxRole = new javax.swing.JComboBox<>();
        jTextFieldFullName = new javax.swing.JTextField();
        jTextFieldSalary = new javax.swing.JTextField();
        jDateChooserBirthDate = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabelVisible = new javax.swing.JLabel();
        jLabelError = new javax.swing.JLabel();
        jLabelSuccess = new javax.swing.JLabel();
        jCheckPass = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(700, 650));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/man.png"))); // NOI18N
        add(jLabelAvatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Username:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Fullname:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Birth Date:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Mail:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("*Password:");
        jLabel8.setToolTipText("Password requirements: At least 6 characters, including at least one letter, one digit, and one special character.");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Role:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Gender:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Salary:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Phone:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, -1, -1));
        jPanel1.add(jTextFieldAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 460, 30));

        jTextFielUsername.setEnabled(false);
        jPanel1.add(jTextFielUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 190, 30));
        jPanel1.add(jTextFieldPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 150, 30));
        jPanel1.add(jTextFieldMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 190, 30));

        jComboBoxGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jComboBoxGender.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxGenderItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBoxGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 90, 30));

        jComboBoxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Employee", "Admin" }));
        jComboBoxRole.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxRoleItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBoxRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 90, 30));
        jPanel1.add(jTextFieldFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 190, 30));

        jTextFieldSalary.setText("0");
        jPanel1.add(jTextFieldSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 150, 30));

        jDateChooserBirthDate.getDateEditor().setEnabled(false);
        jDateChooserBirthDate.setDateFormatString("dd-MM-yyyy");
        jPanel1.add(jDateChooserBirthDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 190, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Address:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jPasswordField1.setToolTipText("Password requirements: At least 6 characters, including at least one letter, one digit, and one special character.");
        jPasswordField1.setEnabled(false);
        jPanel1.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 250, 30));

        jLabelVisible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VisibleOff.png"))); // NOI18N
        jLabelVisible.setVisible(false);
        jLabelVisible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelVisibleMouseClicked(evt);
            }
        });
        jPanel1.add(jLabelVisible, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 280, -1, -1));

        jLabelError.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelError.setForeground(new java.awt.Color(255, 51, 102));
        jLabelError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabelError, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 330, 650, -1));

        jLabelSuccess.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelSuccess.setForeground(new java.awt.Color(0, 153, 0));
        jLabelSuccess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSuccess.setText("Success");
        jPanel1.add(jLabelSuccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 330, 650, -1));
        jLabelSuccess.setVisible(false);

        jCheckPass.setText("Change password");
        jCheckPass.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckPassItemStateChanged(evt);
            }
        });
        jPanel1.add(jCheckPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 280, -1, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 660, 380));

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/remove.png"))); // NOI18N
        jButton3.setToolTipText("Remove");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 580, 70, 40));

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edit.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 580, 70, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        management.disposeEditUser();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        setDefaultBorder();
        CheckAddUser();
        jLabelSuccess.setVisible(false);
        jLabelError.setText("");
        management.selectRow(user);
        management.displayUser(user);
        if (user.getUser().equals(management.getUser().getUser()) && user.getRole().equals("Employee"))
            management.disposeEditUser();
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void jComboBoxRoleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxRoleItemStateChanged
        changeAvatar();
    }//GEN-LAST:event_jComboBoxRoleItemStateChanged

    private void jComboBoxGenderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxGenderItemStateChanged
        changeAvatar();
    }//GEN-LAST:event_jComboBoxGenderItemStateChanged

    private void jCheckPassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckPassItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
                    checkChangePass = true;
                    this.jPasswordField1.setEnabled(true);
                    this.jLabelVisible.setVisible(true);
                } else {
                    checkChangePass = false;
                    this.jPasswordField1.setEnabled(false);
                    this.jLabelVisible.setVisible(false);
                }
    }//GEN-LAST:event_jCheckPassItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckPass;
    private javax.swing.JComboBox<String> jComboBoxGender;
    private javax.swing.JComboBox<String> jComboBoxRole;
    private com.toedter.calendar.JDateChooser jDateChooserBirthDate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAvatar;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JLabel jLabelSuccess;
    private javax.swing.JLabel jLabelVisible;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextFielUsername;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldFullName;
    private javax.swing.JTextField jTextFieldMail;
    private javax.swing.JTextField jTextFieldPhone;
    private javax.swing.JTextField jTextFieldSalary;
    // End of variables declaration//GEN-END:variables
}
