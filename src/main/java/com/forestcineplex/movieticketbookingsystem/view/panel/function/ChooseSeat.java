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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 *
 * @author LENOVO
 */
public class ChooseSeat extends javax.swing.JPanel {

    /**
     * Creates new form ChooseSeat
     */
    private AvailableMovie am;
    private AvailableMovieModel amm;
    private HashMap<Integer, JToggleButton> buttons;
    private String seatResult;
    int quantityChoose;
    
    public void groupButtons() {
        buttons = new HashMap<>();
        buttons.put(1, seat1);
        buttons.put(2, seat2);
        buttons.put(3, seat3);
        buttons.put(4, seat4);
        buttons.put(5, seat5);
        buttons.put(6, seat6);
        buttons.put(7, seat7);
        buttons.put(8, seat8);
        buttons.put(9, seat9);
        buttons.put(10, seat10);
        buttons.put(11, seat11);
        buttons.put(12, seat12);
        buttons.put(13, seat13);
        buttons.put(14, seat14);
        buttons.put(15, seat15);
        buttons.put(16, seat16);
        buttons.put(17, seat17);
        buttons.put(18, seat18);
        buttons.put(19, seat19);
        buttons.put(20, seat20);
        buttons.put(21, seat21);
        buttons.put(22, seat22);
        buttons.put(23, seat23);
        buttons.put(24, seat24);
        buttons.put(25, seat25);
        buttons.put(26, seat26);
        buttons.put(27, seat27);
        buttons.put(28, seat28);
        buttons.put(29, seat29);
        buttons.put(30, seat30);
        buttons.put(31, seat31);
        buttons.put(32, seat32);
        buttons.put(33, seat33);
        buttons.put(34, seat34);
        buttons.put(35, seat35);
        buttons.put(36, seat36);
        buttons.put(37, seat37);
        buttons.put(38, seat38);
        buttons.put(39, seat39);
        buttons.put(40, seat40);
        buttons.put(41, seat41);
        buttons.put(42, seat42);
        buttons.put(43, seat43);
        buttons.put(44, seat44);
        buttons.put(45, seat45);
        buttons.put(46, seat46);
        buttons.put(47, seat47);
        buttons.put(48, seat48);
        buttons.put(49, seat49);
        buttons.put(50, seat50);
        buttons.put(51, seat51);
        buttons.put(52, seat52);
        buttons.put(53, seat53);
        buttons.put(54, seat54);
        buttons.put(55, seat55);
        buttons.put(56, seat56);
        buttons.put(57, seat57);
        buttons.put(58, seat58);
        buttons.put(59, seat59);
        buttons.put(60, seat60);
        buttons.put(61, seat61);
        buttons.put(62, seat62);
        buttons.put(63, seat63);
        buttons.put(64, seat64);
        buttons.put(65, seat65);
        buttons.put(66, seat66);
        buttons.put(67, seat67);
        buttons.put(68, seat68);
        buttons.put(69, seat69);
        buttons.put(70, seat70);
        buttons.put(71, seat71);
        buttons.put(72, seat72);
        buttons.put(73, seat73);
        buttons.put(74, seat74);
        buttons.put(75, seat75);
        buttons.put(76, seat76);
        buttons.put(77, seat77);
        buttons.put(78, seat78);
        buttons.put(79, seat79);
        buttons.put(80, seat80);
    }
    
    public void loadAvaiableMovieModel() {
        this.amm = am.getAvaiableMovieModel();
    }
    
    public String TimeFormat(long duration) {
        return TimeUnit.MILLISECONDS.toHours(duration)+"h"+TimeUnit.MILLISECONDS.toMinutes(duration)%60+"m";
    }
    
    public void load() {
        loadAvaiableMovieModel();
        loadSeats();
        
        MovieModel movieModel = this.amm.getMovieModel();
        
        jLabelName.setText(movieModel.getName());
        jLabelDuration.setText(TimeFormat(movieModel.getDuration()));
        jLabelPrice.setText(movieModel.getPrice()+"$");
        jLabelPoster.setIcon(movieModel.getImage());
        jLabelTotal.setText("0$");
        
        SimpleDateFormat sdfD = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm");
        Date time = new Date(amm.getShowDate().getTime());
        jLabelTime.setText(sdfT.format(time));
        jLabelDate1.setText(sdfD.format(time));
        
    }
    
    public void loadSeats() {
        SeatModel seatModel = amm.getSeatModel();
        for (int i = 1; i<=80; i++) {
            if (seatModel.checkIdSeat(i)==true) {
                buttons.get(i).setEnabled(false);
            } else {
                buttons.get(i).setEnabled(true);
            }
            
        }
    }
    
    public void check() {
        jLabelMess.setVisible(false);
        jTextPaneSeats.setText("");
        
        double total;
        quantityChoose = 0;
        seatResult = "";
        int ID = 0;
        for (JToggleButton jtb : buttons.values()) {
            ID += 1;
            if (jtb.isEnabled() == false) {
                seatResult += "1";
                continue;
            }
            
            if (jtb.isSelected()) {
                seatResult += "1";
                quantityChoose += 1;
                jTextPaneSeats.setText(jTextPaneSeats.getText()+" "+SeatModel.getTypeSeet(ID));
            } else {
                seatResult += "0";
            }
              
        }
        
        total = quantityChoose*amm.getMovieModel().getPrice();
        
        DecimalFormat df = new DecimalFormat("#.##");
        jLabelTotal.setText(df.format(total)+"$");
    }
    
    class ActionListenerButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            check();
        }
        
    }
    
    public void setupActionListenerButton() {
        ActionListenerButton actionListenerButton = new ActionListenerButton();
        for (JToggleButton jtb : buttons.values()) {
            
            jtb.addActionListener(actionListenerButton);
        }

    }
    public ChooseSeat(AvailableMovie am) {
        this.am = am;
        initComponents();
        groupButtons();
        setupActionListenerButton();
    }
    
    public String history() {
        String his = "MovieID:"+amm.getMovieModel().getID()+"; "+amm.getMovieModel().getPrice()+"; "+amm.getShowDate()+"; "+jTextPaneSeats.getText();
        return his;
    }
    
    public void payment() {
        double total = Double.valueOf(jLabelTotal.getText().replace("$", ""));
        User user = am.getUser();
        
        
        
        String[] columns = {"Time", "Total", "Username", "History", "Quantity", "Source"};
        Object[] values = {new Timestamp(System.currentTimeMillis()), total, user.getUser(), history(),quantityChoose,"Movie"};

        DataConnection.insertData("revenue", columns, values);
        
        String sqlQuery = "UPDATE availablemovie SET Seats = ? WHERE ID = ?";
        DataConnection.executeUpdate(sqlQuery, seatResult, amm.getID());
        load();
        am.loadTable();
        am.disposeChooseSeat();
        am.setDefalt();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        seat2 = new javax.swing.JToggleButton();
        seat1 = new javax.swing.JToggleButton();
        seat4 = new javax.swing.JToggleButton();
        seat3 = new javax.swing.JToggleButton();
        seat8 = new javax.swing.JToggleButton();
        seat7 = new javax.swing.JToggleButton();
        seat6 = new javax.swing.JToggleButton();
        seat5 = new javax.swing.JToggleButton();
        seat9 = new javax.swing.JToggleButton();
        seat10 = new javax.swing.JToggleButton();
        seat11 = new javax.swing.JToggleButton();
        seat12 = new javax.swing.JToggleButton();
        seat13 = new javax.swing.JToggleButton();
        seat14 = new javax.swing.JToggleButton();
        seat15 = new javax.swing.JToggleButton();
        seat16 = new javax.swing.JToggleButton();
        seat17 = new javax.swing.JToggleButton();
        seat18 = new javax.swing.JToggleButton();
        seat19 = new javax.swing.JToggleButton();
        seat20 = new javax.swing.JToggleButton();
        seat21 = new javax.swing.JToggleButton();
        seat22 = new javax.swing.JToggleButton();
        seat23 = new javax.swing.JToggleButton();
        seat24 = new javax.swing.JToggleButton();
        seat25 = new javax.swing.JToggleButton();
        seat26 = new javax.swing.JToggleButton();
        seat27 = new javax.swing.JToggleButton();
        seat28 = new javax.swing.JToggleButton();
        seat29 = new javax.swing.JToggleButton();
        seat30 = new javax.swing.JToggleButton();
        seat31 = new javax.swing.JToggleButton();
        seat32 = new javax.swing.JToggleButton();
        seat33 = new javax.swing.JToggleButton();
        seat34 = new javax.swing.JToggleButton();
        seat35 = new javax.swing.JToggleButton();
        seat36 = new javax.swing.JToggleButton();
        seat37 = new javax.swing.JToggleButton();
        seat38 = new javax.swing.JToggleButton();
        seat39 = new javax.swing.JToggleButton();
        seat40 = new javax.swing.JToggleButton();
        seat41 = new javax.swing.JToggleButton();
        seat42 = new javax.swing.JToggleButton();
        seat43 = new javax.swing.JToggleButton();
        seat44 = new javax.swing.JToggleButton();
        seat45 = new javax.swing.JToggleButton();
        seat46 = new javax.swing.JToggleButton();
        seat47 = new javax.swing.JToggleButton();
        seat48 = new javax.swing.JToggleButton();
        seat49 = new javax.swing.JToggleButton();
        seat50 = new javax.swing.JToggleButton();
        seat51 = new javax.swing.JToggleButton();
        seat52 = new javax.swing.JToggleButton();
        seat53 = new javax.swing.JToggleButton();
        seat54 = new javax.swing.JToggleButton();
        seat55 = new javax.swing.JToggleButton();
        seat56 = new javax.swing.JToggleButton();
        seat57 = new javax.swing.JToggleButton();
        seat58 = new javax.swing.JToggleButton();
        seat59 = new javax.swing.JToggleButton();
        seat60 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabelPoster = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelDuration = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelPrice = new javax.swing.JLabel();
        jLabelTime = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabelTotal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneSeats = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        seat61 = new javax.swing.JToggleButton();
        seat62 = new javax.swing.JToggleButton();
        seat63 = new javax.swing.JToggleButton();
        seat64 = new javax.swing.JToggleButton();
        seat65 = new javax.swing.JToggleButton();
        seat66 = new javax.swing.JToggleButton();
        seat67 = new javax.swing.JToggleButton();
        seat68 = new javax.swing.JToggleButton();
        seat69 = new javax.swing.JToggleButton();
        seat70 = new javax.swing.JToggleButton();
        seat80 = new javax.swing.JToggleButton();
        seat79 = new javax.swing.JToggleButton();
        seat78 = new javax.swing.JToggleButton();
        seat77 = new javax.swing.JToggleButton();
        seat76 = new javax.swing.JToggleButton();
        seat75 = new javax.swing.JToggleButton();
        seat74 = new javax.swing.JToggleButton();
        seat73 = new javax.swing.JToggleButton();
        seat72 = new javax.swing.JToggleButton();
        seat71 = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabelDate1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabelMess = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1000, 720));
        setPreferredSize(new java.awt.Dimension(1000, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seat2.setBackground(new java.awt.Color(242, 242, 242));
        seat2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat2.setBorder(null);
        seat2.setContentAreaFilled(false);
        seat2.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        seat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat2ActionPerformed(evt);
            }
        });
        add(seat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 58, -1, -1));

        seat1.setBackground(new java.awt.Color(242, 242, 242));
        seat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat1.setBorder(null);
        seat1.setContentAreaFilled(false);
        seat1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        seat1.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        seat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat1ActionPerformed(evt);
            }
        });
        add(seat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 58, -1, -1));

        seat4.setBackground(new java.awt.Color(242, 242, 242));
        seat4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat4.setBorder(null);
        seat4.setContentAreaFilled(false);
        seat4.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat4, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 58, -1, -1));

        seat3.setBackground(new java.awt.Color(242, 242, 242));
        seat3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat3.setBorder(null);
        seat3.setContentAreaFilled(false);
        seat3.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat3, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 58, -1, -1));

        seat8.setBackground(new java.awt.Color(242, 242, 242));
        seat8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat8.setBorder(null);
        seat8.setContentAreaFilled(false);
        seat8.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat8, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 58, -1, -1));

        seat7.setBackground(new java.awt.Color(242, 242, 242));
        seat7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat7.setBorder(null);
        seat7.setContentAreaFilled(false);
        seat7.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat7, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 58, -1, -1));

        seat6.setBackground(new java.awt.Color(242, 242, 242));
        seat6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat6.setBorder(null);
        seat6.setContentAreaFilled(false);
        seat6.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat6, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 58, -1, -1));

        seat5.setBackground(new java.awt.Color(242, 242, 242));
        seat5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat5.setBorder(null);
        seat5.setContentAreaFilled(false);
        seat5.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat5, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 58, -1, -1));

        seat9.setBackground(new java.awt.Color(242, 242, 242));
        seat9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat9.setBorder(null);
        seat9.setContentAreaFilled(false);
        seat9.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat9, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 58, -1, -1));

        seat10.setBackground(new java.awt.Color(242, 242, 242));
        seat10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat10.setBorder(null);
        seat10.setContentAreaFilled(false);
        seat10.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat10.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat10, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 58, -1, -1));

        seat11.setBackground(new java.awt.Color(242, 242, 242));
        seat11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat11.setBorder(null);
        seat11.setContentAreaFilled(false);
        seat11.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat11.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat11, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 112, -1, -1));

        seat12.setBackground(new java.awt.Color(242, 242, 242));
        seat12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat12.setBorder(null);
        seat12.setContentAreaFilled(false);
        seat12.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat12.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat12, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 112, -1, -1));

        seat13.setBackground(new java.awt.Color(242, 242, 242));
        seat13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat13.setBorder(null);
        seat13.setContentAreaFilled(false);
        seat13.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat13.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat13, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 112, -1, -1));

        seat14.setBackground(new java.awt.Color(242, 242, 242));
        seat14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat14.setBorder(null);
        seat14.setContentAreaFilled(false);
        seat14.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat14.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat14, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 112, -1, -1));

        seat15.setBackground(new java.awt.Color(242, 242, 242));
        seat15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat15.setBorder(null);
        seat15.setContentAreaFilled(false);
        seat15.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat15.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat15, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 112, -1, -1));

        seat16.setBackground(new java.awt.Color(242, 242, 242));
        seat16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat16.setBorder(null);
        seat16.setContentAreaFilled(false);
        seat16.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat16.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat16, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 112, -1, -1));

        seat17.setBackground(new java.awt.Color(242, 242, 242));
        seat17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat17.setBorder(null);
        seat17.setContentAreaFilled(false);
        seat17.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat17.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat17, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 112, -1, -1));

        seat18.setBackground(new java.awt.Color(242, 242, 242));
        seat18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat18.setBorder(null);
        seat18.setContentAreaFilled(false);
        seat18.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat18.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat18, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 112, -1, -1));

        seat19.setBackground(new java.awt.Color(242, 242, 242));
        seat19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat19.setBorder(null);
        seat19.setContentAreaFilled(false);
        seat19.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat19.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat19, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 112, -1, -1));

        seat20.setBackground(new java.awt.Color(242, 242, 242));
        seat20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat20.setBorder(null);
        seat20.setContentAreaFilled(false);
        seat20.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat20.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat20, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 112, -1, -1));

        seat21.setBackground(new java.awt.Color(242, 242, 242));
        seat21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat21.setBorder(null);
        seat21.setContentAreaFilled(false);
        seat21.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat21.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat21, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 166, -1, -1));

        seat22.setBackground(new java.awt.Color(242, 242, 242));
        seat22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat22.setBorder(null);
        seat22.setContentAreaFilled(false);
        seat22.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat22.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat22, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 166, -1, -1));

        seat23.setBackground(new java.awt.Color(242, 242, 242));
        seat23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat23.setBorder(null);
        seat23.setContentAreaFilled(false);
        seat23.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat23.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat23, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 166, -1, -1));

        seat24.setBackground(new java.awt.Color(242, 242, 242));
        seat24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat24.setBorder(null);
        seat24.setContentAreaFilled(false);
        seat24.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat24.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat24, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 166, -1, -1));

        seat25.setBackground(new java.awt.Color(242, 242, 242));
        seat25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat25.setBorder(null);
        seat25.setContentAreaFilled(false);
        seat25.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat25.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat25, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 166, -1, -1));

        seat26.setBackground(new java.awt.Color(242, 242, 242));
        seat26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat26.setBorder(null);
        seat26.setContentAreaFilled(false);
        seat26.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat26.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat26, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 166, -1, -1));

        seat27.setBackground(new java.awt.Color(242, 242, 242));
        seat27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat27.setBorder(null);
        seat27.setContentAreaFilled(false);
        seat27.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat27.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat27, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 166, -1, -1));

        seat28.setBackground(new java.awt.Color(242, 242, 242));
        seat28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat28.setBorder(null);
        seat28.setContentAreaFilled(false);
        seat28.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat28.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat28, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 166, -1, -1));

        seat29.setBackground(new java.awt.Color(242, 242, 242));
        seat29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat29.setBorder(null);
        seat29.setContentAreaFilled(false);
        seat29.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat29.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat29, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 166, -1, -1));

        seat30.setBackground(new java.awt.Color(242, 242, 242));
        seat30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat30.setBorder(null);
        seat30.setContentAreaFilled(false);
        seat30.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat30.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat30, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 166, -1, -1));

        seat31.setBackground(new java.awt.Color(242, 242, 242));
        seat31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat31.setBorder(null);
        seat31.setContentAreaFilled(false);
        seat31.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat31.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat31, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 220, -1, -1));

        seat32.setBackground(new java.awt.Color(242, 242, 242));
        seat32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat32.setBorder(null);
        seat32.setContentAreaFilled(false);
        seat32.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat32.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat32, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 220, -1, -1));

        seat33.setBackground(new java.awt.Color(242, 242, 242));
        seat33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat33.setBorder(null);
        seat33.setContentAreaFilled(false);
        seat33.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat33.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat33, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 220, -1, -1));

        seat34.setBackground(new java.awt.Color(242, 242, 242));
        seat34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat34.setBorder(null);
        seat34.setContentAreaFilled(false);
        seat34.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat34.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat34, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 220, -1, -1));

        seat35.setBackground(new java.awt.Color(242, 242, 242));
        seat35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat35.setBorder(null);
        seat35.setContentAreaFilled(false);
        seat35.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat35.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat35, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 220, -1, -1));

        seat36.setBackground(new java.awt.Color(242, 242, 242));
        seat36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat36.setBorder(null);
        seat36.setContentAreaFilled(false);
        seat36.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat36.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat36, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 220, -1, -1));

        seat37.setBackground(new java.awt.Color(242, 242, 242));
        seat37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat37.setBorder(null);
        seat37.setContentAreaFilled(false);
        seat37.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat37.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat37, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 220, -1, -1));

        seat38.setBackground(new java.awt.Color(242, 242, 242));
        seat38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat38.setBorder(null);
        seat38.setContentAreaFilled(false);
        seat38.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat38.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat38, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 220, -1, -1));

        seat39.setBackground(new java.awt.Color(242, 242, 242));
        seat39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat39.setBorder(null);
        seat39.setContentAreaFilled(false);
        seat39.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat39.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat39, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 220, -1, -1));

        seat40.setBackground(new java.awt.Color(242, 242, 242));
        seat40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat40.setBorder(null);
        seat40.setContentAreaFilled(false);
        seat40.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat40.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat40, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 220, -1, -1));

        seat41.setBackground(new java.awt.Color(242, 242, 242));
        seat41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat41.setBorder(null);
        seat41.setContentAreaFilled(false);
        seat41.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat41.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat41, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 274, -1, -1));

        seat42.setBackground(new java.awt.Color(242, 242, 242));
        seat42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat42.setBorder(null);
        seat42.setContentAreaFilled(false);
        seat42.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat42.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat42, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 274, -1, -1));

        seat43.setBackground(new java.awt.Color(242, 242, 242));
        seat43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat43.setBorder(null);
        seat43.setContentAreaFilled(false);
        seat43.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat43.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat43, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 274, -1, -1));

        seat44.setBackground(new java.awt.Color(242, 242, 242));
        seat44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat44.setBorder(null);
        seat44.setContentAreaFilled(false);
        seat44.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat44.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat44, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 274, -1, -1));

        seat45.setBackground(new java.awt.Color(242, 242, 242));
        seat45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat45.setBorder(null);
        seat45.setContentAreaFilled(false);
        seat45.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat45.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat45, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 274, -1, -1));

        seat46.setBackground(new java.awt.Color(242, 242, 242));
        seat46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat46.setBorder(null);
        seat46.setContentAreaFilled(false);
        seat46.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat46.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat46, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 274, -1, -1));

        seat47.setBackground(new java.awt.Color(242, 242, 242));
        seat47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat47.setBorder(null);
        seat47.setContentAreaFilled(false);
        seat47.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat47.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat47, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 274, -1, -1));

        seat48.setBackground(new java.awt.Color(242, 242, 242));
        seat48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat48.setBorder(null);
        seat48.setContentAreaFilled(false);
        seat48.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat48.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat48, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 274, -1, -1));

        seat49.setBackground(new java.awt.Color(242, 242, 242));
        seat49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat49.setBorder(null);
        seat49.setContentAreaFilled(false);
        seat49.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat49.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat49, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 274, -1, -1));

        seat50.setBackground(new java.awt.Color(242, 242, 242));
        seat50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat50.setBorder(null);
        seat50.setContentAreaFilled(false);
        seat50.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat50.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat50, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 274, -1, -1));

        seat51.setBackground(new java.awt.Color(242, 242, 242));
        seat51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat51.setBorder(null);
        seat51.setContentAreaFilled(false);
        seat51.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat51.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat51, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 328, -1, -1));

        seat52.setBackground(new java.awt.Color(242, 242, 242));
        seat52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat52.setBorder(null);
        seat52.setContentAreaFilled(false);
        seat52.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat52.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat52, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 328, -1, -1));

        seat53.setBackground(new java.awt.Color(242, 242, 242));
        seat53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat53.setBorder(null);
        seat53.setContentAreaFilled(false);
        seat53.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat53.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat53, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 328, -1, -1));

        seat54.setBackground(new java.awt.Color(242, 242, 242));
        seat54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat54.setBorder(null);
        seat54.setContentAreaFilled(false);
        seat54.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat54.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat54, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 328, -1, -1));

        seat55.setBackground(new java.awt.Color(242, 242, 242));
        seat55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat55.setBorder(null);
        seat55.setContentAreaFilled(false);
        seat55.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat55.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat55, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 328, -1, -1));

        seat56.setBackground(new java.awt.Color(242, 242, 242));
        seat56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat56.setBorder(null);
        seat56.setContentAreaFilled(false);
        seat56.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat56.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat56, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 328, -1, -1));

        seat57.setBackground(new java.awt.Color(242, 242, 242));
        seat57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat57.setBorder(null);
        seat57.setContentAreaFilled(false);
        seat57.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat57.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat57, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 328, -1, -1));

        seat58.setBackground(new java.awt.Color(242, 242, 242));
        seat58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat58.setBorder(null);
        seat58.setContentAreaFilled(false);
        seat58.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat58.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat58, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 328, -1, -1));

        seat59.setBackground(new java.awt.Color(242, 242, 242));
        seat59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat59.setBorder(null);
        seat59.setContentAreaFilled(false);
        seat59.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat59.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat59, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 328, -1, -1));

        seat60.setBackground(new java.awt.Color(242, 242, 242));
        seat60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat60.setBorder(null);
        seat60.setContentAreaFilled(false);
        seat60.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat60.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat60, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 328, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SCREEN");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 6, 534, 40));

        jLabelPoster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poster.jpg"))); // NOI18N
        add(jLabelPoster, new org.netbeans.lib.awtextra.AbsoluteConstraints(711, 6, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Name:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 276, -1, -1));

        jLabelName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelName.setText("Avengers Endgame");
        add(jLabelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(692, 276, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Duration:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 326, -1, -1));

        jLabelDuration.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelDuration.setText("1h56m");
        add(jLabelDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(722, 326, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Price:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(822, 326, -1, -1));

        jLabelPrice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelPrice.setText("4$");
        add(jLabelPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(882, 326, -1, -1));

        jLabelTime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelTime.setText("13:00");
        add(jLabelTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(882, 376, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Time:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(822, 376, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Date:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 376, -1, -1));

        jLabelTotal.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelTotal.setText("0$");
        add(jLabelTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(709, 576, -1, -1));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextPaneSeats.setEditable(false);
        jTextPaneSeats.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextPaneSeats.getStyledDocument();
        jScrollPane1.setViewportView(jTextPaneSeats);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(686, 430, 240, 128));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Seat:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 430, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("B");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 123, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("C");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 176, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("D");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 232, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("E");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 285, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("F");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 337, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("G");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 395, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("H");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 448, -1, -1));

        seat61.setBackground(new java.awt.Color(242, 242, 242));
        seat61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat61.setBorder(null);
        seat61.setContentAreaFilled(false);
        seat61.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat61.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat61, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 382, -1, -1));

        seat62.setBackground(new java.awt.Color(242, 242, 242));
        seat62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat62.setBorder(null);
        seat62.setContentAreaFilled(false);
        seat62.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat62.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat62, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 382, -1, -1));

        seat63.setBackground(new java.awt.Color(242, 242, 242));
        seat63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat63.setBorder(null);
        seat63.setContentAreaFilled(false);
        seat63.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat63.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat63, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 382, -1, -1));

        seat64.setBackground(new java.awt.Color(242, 242, 242));
        seat64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat64.setBorder(null);
        seat64.setContentAreaFilled(false);
        seat64.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat64.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat64, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 382, -1, -1));

        seat65.setBackground(new java.awt.Color(242, 242, 242));
        seat65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat65.setBorder(null);
        seat65.setContentAreaFilled(false);
        seat65.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat65.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat65, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 382, -1, -1));

        seat66.setBackground(new java.awt.Color(242, 242, 242));
        seat66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat66.setBorder(null);
        seat66.setContentAreaFilled(false);
        seat66.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat66.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat66, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 382, -1, -1));

        seat67.setBackground(new java.awt.Color(242, 242, 242));
        seat67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat67.setBorder(null);
        seat67.setContentAreaFilled(false);
        seat67.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat67.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat67, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 382, -1, -1));

        seat68.setBackground(new java.awt.Color(242, 242, 242));
        seat68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat68.setBorder(null);
        seat68.setContentAreaFilled(false);
        seat68.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat68.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat68, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 382, -1, -1));

        seat69.setBackground(new java.awt.Color(242, 242, 242));
        seat69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat69.setBorder(null);
        seat69.setContentAreaFilled(false);
        seat69.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat69.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat69, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 382, -1, -1));

        seat70.setBackground(new java.awt.Color(242, 242, 242));
        seat70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat70.setBorder(null);
        seat70.setContentAreaFilled(false);
        seat70.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat70.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat70, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 382, -1, -1));

        seat80.setBackground(new java.awt.Color(242, 242, 242));
        seat80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat80.setBorder(null);
        seat80.setContentAreaFilled(false);
        seat80.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat80.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat80, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 436, -1, -1));

        seat79.setBackground(new java.awt.Color(242, 242, 242));
        seat79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat79.setBorder(null);
        seat79.setContentAreaFilled(false);
        seat79.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat79.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat79, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 436, -1, -1));

        seat78.setBackground(new java.awt.Color(242, 242, 242));
        seat78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat78.setBorder(null);
        seat78.setContentAreaFilled(false);
        seat78.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat78.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat78, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 436, -1, -1));

        seat77.setBackground(new java.awt.Color(242, 242, 242));
        seat77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat77.setBorder(null);
        seat77.setContentAreaFilled(false);
        seat77.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat77.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat77, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 436, -1, -1));

        seat76.setBackground(new java.awt.Color(242, 242, 242));
        seat76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat76.setBorder(null);
        seat76.setContentAreaFilled(false);
        seat76.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat76.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat76, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 436, -1, -1));

        seat75.setBackground(new java.awt.Color(242, 242, 242));
        seat75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat75.setBorder(null);
        seat75.setContentAreaFilled(false);
        seat75.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat75.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat75, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 436, -1, -1));

        seat74.setBackground(new java.awt.Color(242, 242, 242));
        seat74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat74.setBorder(null);
        seat74.setContentAreaFilled(false);
        seat74.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat74.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat74, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 436, -1, -1));

        seat73.setBackground(new java.awt.Color(242, 242, 242));
        seat73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat73.setBorder(null);
        seat73.setContentAreaFilled(false);
        seat73.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat73.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat73, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 436, -1, -1));

        seat72.setBackground(new java.awt.Color(242, 242, 242));
        seat72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat72.setBorder(null);
        seat72.setContentAreaFilled(false);
        seat72.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat72.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat72, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 436, -1, -1));

        seat71.setBackground(new java.awt.Color(242, 242, 242));
        seat71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        seat71.setBorder(null);
        seat71.setContentAreaFilled(false);
        seat71.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        seat71.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        add(seat71, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 436, -1, -1));

        jButton2.setBackground(new java.awt.Color(204, 255, 204));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/success.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 660, 70, 40));

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/remove.png"))); // NOI18N
        jButton3.setToolTipText("Remove");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 660, 70, 40));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noseat.png"))); // NOI18N
        jLabel16.setText("Seet have bean selected");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 540, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casualseat.png"))); // NOI18N
        jLabel17.setText("Casual Seat");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 540, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/selectseat.png"))); // NOI18N
        jLabel18.setText("Seat Selecting");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 540, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setText("Total:");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 576, -1, -1));

        jLabelDate1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelDate1.setText("22/01/24");
        add(jLabelDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 376, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("A");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 69, -1, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("   1         2         3        4         5         6         7        8         9        10");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 580, -1));

        jLabelMess.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelMess.setForeground(new java.awt.Color(255, 51, 51));
        jLabelMess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMess.setText("Please choose at least one seat");
        add(jLabelMess, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 610, 450, 40));
        jLabelMess.setVisible(false);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jLabelMess.setVisible(false);
        if (jLabelTotal.getText().equals("0$")) {
            jLabelMess.setVisible(true);
        } else {
            int result = JOptionPane.showConfirmDialog(this, "Confirm","", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                payment();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        am.disposeChooseSeat();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void seat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat1ActionPerformed
        JToggleButton btn = (JToggleButton) evt.getSource();
        System.out.println(btn.isSelected());
    }//GEN-LAST:event_seat1ActionPerformed

    private void seat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat2ActionPerformed
        // TODO add your handling code here:
        JToggleButton btn = (JToggleButton) evt.getSource();
        System.out.println(btn.isSelected());
    }//GEN-LAST:event_seat2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDate1;
    private javax.swing.JLabel jLabelDuration;
    private javax.swing.JLabel jLabelMess;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPoster;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabelTime;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPaneSeats;
    private javax.swing.JToggleButton seat1;
    private javax.swing.JToggleButton seat10;
    private javax.swing.JToggleButton seat11;
    private javax.swing.JToggleButton seat12;
    private javax.swing.JToggleButton seat13;
    private javax.swing.JToggleButton seat14;
    private javax.swing.JToggleButton seat15;
    private javax.swing.JToggleButton seat16;
    private javax.swing.JToggleButton seat17;
    private javax.swing.JToggleButton seat18;
    private javax.swing.JToggleButton seat19;
    private javax.swing.JToggleButton seat2;
    private javax.swing.JToggleButton seat20;
    private javax.swing.JToggleButton seat21;
    private javax.swing.JToggleButton seat22;
    private javax.swing.JToggleButton seat23;
    private javax.swing.JToggleButton seat24;
    private javax.swing.JToggleButton seat25;
    private javax.swing.JToggleButton seat26;
    private javax.swing.JToggleButton seat27;
    private javax.swing.JToggleButton seat28;
    private javax.swing.JToggleButton seat29;
    private javax.swing.JToggleButton seat3;
    private javax.swing.JToggleButton seat30;
    private javax.swing.JToggleButton seat31;
    private javax.swing.JToggleButton seat32;
    private javax.swing.JToggleButton seat33;
    private javax.swing.JToggleButton seat34;
    private javax.swing.JToggleButton seat35;
    private javax.swing.JToggleButton seat36;
    private javax.swing.JToggleButton seat37;
    private javax.swing.JToggleButton seat38;
    private javax.swing.JToggleButton seat39;
    private javax.swing.JToggleButton seat4;
    private javax.swing.JToggleButton seat40;
    private javax.swing.JToggleButton seat41;
    private javax.swing.JToggleButton seat42;
    private javax.swing.JToggleButton seat43;
    private javax.swing.JToggleButton seat44;
    private javax.swing.JToggleButton seat45;
    private javax.swing.JToggleButton seat46;
    private javax.swing.JToggleButton seat47;
    private javax.swing.JToggleButton seat48;
    private javax.swing.JToggleButton seat49;
    private javax.swing.JToggleButton seat5;
    private javax.swing.JToggleButton seat50;
    private javax.swing.JToggleButton seat51;
    private javax.swing.JToggleButton seat52;
    private javax.swing.JToggleButton seat53;
    private javax.swing.JToggleButton seat54;
    private javax.swing.JToggleButton seat55;
    private javax.swing.JToggleButton seat56;
    private javax.swing.JToggleButton seat57;
    private javax.swing.JToggleButton seat58;
    private javax.swing.JToggleButton seat59;
    private javax.swing.JToggleButton seat6;
    private javax.swing.JToggleButton seat60;
    private javax.swing.JToggleButton seat61;
    private javax.swing.JToggleButton seat62;
    private javax.swing.JToggleButton seat63;
    private javax.swing.JToggleButton seat64;
    private javax.swing.JToggleButton seat65;
    private javax.swing.JToggleButton seat66;
    private javax.swing.JToggleButton seat67;
    private javax.swing.JToggleButton seat68;
    private javax.swing.JToggleButton seat69;
    private javax.swing.JToggleButton seat7;
    private javax.swing.JToggleButton seat70;
    private javax.swing.JToggleButton seat71;
    private javax.swing.JToggleButton seat72;
    private javax.swing.JToggleButton seat73;
    private javax.swing.JToggleButton seat74;
    private javax.swing.JToggleButton seat75;
    private javax.swing.JToggleButton seat76;
    private javax.swing.JToggleButton seat77;
    private javax.swing.JToggleButton seat78;
    private javax.swing.JToggleButton seat79;
    private javax.swing.JToggleButton seat8;
    private javax.swing.JToggleButton seat80;
    private javax.swing.JToggleButton seat9;
    // End of variables declaration//GEN-END:variables
}
