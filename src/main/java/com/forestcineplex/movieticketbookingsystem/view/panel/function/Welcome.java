/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.view.panel.function;

import com.forestcineplex.movieticketbookingsystem.data.DataConnection;
import com.forestcineplex.movieticketbookingsystem.model.ForestCineplexModel;
import com.forestcineplex.movieticketbookingsystem.model.MovieModel;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;


/**
 *
 * @author LENOVO
 */
public class Welcome extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    
    public String Doubleformat(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(number);       
    }
    
    public void loadDashBoard() {
        double budget = 0;
        try (ResultSet resultSet = DataConnection.executeQuery("SELECT SUM(Total) FROM revenue")) {
            if (resultSet != null && resultSet.next()) {
                do {
                    budget = resultSet.getDouble(1);
                } while (resultSet.next());
      
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jLabelTotalIncome.setText(budget+"");
        
        int QuantityAvaiable = 0;
        try (ResultSet resultSet = DataConnection.executeQuery("SELECT COUNT(ID) FROM availablemovie")) {
            if (resultSet != null && resultSet.next()) {
                do {
                    QuantityAvaiable = resultSet.getInt(1);
                } while (resultSet.next());
      
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jLabelTotalAvaiableMovie.setText(QuantityAvaiable+"");
        
        
        int QuantityTicketSold = 0;
        try (ResultSet resultSet = DataConnection.executeQuery("SELECT SUM(Quantity) FROM revenue WHERE Source = 'Movie'")) {
            if (resultSet != null && resultSet.next()) {
                do {
                    QuantityTicketSold = resultSet.getInt(1);
                } while (resultSet.next());
      
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        
        
        jLabelTotalSoldTicket.setText(QuantityTicketSold+"");
        
        jPanelChart.removeAll();
        showChart();
    } 
    
    private CategoryDataset createDataset() {
        String query = "SELECT t.MonthYear, COALESCE(SUM(r.`Total`), 0) AS MonthlyRevenue FROM ( SELECT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL n MONTH), '%Y-%m-01') AS MonthYear FROM ( SELECT 0 AS n UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 ) AS n_values ) t LEFT JOIN `revenue` r ON t.MonthYear = DATE_FORMAT(r.`Time`, '%Y-%m-01') GROUP BY t.MonthYear ORDER BY t.MonthYear ASC;";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        try (ResultSet resultSet = DataConnection.executeQuery(query)) {
            if (resultSet != null && resultSet.next()) {
                do {
                    Date date = resultSet.getDate(1);
                    double total = resultSet.getDouble(2);
                    dataset.addValue(total, "", simpleDateFormat.format(date));
                } while (resultSet.next());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }
    
    public void showChart() {
        CategoryDataset dataset = createDataset();
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Revenue over the past 12 months",
                "",
                "Revenue($)",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );
        lineChart.setBackgroundPaint(new Color(204,255,204));
        CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRange(true);
        rangeAxis.setAutoRangeIncludesZero(false);

        CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        plot.getRenderer().setBaseItemLabelGenerator(generator);
        plot.getRenderer().setBaseItemLabelsVisible(true);
        plot.getRenderer().setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER
        ));
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(890, 430));
        jPanelChart.add(chartPanel);

    }


    
    
    public Welcome() {
        initComponents();
        loadDashBoard();
        showChart();
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTotalAvaiableMovie = new javax.swing.JLabel();
        jLabelTotalIncome = new javax.swing.JLabel();
        jLabelTotalSoldTicket = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanelChart = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 255, 204));
        setMinimumSize(new java.awt.Dimension(650, 650));
        setPreferredSize(new java.awt.Dimension(970, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTotalAvaiableMovie.setFont(new java.awt.Font("Arial", 1, 40)); // NOI18N
        jLabelTotalAvaiableMovie.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTotalAvaiableMovie.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotalAvaiableMovie.setText("0");
        add(jLabelTotalAvaiableMovie, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, 250, -1));

        jLabelTotalIncome.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabelTotalIncome.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTotalIncome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotalIncome.setText("0");
        add(jLabelTotalIncome, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 250, -1));

        jLabelTotalSoldTicket.setFont(new java.awt.Font("Arial", 1, 40)); // NOI18N
        jLabelTotalSoldTicket.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTotalSoldTicket.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotalSoldTicket.setText("0");
        add(jLabelTotalSoldTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 110, 250, -1));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movie48.png"))); // NOI18N
        jLabel6.setText("Available Movie");
        jLabel6.setFocusable(false);
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 230, -1));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coin48.png"))); // NOI18N
        jLabel5.setText("Total Income");
        jLabel5.setFocusable(false);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 230, -1));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ticket48.png"))); // NOI18N
        jLabel4.setText("Total Sold Ticket");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));
        jLabel4.getAccessibleContext().setAccessibleName("Sold Ticket");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shape4.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shape4.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shape4.png"))); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        jPanelChart.setBackground(new java.awt.Color(204, 255, 204));
        add(jPanelChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 890, 430));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelTotalAvaiableMovie;
    private javax.swing.JLabel jLabelTotalIncome;
    private javax.swing.JLabel jLabelTotalSoldTicket;
    private javax.swing.JPanel jPanelChart;
    // End of variables declaration//GEN-END:variables
}
