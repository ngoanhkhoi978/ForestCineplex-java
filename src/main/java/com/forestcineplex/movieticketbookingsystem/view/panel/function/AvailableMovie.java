/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.view.panel.function;

import com.forestcineplex.movieticketbookingsystem.data.DataConnection;
import com.forestcineplex.movieticketbookingsystem.model.AvailableMovieModel;
import com.forestcineplex.movieticketbookingsystem.model.MovieModel;
import com.forestcineplex.movieticketbookingsystem.model.SeatModel;
import com.forestcineplex.movieticketbookingsystem.model.User;
import com.forestcineplex.movieticketbookingsystem.view.frame.MainSystem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author LENOVO
 */
public class AvailableMovie extends javax.swing.JPanel {
    
    /**
     * Creates new form AvailableMovie
     */
    
    private HashMap<Integer, AvailableMovieModel> avaMap;
    private MainSystem mainSystem;
    private User user;
    
    public void loadAvaMap() {
        avaMap = new HashMap<>();
        
        try (ResultSet resultSet = DataConnection.executeQuery("SELECT * FROM availablemovie ORDER BY Time DESC")) {
            if (resultSet != null && resultSet.next()) {
                do {
                    int ID = resultSet.getInt("ID");
                    int IDMovieModel = resultSet.getInt("ID_Movie");
                    SeatModel seatModel = new SeatModel(resultSet.getString("Seats"));
                    Timestamp time = resultSet.getTimestamp("Time");
                    MovieModel movieModel = new MovieModel();
                    
                    try (ResultSet resultSet1 = DataConnection.executeQuery("SELECT * FROM movie WHERE ID = '"+IDMovieModel+"'")) {
                        if (resultSet1 != null && resultSet1.next()) {
                            do {
                                int IDMVD = Integer.valueOf(resultSet1.getString("ID"));
                                String name = resultSet1.getString("Name");
                                byte[] bytes = resultSet1.getBytes("Image");
                                ImageIcon imageIcon = new ImageIcon(bytes);
                                double price = resultSet1.getDouble("Price");
                                long duration = resultSet1.getLong("Duration");
                                Timestamp dateCreated = resultSet1.getTimestamp("DateCreated");
                                String describe = resultSet1.getString("Des");
                                movieModel = new MovieModel(IDMVD, name, imageIcon, price, duration, dateCreated, describe);
                            } while (resultSet1.next());

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    
                    //users.put(username, new User(username, passwordHash, role, name, gender, salary, address, phone, mail, birthDate));
                    avaMap.put(ID, new AvailableMovieModel(movieModel, ID, seatModel, time));
                    
                } while (resultSet.next());
   
            } else {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
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

         try (ResultSet resultSet = DataConnection.executeQuery("SELECT * FROM revenue WHERE Source = 'Movie' ORDER BY Time DESC")) {
            if (resultSet != null && resultSet.next()) {
                do {
                    Timestamp time = resultSet.getTimestamp("Time");
                    String username = resultSet.getString("Username");
                    double total = resultSet.getDouble("Total");
                    String history = resultSet.getString("History");
                    defaultTableModel.addRow(new Object[] {time, username, total, history});
                } while (resultSet.next());

            } else {
                System.out.println("null");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void filter() {
        String search = jTextFieldFind.getText();
        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)"+ Pattern.quote(search),0,1);
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
        ((TableRowSorter<DefaultTableModel>) jTable1.getRowSorter()).setRowFilter(rowFilter);
    }
    
    public String TimeFormat(long duration) {
        return TimeUnit.MILLISECONDS.toHours(duration)+"h"+TimeUnit.MILLISECONDS.toMinutes(duration)%60+"m";
    }
    
    public void setDefalt() {
        jTable1.clearSelection();
        jLabelName.setText("");
        jLabelDuration.setText("");
        jLabelPrice.setText("");
        jTextPaneDescribe.setText("");
        jLabelPoster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/empty.jpg")));
        jLabelTime.setText("");
        jLabelDate.setText("");
        
    }
    
    public void displayMovie(AvailableMovieModel amm) {
        SimpleDateFormat sdfD = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm");
        Date time = new Date(amm.getShowDate().getTime());
        
        MovieModel movieModel = amm.getMovieModel();
        
        jLabelName.setText(movieModel.getName());
        jLabelDuration.setText(TimeFormat(movieModel.getDuration()));
        jLabelPrice.setText(movieModel.getPrice()+"$");
        jTextPaneDescribe.setText(movieModel.getDescribe());
        jLabelPoster.setIcon(movieModel.getImage());
        
        jLabelTime.setText(sdfT.format(time));
        jLabelDate.setText(sdfD.format(time));
        
    }
    
    public void setupJtextfieldFind() {
        jTextFieldFind.getDocument().addDocumentListener(new DocumentListener() {
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
    
    public void loadTable() {
        loadAvaMap();
        
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel defaultTableModel = (DefaultTableModel)jTable1.getModel();
        defaultTableModel.setRowCount(0);
        SimpleDateFormat sdfD = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm");
        
        for (AvailableMovieModel availableMovieModel : avaMap.values()) {
            Date time = new Date(availableMovieModel.getShowDate().getTime());
            String status = String.valueOf(80-availableMovieModel.getSeatModel().QuantityEmptySeat())+"/80";
            defaultTableModel.addRow(new Object[] {availableMovieModel.getID(), availableMovieModel.getMovieModel().getName(),sdfD.format(time),sdfT.format(time),status});
        }
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(defaultTableModel);
        rowSorter.toggleSortOrder(1);
        jTable1.setRowSorter(rowSorter);
    }
    
    public void setDefaultTable() {
        
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);
        
        DefaultTableModel defaultTableModel = (DefaultTableModel)jTable1.getModel();
        
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
        
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRender);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(centerRender);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(centerRender);

    }
    public AvailableMovie(MainSystem mainSystem) {
        this.mainSystem = mainSystem;
        initComponents();
        setDefaultTable();
        setupJtextfieldFind();
    }
    
    public User getUser() {
        return mainSystem.getUser();
    }
    
    public AvailableMovieModel getAvaiableMovieModel() {
        int row = jTable1.getSelectedRow();
        row = jTable1.convertRowIndexToModel(row);
        int ID = (Integer)jTable1.getModel().getValueAt(row, 0);
        AvailableMovieModel amm = avaMap.get(ID);
        return amm;
    }
    
    public void displayChooseSeat() {
        ChooseSeat chooseSeat = new ChooseSeat(this);
        chooseSeat.load();
        jDialogBuy.getContentPane().add(chooseSeat);
        jDialogBuy.setTitle("Choose Seat");
        jDialogBuy.setLocation(200, 50);
        jDialogBuy.setSize(1000,770);
        jDialogBuy.setResizable(false);
        jDialogBuy.setVisible(true);
    }
    
    public void disposeChooseSeat() {
        jDialogBuy.dispose();
        jDialogBuy.getContentPane().removeAll();
        jDialogBuy.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogBuy = new javax.swing.JDialog(mainSystem,"Hello",true);
        jOptionPaneConfirm = new javax.swing.JOptionPane();
        jScrollPaneHistory = new javax.swing.JScrollPane();
        jTableHistory = new javax.swing.JTable();
        jOptionHistory = new javax.swing.JOptionPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneDescribe = new javax.swing.JTextPane();
        jTextFieldFind = new javax.swing.JTextField();
        jLabelPoster = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelTime = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelPrice = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabelDuration = new javax.swing.JLabel();
        jLabelDate = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButtonHistory = new javax.swing.JButton();
        jLabelMess = new javax.swing.JLabel();

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

        jOptionHistory.setVisible(false);

        setPreferredSize(new java.awt.Dimension(970, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Date", "Time", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 120, 521, 600));

        jTextPaneDescribe.setEditable(false);
        jTextPaneDescribe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextPaneDescribe.setText("After Thanos, an intergalactic warlord, disintegrates half of the universe, the Avengers must reunite and assemble again to reinvigorate their trounced allies and restore balance.");
        jScrollPane2.setViewportView(jTextPaneDescribe);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, 320, 120));

        jTextFieldFind.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        add(jTextFieldFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 450, 50));

        jLabelPoster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poster.jpg"))); // NOI18N
        add(jLabelPoster, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Name:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jLabelName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelName.setText("Avengers Endgame");
        add(jLabelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Time:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, -1, -1));

        jLabelTime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelTime.setText("13:00");
        add(jLabelTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 430, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Price:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, -1, -1));

        jLabelPrice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelPrice.setText("4$");
        add(jLabelPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Describe:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Duration:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Date:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, -1));

        jLabelDuration.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelDuration.setText("1h56m");
        add(jLabelDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, -1, -1));

        jLabelDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelDate.setText("22/01/24");
        add(jLabelDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, -1, -1));

        jButton2.setBackground(new java.awt.Color(204, 255, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ticket.png"))); // NOI18N
        jButton2.setText("Buy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 650, 120, 40));

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 670, 70, 20));

        jButtonHistory.setBackground(new java.awt.Color(242, 242, 242));
        jButtonHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/history48.png"))); // NOI18N
        jButtonHistory.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistoryActionPerformed(evt);
            }
        });
        add(jButtonHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 30, -1, -1));

        jLabelMess.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelMess.setForeground(new java.awt.Color(255, 51, 51));
        jLabelMess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMess.setText("Please select a available movie");
        add(jLabelMess, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 450, 40));
        jLabelMess.setVisible(false);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jLabelMess.setVisible(false);
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            jLabelMess.setVisible(true);
            return;
        } else {
            row = jTable1.convertRowIndexToModel(row);
            disposeChooseSeat();
            displayChooseSeat();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jLabelMess.setVisible(false);
        int row = jTable1.getSelectedRow();
        if (row == -1) {

            jLabelMess.setVisible(true);
            return;
        } else {
            row = jTable1.convertRowIndexToModel(row);
            int ID =  (Integer)jTable1.getModel().getValueAt(row, 0);  
            int result = jOptionPaneConfirm.showConfirmDialog(this,"Delete","", jOptionPaneConfirm.YES_NO_OPTION, jOptionPaneConfirm.QUESTION_MESSAGE);
            if (result == jOptionPaneConfirm.YES_OPTION) {
                DataConnection.executeUpdate("DELETE FROM availablemovie WHERE ID = ?", ID);
                loadTable();
                setDefalt();
            } 
            jTable1.clearSelection();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistoryActionPerformed
        // TODO add your handling code here:
        loadTableHistory();
        jOptionHistory.showMessageDialog(this, jScrollPaneHistory,"History",JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_jButtonHistoryActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        jLabelMess.setVisible(false);
        int row = jTable1.getSelectedRow();
        if (row == -1)
            return;
        else {
            row = jTable1.convertRowIndexToModel(row);
            int ID =  (Integer)jTable1.getModel().getValueAt(row, 0);
            AvailableMovieModel amm = avaMap.get(ID);
            displayMovie(amm);
        }
        
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonHistory;
    private javax.swing.JDialog jDialogBuy;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelDate;
    private javax.swing.JLabel jLabelDuration;
    private javax.swing.JLabel jLabelMess;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPoster;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabelTime;
    private javax.swing.JOptionPane jOptionHistory;
    private javax.swing.JOptionPane jOptionPaneConfirm;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneHistory;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableHistory;
    private javax.swing.JTextField jTextFieldFind;
    private javax.swing.JTextPane jTextPaneDescribe;
    // End of variables declaration//GEN-END:variables
}
