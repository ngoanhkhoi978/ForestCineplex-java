/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.model;

import java.sql.Timestamp;

/**
 *
 * @author LENOVO
 */
public class AvailableMovieModel {
    
    private MovieModel movieModel;
    private int ID;
    private SeatModel seatModel;
    private Timestamp showDate;

    public AvailableMovieModel(MovieModel movieModel, int ID, SeatModel seatModel, Timestamp showDate) {
        this.movieModel = movieModel;
        this.ID = ID;
        this.seatModel = seatModel;
        this.showDate = showDate;
    }
    
    

    public MovieModel getMovieModel() {
        return movieModel;
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public SeatModel getSeatModel() {
        return seatModel;
    }

    public void setSeatModel(SeatModel seatModel) {
        this.seatModel = seatModel;
    }

    public Timestamp getShowDate() {
        return showDate;
    }

    public void setShowDate(Timestamp showDate) {
        this.showDate = showDate;
    }
    
    
    
    
    
    
}
