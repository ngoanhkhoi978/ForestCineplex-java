/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.forestcineplex.movieticketbookingsystem;

import com.forestcineplex.movieticketbookingsystem.data.DataConnection;
import com.forestcineplex.movieticketbookingsystem.model.User;
import com.forestcineplex.movieticketbookingsystem.view.frame.LobbySystem;
import com.forestcineplex.movieticketbookingsystem.view.frame.MainSystem;
import com.formdev.flatlaf.FlatLightLaf;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.UIManager;

/**
 *
 * @author LENOVO
 */
public class MovieTicketBookingSystem {

    public static void main(String[] args) throws SQLException {
        try {
            Connection connection = DataConnection.connect();
            DataConnection.close(connection);

            UIManager.setLookAndFeel( new FlatLightLaf() );
            LobbySystem main = new LobbySystem();
//            String username = "admin";
//            String passwordHash = "123";
//            String role = "Admin";
//            String fullname = "Admin";
//            String gender = "Male";
//            String address = "";
//            String mail = "admin@admin.com";
//            String phone = "0999999999";
//            Date birthDate = new Date(2005, 2, 2);
//            User user = new User(username, passwordHash, role, fullname, gender, 0, address, phone, mail, birthDate);
//            MainSystem mainSystem = new MainSystem(user);
            
        } catch( CommunicationsException e ) {
            e.printStackTrace();
        } catch ( Exception e) {
            e.printStackTrace();
            
        }

        
    }
}
