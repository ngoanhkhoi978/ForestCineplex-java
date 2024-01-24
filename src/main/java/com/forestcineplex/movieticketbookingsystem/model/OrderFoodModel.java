/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.model;


import com.forestcineplex.movieticketbookingsystem.data.DataConnection;
import java.sql.Timestamp;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class OrderFoodModel {
    
    private int index;
    private Timestamp time;
    private String userOrder;
    private double total;
    private String history;

    public OrderFoodModel(int index, Timestamp time, String userOrder, double total, String history) {
        this.index = index;
        this.time = time;
        this.userOrder = userOrder;
        this.total = total;
        this.history = history;
    }
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(String userOrder) {
        this.userOrder = userOrder;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    
    
    
    
}
