/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.view.panel.function;

import com.forestcineplex.movieticketbookingsystem.data.DataConnection;
import com.forestcineplex.movieticketbookingsystem.model.MovieModel;
import com.forestcineplex.movieticketbookingsystem.model.OrderFoodModel;
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
public class AddMovie extends javax.swing.JPanel {

    /**
     * Creates new form AddMovie
     */
    private MainSystem mainSystem;
    private HashMap<Integer, MovieModel> movies = new HashMap<Integer, MovieModel>();

    public void loadMoviesModel() {
        movies.clear();
        try (ResultSet resultSet = DataConnection.executeQuery("SELECT * FROM movie ORDER BY ID")) {
            if (resultSet != null && resultSet.next()) {
                do {
                    int ID = Integer.valueOf(resultSet.getString("ID"));
                    String name = resultSet.getString("Name");
                    byte[] bytes = resultSet.getBytes("Image");
                    ImageIcon imageIcon = new ImageIcon(bytes);
                    double price = resultSet.getDouble("Price");
                    long duration = resultSet.getLong("Duration");
                    Timestamp dateCreated = resultSet.getTimestamp("DateCreated");
                    String describe = resultSet.getString("Des");
                    movies.put(ID, new MovieModel(ID, name, imageIcon, price, duration, dateCreated, describe));
                } while (resultSet.next());

            } else {
                System.out.println("null");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void setDefaultTable() {

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
        //jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);

        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRender);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);

    }

    public String TimeFormat(long duration) {
        return TimeUnit.MILLISECONDS.toHours(duration) + "h" + TimeUnit.MILLISECONDS.toMinutes(duration) % 60 + "m";
    }

    public void loadTableMovie() {
        loadMoviesModel();
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
        defaultTableModel.setRowCount(0);

        for (MovieModel movieModel : movies.values()) {
            defaultTableModel.addRow(new Object[]{movieModel.getID(), movieModel.getName(), movieModel.getTimeCreated()});
        }
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(defaultTableModel);
        rowSorter.toggleSortOrder(0);
        jTable1.setRowSorter(rowSorter);

    }

    public void displayMovieModel(MovieModel movieModel) {
        jLabelName.setText(movieModel.getName());
        jLabelDuration.setText(TimeFormat(movieModel.getDuration()));
        jLabelPrice.setText(movieModel.getPrice() + "$");
        jTextPaneDescribe.setText(movieModel.getDescribe());

        jLabelPoster.setIcon(movieModel.getImage());

    }

    public AddMovie() {
        initComponents();
        setDefaultTable();
        loadTableMovie();
        setupJtextfieldFind();
    }

    public AddMovie(MainSystem mainSystem) {
        this.mainSystem = mainSystem;
        initComponents();
        setDefaultTable();
        loadTableMovie();
        setupJtextfieldFind();
    }

    public MainSystem getMainSystem() {
        return mainSystem;
    }

    public void displaySuccess() {
        this.jLabelSuccess.setVisible(true);
    }

    public void SetDefault() {
        jTextFieldFind.setText("");
        jLabelSuccess.setVisible(false);
        jLabelName.setText("");
        jLabelDuration.setText("");
        jLabelPrice.setText("");
        jTextPaneDescribe.setText("");
        jLabelPoster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/empty.jpg"))); // NOI18N

    }

    private void filter() {
        String search = jTextFieldFind.getText();
        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + Pattern.quote(search), 0, 1);
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
        ((TableRowSorter<DefaultTableModel>) jTable1.getRowSorter()).setRowFilter(rowFilter);
    }

    public MovieModel getMovieModel() {
        int row = jTable1.getSelectedRow();
        row = jTable1.convertRowIndexToModel(row);
        int ID = (Integer) jTable1.getModel().getValueAt(row, 0);
        MovieModel movieModel = movies.get(ID);
        return movieModel;
    }

    public void displayUploadMovie() {

        UploadMovie uploadMovie = new UploadMovie(this);
        jDialogAdd.setContentPane(uploadMovie);
        jDialogAdd.setTitle("Upload Movie");
        jDialogAdd.setLocation(350, 150);
        jDialogAdd.setSize(730, 500);
        jDialogAdd.setResizable(false);
        jDialogAdd.setVisible(true);

    }

    public void disposeUploadMovie() {
        jDialogAdd.dispose();
        jDialogAdd.getContentPane().removeAll();
        jDialogAdd.repaint();
    }

    public void displayActivateMovie() {
        ActivateMovie activateMovie = new ActivateMovie(this);
        activateMovie.setDefault();
        activateMovie.loadMovieModel();
        activateMovie.display();
        jDialogActivate.setContentPane(activateMovie);
        jDialogActivate.setTitle("Activate Movie");
        jDialogActivate.setLocation(350, 150);
        jDialogActivate.setSize(700, 500);
        jDialogActivate.setResizable(false);
        jDialogActivate.setVisible(true);
    }

    public void disposeActivateMovie() {
        jDialogActivate.dispose();
        jDialogActivate.getContentPane().removeAll();
        jDialogAdd.repaint();
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
        jDialogActivate = new javax.swing.JDialog(mainSystem,"Hello",true);
        jOptionPaneError = new javax.swing.JOptionPane();
        jOptionPaneConfirmDelete = new javax.swing.JOptionPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabelPoster = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelMess = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelPrice = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabelDuration = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneDescribe = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldFind = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabelSuccess = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 204));
        setPreferredSize(new java.awt.Dimension(970, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Movie", "Name", "Date Created"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
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
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 490, 720));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPoster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poster.jpg"))); // NOI18N
        jPanel1.add(jLabelPoster, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Name:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        jLabelMess.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelMess.setForeground(new java.awt.Color(255, 51, 51));
        jLabelMess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMess.setText("Please select a movie");
        jPanel1.add(jLabelMess, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 480, 40));
        jLabelMess.setVisible(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Describe:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Price:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Duration:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        jLabelPrice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelPrice.setText("4$");
        jPanel1.add(jLabelPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, -1, -1));

        jLabelName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelName.setText("Avengers Endgame");
        jPanel1.add(jLabelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, -1, -1));

        jLabelDuration.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelDuration.setText("1h56m");
        jPanel1.add(jLabelDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, -1, -1));

        jTextPaneDescribe.setEditable(false);
        jTextPaneDescribe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextPaneDescribe.setText("After Thanos, an intergalactic warlord, disintegrates half of the universe, the Avengers must reunite and assemble again to reinvigorate their trounced allies and restore balance.");
        jScrollPane2.setViewportView(jTextPaneDescribe);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 450, 350, 140));

        jButton1.setBackground(new java.awt.Color(255, 255, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ticket.png"))); // NOI18N
        jButton1.setText("Activate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 650, 120, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/find.png"))); // NOI18N
        jLabel9.setToolTipText("Search for Name or ID");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        jTextFieldFind.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextFieldFind.setToolTipText("Search for Name or ID");
        jPanel1.add(jTextFieldFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 390, 50));

        jButton2.setBackground(new java.awt.Color(204, 255, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus.png"))); // NOI18N
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, 120, 40));

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/remove.png"))); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 650, 120, 40));

        jLabelSuccess.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelSuccess.setForeground(new java.awt.Color(51, 204, 0));
        jLabelSuccess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSuccess.setText("Success");
        jPanel1.add(jLabelSuccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 480, 40));
        jLabelSuccess.setVisible(false);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        jLabelSuccess.setVisible(false);
        jLabelMess.setVisible(false);
        int row = jTable1.getSelectedRow();
        if (row == -1)
            return;
        else {
            row = jTable1.convertRowIndexToModel(row);
            //jLabelMess.setVisible(false);
            int ID = (Integer) jTable1.getModel().getValueAt(row, 0);
            MovieModel movieModel = movies.get(ID);
            displayMovieModel(movieModel);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jLabelSuccess.setVisible(false);
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            jLabelMess.setVisible(true);
            return;
        } else {
            displayActivateMovie();

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jLabelSuccess.setVisible(false);

        int row = jTable1.getSelectedRow();
        if (row == -1) {
            jLabelMess.setVisible(true);
            return;
        } else {
            row = jTable1.convertRowIndexToModel(row);
            int result = jOptionPaneConfirm.showConfirmDialog(mainSystem, "Delete", "Confirm", jOptionPaneConfirm.YES_NO_OPTION, jOptionPaneConfirm.QUESTION_MESSAGE);

            if (result == jOptionPaneConfirm.YES_OPTION) {
                int ID = (Integer) jTable1.getModel().getValueAt(row, 0);
                try {
                    DataConnection.executeUpdate("DELETE FROM movie WHERE ID = ?", ID);
                } catch (Exception e) {
                    if (e.getMessage().contains("Cannot delete or update a parent row: a foreign key constraint fails (`forestcineplex`.`availablemovie`")) {
                        int res = JOptionPane.showConfirmDialog(mainSystem, "If you delete this movie, also delete any movies under its current showings.", "Delete Confirm", JOptionPane.YES_NO_OPTION);
                        if (res == JOptionPane.YES_OPTION) {
                            
                            DataConnection.executeUpdate("DELETE FROM availablemovie WHERE ID_Movie = ?", ID);
                            DataConnection.executeUpdate("DELETE FROM movie WHERE ID = ?", ID);
                        }
                    }
                    jTable1.clearSelection();
                    SetDefault();
                    loadTableMovie();
                }
            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //SetDefault();
        jLabelMess.setVisible(false);
        jLabelSuccess.setVisible(false);
        displayUploadMovie();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JDialog jDialogActivate;
    private javax.swing.JDialog jDialogAdd;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDuration;
    private javax.swing.JLabel jLabelMess;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPoster;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabelSuccess;
    private javax.swing.JOptionPane jOptionPaneConfirm;
    private javax.swing.JOptionPane jOptionPaneConfirmDelete;
    private javax.swing.JOptionPane jOptionPaneError;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldFind;
    private javax.swing.JTextPane jTextPaneDescribe;
    // End of variables declaration//GEN-END:variables
}
