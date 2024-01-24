/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.model;

/**
 *
 * @author LENOVO
 */
public class SeatModel {
    
    private String seats;
    
    public SeatModel(String seats) {
        this.seats = seats;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
    
    public boolean checkIdSeat(int ID) {
        char[] seat = seats.toCharArray();
        if (seat[ID-1] == '1')
            return true;
        return false;
    }
    
    public void setStatusSeat(int ID,boolean status) {
        char[] seat = seats.toCharArray();
        if (status==true)
            seat[ID-1] = '1';
        else
            seat[ID-1] = '0';
        setSeats(new String(seat));
    }
    
    public int QuantityEmptySeat() {
        int count = 0;
        char[] seat = seats.toCharArray();
        for (char s: seat) {
            if (s=='0')
                count += 1;
        }
        return count;
    }
    
    public static String getTypeSeet(int ID) {
        int a = ID/(int)10;
        int b = ID%10;
        if (b==0) {
            b = 10;
            a -= 1;
        }
        return (char)(65+a)+""+b;
        
    }
    
    

    
    
    
}
