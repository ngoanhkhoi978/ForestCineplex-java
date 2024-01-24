/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.view.panel.function;

import com.forestcineplex.movieticketbookingsystem.data.DataConnection;
import com.forestcineplex.movieticketbookingsystem.model.ForestCineplexModel;
import com.forestcineplex.movieticketbookingsystem.model.OrderFoodModel;
import com.forestcineplex.movieticketbookingsystem.model.User;
import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author LENOVO
 */
public class FoodOrder extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    
    private ArrayList<OrderFoodModel> orderFoodModelList;
    private User user;
    
    
    public void loadTableHistory() {
        
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        
        DefaultTableModel defaultTableModel = (DefaultTableModel)jTableHistory.getModel();
        jTableHistory.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTableHistory.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTableHistory.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTableHistory.getColumnModel().getColumn(3).setPreferredWidth(600);
        
        jTableHistory.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        jTableHistory.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        jTableHistory.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
        jTableHistory.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
        
        defaultTableModel.setRowCount(0);
        for (OrderFoodModel orderFoodModel: orderFoodModelList) {
            defaultTableModel.addRow(new Object[] {orderFoodModel.getTime(), orderFoodModel.getUserOrder(), orderFoodModel.getTotal(), orderFoodModel.getHistory()});
            
        }
    }
    
    public void loadOrderFoodModelList() {
        orderFoodModelList = new ArrayList<>();
        
        try (ResultSet resultSet = DataConnection.executeQuery("SELECT Time, Username, Total, History FROM revenue WHERE Source = 'Food' ORDER BY Time DESC")) {
            if (resultSet != null && resultSet.next()) {
                do {
                    Timestamp time = resultSet.getTimestamp("Time");
                    String userOrder = resultSet.getString("Username");
                    double total = Double.valueOf(resultSet.getString("Total"));
                    String history = resultSet.getString("History");
                    orderFoodModelList.add(new OrderFoodModel(1,time, userOrder, total, history));
                } while (resultSet.next());
                
                
            } else {
                System.out.println("null");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    //public void load
    
    public FoodOrder() {
        loadOrderFoodModelList();
        initComponents();  
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    public FoodOrder(User user) {
        loadOrderFoodModelList();
        setUser(user);
        initComponents();
    }
    
    public String MessTotalFoodOrder() {
        int valuePop1 = (int)jSpinnerPop1.getValue();
        int valuePop2 = (int)jSpinnerPop2.getValue();
        int valueCoca = (int)jSpinnerCoca.getValue();
        int valuePepsi = (int)jSpinnerPepsi.getValue();
        int valueSprite = (int)jSpinnerSprite.getValue();
        
        String mess = "";
        if (valuePop1>0)
            mess += "Pop corn 60 OZ             x " + valuePop1 +" = " +(valuePop1*2)+"$"+"\n\n";
        if (valuePop2>0)
            mess += "Pop corn caramel 60 OZ x " + valuePop2 +" = " +(valuePop2*2.29)+"$"+"\n\n";
        if (valueCoca>0)
            mess += "Coca 32 OZ                   x " + valueCoca +" = " +(valueCoca*1.49)+"$"+"\n\n";
        if (valuePepsi>0)
            mess += "Pepsi 32 OZ                   x " + valuePepsi +" = " +(valuePepsi*1.49)+"$"+"\n\n";
        if (valueSprite>0)
            mess += "Sprite 32 OZ                   x " + valueSprite +" = " +(valueSprite*1.49)+"$"+"\n\n";
        if (TotalFoodOrder()>0) {
            mess += "----------------------------------------------------" + "\n\n";
            mess += "Total: " + DoubleFormat(TotalFoodOrder())+"$";
            
        }
        
        return mess;
    }
    
    public double TotalFoodOrder() {
        int valuePop1 = (int)jSpinnerPop1.getValue();
        int valuePop2 = (int)jSpinnerPop2.getValue();
        int valueCoca = (int)jSpinnerCoca.getValue();
        int valuePepsi = (int)jSpinnerPepsi.getValue();
        int valueSprite = (int)jSpinnerSprite.getValue();
        return valuePop1*2 + valuePop2*2.29 + (valueCoca+valuePepsi+valueSprite)*1.49;
    }
    
    public String DoubleFormat(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(number);
        
    }
    
    public void InsertOrderFoodModelList(Timestamp time, String userOrder, double total, String history) {
        String[] columns = {"Time","Username","Total","History","Source"};
        String[] values = {String.valueOf(time), userOrder,String.valueOf(total) ,history,"Food"};
        DataConnection.insertData("revenue", columns, values);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        total = Double.valueOf(decimalFormat.format(total));
        loadOrderFoodModelList();
        loadTableHistory();
    }
    
    public String getHistoryMess() {
        int valuePop1 = (int)jSpinnerPop1.getValue();
        int valuePop2 = (int)jSpinnerPop2.getValue();
        int valueCoca = (int)jSpinnerCoca.getValue();
        int valuePepsi = (int)jSpinnerPepsi.getValue();
        int valueSprite = (int)jSpinnerSprite.getValue();
        String mess ="";
        if(valuePop1>0)
            mess += "Pop corn 60 OZ x " +valuePop1+"; ";
        if(valuePop2>0)
            mess += "Pop corn caramel 60 OZ x " +valuePop2+"; ";
        if (valueCoca>0)
            mess += "Coca 32 OZ x " + valueCoca +"; ";
        if (valuePepsi>0)
            mess += "Pepsi 32 OZ x " + valuePepsi +"; ";
        if (valueSprite>0)
            mess += "Sprite 32 OZ x " + valueSprite +"; ";
        return mess;
    }
    
    
    public void payment() {
        
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String userOrder = user.getUser();
        double total = TotalFoodOrder();
        String history = getHistoryMess();
        InsertOrderFoodModelList(time,userOrder,total,history);
    }
    
    public void OptionYes() {
        payment();
        resetSpinnerOrder();
        jTextArea1.setText("\n\n\n\n                    *Payment success*");
        jButton1.setEnabled(true);
        
    }
    
    public void OptionNo() {
        resetSpinnerOrder();
        jTextArea1.setText("\n\n\n\n                     *Payment failed*");
        jButton1.setEnabled(true);       
    }
    
    public void resetSpinnerOrder() {
        jSpinnerPop1.setValue(0);
        jSpinnerPop2.setValue(0);
        jSpinnerCoca.setValue(0);
        jSpinnerPepsi.setValue(0);
        jSpinnerSprite.setValue(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPaneConfirm = new javax.swing.JOptionPane();
        jOptionHistory = new javax.swing.JOptionPane();
        jScrollPaneHistory = new javax.swing.JScrollPane();
        jTableHistory = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSpinnerSprite = new javax.swing.JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        jSpinnerPop1 = new javax.swing.JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        jSpinnerPop2 = new javax.swing.JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        jSpinnerCoca = new javax.swing.JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        jSpinnerPepsi = new javax.swing.JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButtonHistory = new javax.swing.JButton();

        jOptionPaneConfirm.setBackground(new java.awt.Color(235, 235, 235));
        jOptionPaneConfirm.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jOptionPaneConfirm.setFocusTraversalPolicyProvider(true);
        jOptionPaneConfirm.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jOptionPaneConfirm.setVisible(false);

        jOptionHistory.setVisible(false);

        jScrollPaneHistory.setPreferredSize(new java.awt.Dimension(1050, 600));

        jTableHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "User", "Total", "History"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneHistory.setViewportView(jTableHistory);

        setBackground(new java.awt.Color(204, 255, 204));
        setMinimumSize(new java.awt.Dimension(650, 650));
        setPreferredSize(new java.awt.Dimension(970, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popcorn.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprite.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popcorn.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coca.jpg"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pepsi.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Sprite 32 OZ (1.49$)");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 530, -1, 20));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setText("TOTAL");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 40, -1, 20));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setText("Pop corn caramel 60 OZ (2.29$)");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, 20));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setText("Coca 32 OZ (1.49$)");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, -1, 20));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setText("Pepsi 32 OZ (1.49$)");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, -1, 20));

        jSpinnerSprite.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerSpriteStateChanged(evt);
            }
        });
        jPanel1.add(jSpinnerSprite, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 530, 100, -1));

        jSpinnerPop1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSpinnerPop1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerPop1StateChanged(evt);
            }
        });
        jPanel1.add(jSpinnerPop1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 100, -1));

        jSpinnerPop2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerPop2StateChanged(evt);
            }
        });
        jPanel1.add(jSpinnerPop2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 100, -1));

        jSpinnerCoca.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerCocaStateChanged(evt);
            }
        });
        jPanel1.add(jSpinnerCoca, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 100, -1));

        jSpinnerPepsi.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerPepsiStateChanged(evt);
            }
        });
        jPanel1.add(jSpinnerPepsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 400, 100, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, -70, 10, 720));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setText("Pop corn 60 OZ (2.00$)");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, 20));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 320, 420));

        jButton1.setText("Payment");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 560, 190, 40));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 970, 650));

        jButtonHistory.setBackground(new java.awt.Color(204, 255, 204));
        jButtonHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/history48.png"))); // NOI18N
        jButtonHistory.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistoryActionPerformed(evt);
            }
        });
        add(jButtonHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (TotalFoodOrder()> 0) {
            jButton1.setEnabled(false);
            int result = jOptionPaneConfirm.showConfirmDialog(this,"Confirm","", jOptionPaneConfirm.YES_NO_OPTION, jOptionPaneConfirm.QUESTION_MESSAGE);

            if (result == jOptionPaneConfirm.YES_OPTION) {
                OptionYes();
            } else {
                OptionNo();
            }
        } else {
            jTextArea1.setText("\n\n\n\n                 *Please select an item*");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSpinnerPop1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerPop1StateChanged
        // TODO add your handling code here:
        jTextArea1.setText(MessTotalFoodOrder());
    }//GEN-LAST:event_jSpinnerPop1StateChanged

    private void jSpinnerPop2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerPop2StateChanged
        // TODO add your handling code here:
        jTextArea1.setText(MessTotalFoodOrder());
    }//GEN-LAST:event_jSpinnerPop2StateChanged

    private void jSpinnerCocaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerCocaStateChanged
        // TODO add your handling code here:
        jTextArea1.setText(MessTotalFoodOrder());
    }//GEN-LAST:event_jSpinnerCocaStateChanged

    private void jSpinnerPepsiStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerPepsiStateChanged
        // TODO add your handling code here:
        jTextArea1.setText(MessTotalFoodOrder());
    }//GEN-LAST:event_jSpinnerPepsiStateChanged

    private void jSpinnerSpriteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerSpriteStateChanged
        // TODO add your handling code here:
        jTextArea1.setText(MessTotalFoodOrder());
    }//GEN-LAST:event_jSpinnerSpriteStateChanged

    private void jButtonHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistoryActionPerformed
        // TODO add your handling code here:
        loadTableHistory();
        jOptionHistory.showMessageDialog(this, jScrollPaneHistory,"History",JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_jButtonHistoryActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonHistory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JOptionPane jOptionHistory;
    private javax.swing.JOptionPane jOptionPaneConfirm;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneHistory;
    private javax.swing.JSpinner jSpinnerCoca;
    private javax.swing.JSpinner jSpinnerPepsi;
    private javax.swing.JSpinner jSpinnerPop1;
    private javax.swing.JSpinner jSpinnerPop2;
    private javax.swing.JSpinner jSpinnerSprite;
    private javax.swing.JTable jTableHistory;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
