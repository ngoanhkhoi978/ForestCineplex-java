/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.model;

import com.forestcineplex.movieticketbookingsystem.data.DataConnection;

/**
 *
 * @author LENOVO
 */
public class ForestCineplexModel {
    private double budget;
    private int  ticketSold;
    private int availableMovie;

    public ForestCineplexModel(double budget, int ticketSold, int availableMovie) {
        this.budget = budget;
        this.ticketSold = ticketSold;
        this.availableMovie = availableMovie;
    }
    
    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getTicketSold() {
        return ticketSold;
    }

    public void setTicketSold(int ticketSold) {
        this.ticketSold = ticketSold;
    }

    public int getAvailableMovie() {
        return availableMovie;
    }

    public void setAvailableMovie(int availableMovie) {
        this.availableMovie = availableMovie;
    }
    
    public void updateAvailableMovie(int availableMovie) {
        setAvailableMovie(availableMovie);
        DataConnection.executeUpdate("UPDATE mycompany SET AvailableMovie = ?", String.valueOf(availableMovie));
        
    }
    
    public void updateTicketSold(int ticketSold) {
        setTicketSold(ticketSold);
        DataConnection.executeUpdate("UPDATE mycompany SET TicketSold = ?", String.valueOf(ticketSold));
    }
    
    public void updateBudget(double budget) {
        setBudget(budget);
        DataConnection.executeUpdate("UPDATE mycompany SET Budget = ?", String.valueOf(budget));
    }
    
    
}
