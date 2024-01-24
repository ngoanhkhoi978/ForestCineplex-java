/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.view.panel.function;

import com.forestcineplex.movieticketbookingsystem.data.DataConnection;
import com.forestcineplex.movieticketbookingsystem.model.MovieModel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author LENOVO
 */
public class UploadMovie extends javax.swing.JPanel {

    /**
     * Creates new form UploadMovie
     */
    
    private AddMovie addMovie;
    public UploadMovie() {
        initComponents();
    }
    
    public UploadMovie(AddMovie addMovie) {
        this.addMovie = addMovie;
        initComponents();
        setDefault();
        
    }
    
    public void setDefaultBorder() {
        LineBorder lineBorder = new LineBorder(Color.LIGHT_GRAY, 1);
        jTextFieldName.setBorder(lineBorder);
        jTextFieldDuration.setBorder(lineBorder);
        jTextFieldPrice.setBorder(lineBorder);
        jTextPaneDescribe.setBorder(lineBorder);
    }
    
    public void setDefault() {
        setDefaultBorder();
        jTextFieldName.setText("");
        jTextFieldDuration.setText("");
        jTextFieldPrice.setText("");
        jTextPaneDescribe.setText("");
        jLabelMess.setText("");
        jLabelSuccess.setVisible(false);
    }
    
    public void setMessDefault() {
        this.jLabelMess.setText("");
        this.jLabelSuccess.setVisible(false);
    }
    /**
     * Converts an Image object to a byte array.
     *
     * @param image the Image object to convert
     * @return the byte array representing the converted image
     * @throws IOException if an I/O error occurs
     */
    private byte[] convertImageToByteArray(Image image) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", outStream);
        return outStream.toByteArray();
    }
    
    public void add(MovieModel movieModel) {
        try {
            //System.out.println(movieModel.getDescribe());
            byte[] image = convertImageToByteArray(movieModel.getImage().getImage());
            String[] columns = {"Name","Image","Price","Duration","DateCreated","Des"};
            Object[] values = {movieModel.getName(), image, movieModel.getPrice(), movieModel.getDuration(), movieModel.getTimeCreated(),movieModel.getDescribe()};
            DataConnection.insertData("movie", columns, values);
            addMovie.loadTableMovie();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void checkAddMovie() {
        String name;
        long duration;
        double price;
        String describe;
        
        LineBorder error = new LineBorder(Color.RED);
        
        name = jTextFieldName.getText();
        if (name.equals("")) {
            this.jTextFieldName.setBorder(error);
            this.jLabelMess.setText("Name cannot be empty");
            return;
        }
        
        try {
            duration = Integer.valueOf(jTextFieldDuration.getText())*60*1000;
        } catch (Exception e) {
            this.jTextFieldDuration.setBorder(error);
            this.jTextFieldDuration.setText("");
            this.jLabelMess.setText("Invalid duration");
            return;
        }
        
        try {
            price = Double.valueOf(jTextFieldPrice.getText());
        } catch (Exception e) {
            this.jTextFieldPrice.setBorder(error);
            this.jTextFieldPrice.setText("");
            this.jLabelMess.setText("Invalid price");
            return;
        }
        
        describe = jTextPaneDescribe.getText();
        if (describe.equals("")) {
            this.jTextPaneDescribe.setBorder(error);
            this.jLabelMess.setText("Describe cannot be empty");
            return;
        }
        MovieModel movieModel = new MovieModel(0, name, (ImageIcon)jLabelPoster.getIcon(), price, duration, new Timestamp(System.currentTimeMillis()),describe);
        add(movieModel);
        
        jLabelSuccess.setVisible(true);
        
        
           
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelPoster = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneDescribe = new javax.swing.JTextPane();
        jTextFieldPrice = new javax.swing.JTextField();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldDuration = new javax.swing.JTextField();
        jLabelDuration = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabelMess = new javax.swing.JLabel();
        jLabelSuccess = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(700, 420));
        setPreferredSize(new java.awt.Dimension(700, 450));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPoster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/empty.jpg"))); // NOI18N
        add(jLabelPoster, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/upload.png"))); // NOI18N
        jButton1.setText("Change Image");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 130, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Name:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Duration:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Price:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Describe:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, -1));

        jTextPaneDescribe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jScrollPane2.setViewportView(jTextPaneDescribe);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 350, 150));

        jTextFieldPrice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        add(jTextFieldPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 100, 30));

        jTextFieldName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        add(jTextFieldName, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 340, 30));

        jTextFieldDuration.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        add(jTextFieldDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 130, 30));

        jLabelDuration.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabelDuration.setText("min");
        add(jLabelDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, -1, -1));

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/remove.png"))); // NOI18N
        jButton3.setToolTipText("Remove");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 70, 40));

        jButton2.setBackground(new java.awt.Color(204, 255, 204));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 70, -1));

        jLabelMess.setBackground(new java.awt.Color(255, 204, 204));
        jLabelMess.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelMess.setForeground(new java.awt.Color(255, 102, 102));
        jLabelMess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(jLabelMess, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 340, 690, -1));

        jLabelSuccess.setBackground(new java.awt.Color(51, 255, 51));
        jLabelSuccess.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelSuccess.setForeground(new java.awt.Color(0, 204, 0));
        jLabelSuccess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSuccess.setText("Success");
        jLabelSuccess.setVisible(false);
        add(jLabelSuccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 340, 690, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(addMovie.getMainSystem());
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                ImageIcon image = new ImageIcon(ImageIO.read(selectedFile));
                jLabelPoster.setIcon(ImageUtil.resize(image, 167, 250));
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setMessDefault();
        setDefaultBorder();
        checkAddMovie();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        addMovie.disposeUploadMovie();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelDuration;
    private javax.swing.JLabel jLabelMess;
    private javax.swing.JLabel jLabelPoster;
    private javax.swing.JLabel jLabelSuccess;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldDuration;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPrice;
    private javax.swing.JTextPane jTextPaneDescribe;
    // End of variables declaration//GEN-END:variables
}
