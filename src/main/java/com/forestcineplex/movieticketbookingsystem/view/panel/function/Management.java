/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.view.panel.function;

import com.forestcineplex.movieticketbookingsystem.data.DataConnection;
import com.forestcineplex.movieticketbookingsystem.model.OrderFoodModel;
import com.forestcineplex.movieticketbookingsystem.model.User;
import com.forestcineplex.movieticketbookingsystem.view.frame.MainSystem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author LENOVO
 */
public class Management extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    private MainSystem mainSystem;
    private HashMap<String,User> users;
    
    public User getUser() {
        return mainSystem.getUser();
    }
    
    public void loadUserList() {
        users = new HashMap<>();
        
        try (ResultSet resultSet = DataConnection.executeQuery("SELECT * FROM user")) {
            if (resultSet != null && resultSet.next()) {
                do {
                    String username = resultSet.getString("User");
                    String passwordHash = resultSet.getString("PasswordHash");
                    String role = resultSet.getString("Role");
                    String name = resultSet.getString("FullName");
                    String gender = resultSet.getString("Gender");
                    Date birthDate = resultSet.getDate("BirthDate");
                    String address = resultSet.getString("Address");
                    String phone = resultSet.getString("Phone");
                    String mail = resultSet.getString("Mail");
                    double salary = resultSet.getDouble("Salary");
                    
                    users.put(username, new User(username, passwordHash, role, name, gender, salary, address, phone, mail, birthDate));
                    
                    
                } while (resultSet.next());
                
                
            } else {
                System.out.println("null");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void loadMainAdmin() {
        mainSystem.loadMainAdmin();
    }

    
    public void loadTable() {
        loadUserList();
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel defaultTableModel = (DefaultTableModel)jTable1.getModel();
        defaultTableModel.setRowCount(0);
        for (User user : users.values()) {
            defaultTableModel.addRow(new Object[] {user.getUser(), user.getFullname(), user.getRole()});
        }
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(defaultTableModel);
        rowSorter.toggleSortOrder(2);
        jTable1.setRowSorter(rowSorter);
    }
    public Management() {
        initComponents();
        loadTable();
        
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }
    
    public Management(MainSystem mainSystem) {
        
        this.mainSystem = mainSystem;
        initComponents();
        loadTable();
        
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        
    }
    
    private void filter() {
        String search = jTextField1.getText();
        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)"+ Pattern.quote(search),0,1);
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
        ((TableRowSorter<DefaultTableModel>) jTable1.getRowSorter()).setRowFilter(rowFilter);
    }
    
    public void displayUser(User user) {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        jLabelUser.setText(user.getUser());
        jLabelRole.setText(user.getRole());
        jLabelFullName.setText(user.getFullname());
        jLabelGender.setText(user.getGender());
        jLabelBirthDate.setText(dateFormat.format(user.getBirthDate()));
        jLabelSalary.setText(decimalFormat.format(user.getSalary())+"$");
        jLabelPhone.setText(user.getPhone());
        jLabelMail.setText(user.getMail());
        jLabelAddress.setText(user.getAddress()); 
        String role = user.getRole();
        String gender= user.getGender();
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
    
    public void displayEditUser() {
        EditUser editUser = new EditUser(this, getUserSelect());
        //jDialogEdit.getContentPane().add(editUser);
        jDialogEdit.setContentPane(editUser);
        jDialogEdit.setTitle("Edit Users");
        jDialogEdit.setLocation(350, 150);
        jDialogEdit.setSize(720,670);
        jDialogEdit.setResizable(false);
        jDialogEdit.setVisible(true);
    }
    
    public void disposeEditUser() {
        jDialogEdit.dispose();
        jDialogEdit.getContentPane().removeAll();
        jDialogEdit.repaint();
    }
    
    public void displayAddUser() {
        AddUser addUser = new AddUser(this);
        jDialogAdd.getContentPane().add(addUser);
        jDialogAdd.setTitle("Add Users");
        jDialogAdd.setLocation(350, 150);
        jDialogAdd.setSize(720,670);
        jDialogAdd.setResizable(false);
        jDialogAdd.setVisible(true);
    }
    
    public void disposeAddUser() {
        jDialogAdd.dispose();
        jDialogAdd.getContentPane().removeAll();
        jDialogAdd.repaint();
    }
    
    public void setDefault() {
        jLabelAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userdefault.png")));
        jLabelUser.setText("");
        jLabelFullName.setText("");
        jLabelRole.setText("");
        jLabelGender.setText("");
        jLabelSalary.setText("");
        jLabelBirthDate.setText("");
        jLabelMail.setText("");
        jLabelPhone.setText("");
        jLabelAddress.setText("");
        jTable1.clearSelection();

    }
    
    public User getUserSelect() {
        int row = jTable1.getSelectedRow();
        row = jTable1.convertRowIndexToModel(row);
        String username =  (String)jTable1.getModel().getValueAt(row, 0);
        User user = users.get(username);
        return user;
    }
    
    public void selectRow(User user) {
        for (int i = 0; i<jTable1.getRowCount(); i++) {
            if (user.getUser().equals(jTable1.getModel().getValueAt(i, 0))) {
                jTable1.setRowSelectionInterval(i,i);
                return;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogAdd = new javax.swing.JDialog(mainSystem,"Hello",true);
        jOptionPaneConfirm = new javax.swing.JOptionPane();
        jDialogEdit = new javax.swing.JDialog(mainSystem,"Hello",true);
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabelAvatar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelMail = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabelPhone = new javax.swing.JLabel();
        jLabelFullName = new javax.swing.JLabel();
        jLabelAddress = new javax.swing.JLabel();
        jLabelBirthDate = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();
        jLabelRole = new javax.swing.JLabel();
        jLabelGender = new javax.swing.JLabel();
        jLabelSalary = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabelMess = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 204));
        setMinimumSize(new java.awt.Dimension(650, 650));
        setPreferredSize(new java.awt.Dimension(970, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"admin", "Ngo Anh Khoi", "Admin"},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "User", "Full Name", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(612, 0, 360, 660));

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField1.setToolTipText("Search for Username or FullName");
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 530, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/find.png"))); // NOI18N
        jLabel1.setToolTipText("Search for Username or FullName");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, -1));

        jLabelAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userdefault.png"))); // NOI18N
        jPanel2.add(jLabelAvatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Role:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, -1, -1));

        jLabelMail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(jLabelMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Gender:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Fullname:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Address:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Salary:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Phone:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Birth Date:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Mail:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Username:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        jLabelPhone.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(jLabelPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, -1, -1));

        jLabelFullName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(jLabelFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, -1, -1));

        jLabelAddress.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(jLabelAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, -1, -1));

        jLabelBirthDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(jLabelBirthDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, -1, -1));

        jLabelUser.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(jLabelUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, -1, -1));

        jLabelRole.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(jLabelRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 270, -1, -1));

        jLabelGender.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(jLabelGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, -1, -1));

        jLabelSalary.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(jLabelSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 570, 280));

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 600, 70, -1));

        jButtonEdit.setBackground(new java.awt.Color(153, 204, 255));
        jButtonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edit.png"))); // NOI18N
        jButtonEdit.setToolTipText("Edit");
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 600, 70, 40));

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/remove.png"))); // NOI18N
        jButton3.setToolTipText("Remove");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 600, 70, 40));

        jLabelMess.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelMess.setForeground(new java.awt.Color(255, 51, 51));
        jLabelMess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMess.setText("Please select a user");
        jPanel2.add(jLabelMess, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 540, 540, 40));
        jLabelMess.setVisible(false);

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 970, 660));
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
     
        int row = jTable1.getSelectedRow();
        if (row == -1)
            return;
        else {
            row = jTable1.convertRowIndexToModel(row);
            jLabelMess.setVisible(false);
            String username =  (String)jTable1.getModel().getValueAt(row, 0);
            User user = users.get(username);

            
            displayUser(user);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            jLabelMess.setVisible(true);
            return;
        } else {
            row = jTable1.convertRowIndexToModel(row);
            displayEditUser();
        }
    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        displayAddUser();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            jLabelMess.setText("Please select a user");
            jLabelMess.setVisible(true);
            return;
        } else {
            row = jTable1.convertRowIndexToModel(row);
            String username =  (String)jTable1.getModel().getValueAt(row, 0);  
            if (username.equals(this.getUser().getUser())) {
                jLabelMess.setText("Please do not choose oneself");
                jLabelMess.setVisible(true);
                return;
            }
            int result = jOptionPaneConfirm.showConfirmDialog(this,"Delete","", jOptionPaneConfirm.YES_NO_OPTION, jOptionPaneConfirm.QUESTION_MESSAGE);
            if (result == jOptionPaneConfirm.YES_OPTION) {
                DataConnection.executeUpdate("DELETE FROM user WHERE User = ?", username);
                loadTable();
                setDefault();
            } 
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JDialog jDialogAdd;
    private javax.swing.JDialog jDialogEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAddress;
    private javax.swing.JLabel jLabelAvatar;
    private javax.swing.JLabel jLabelBirthDate;
    private javax.swing.JLabel jLabelFullName;
    private javax.swing.JLabel jLabelGender;
    private javax.swing.JLabel jLabelMail;
    private javax.swing.JLabel jLabelMess;
    private javax.swing.JLabel jLabelPhone;
    private javax.swing.JLabel jLabelRole;
    private javax.swing.JLabel jLabelSalary;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JOptionPane jOptionPaneConfirm;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
