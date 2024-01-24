/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author LENOVO
 */
public class MovieModel {
    private int ID;
    private String name;
    private ImageIcon image;
    private double price;
    private long duration;
    private Timestamp TimeCreated;
    private String describe;

    public MovieModel() {
        
    }
    
    public MovieModel(int ID, String name, ImageIcon image, double price, long duration, Timestamp TimeCreated, String describe) {
        this.ID = ID;
        this.name = name;
        this.image = image;
        this.price = price;
        this.duration = duration;
        this.TimeCreated = TimeCreated;
        this.describe = describe;
    }
    
    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Timestamp getTimeCreated() {
        return TimeCreated;
    }

    public void setTimeCreated(Timestamp TimeCreated) {
        this.TimeCreated = TimeCreated;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
    
    


    

    
    
    
    
    
    
    
    
    
    
   
    
    
    
    
    
    
}
