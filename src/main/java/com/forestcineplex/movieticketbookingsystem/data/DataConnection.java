/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class DataConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/forestcineplex";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    private static void setParameters(PreparedStatement preparedStatement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            preparedStatement.setObject(i + 1, parameters[i]);
        }
    }
    
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void close(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        close(resultSet);
        close(preparedStatement);
        close(connection);
    }
    
    public static ResultSet executeQuery(String query, Object... parameters) {
        try {
            Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            setParameters(preparedStatement, parameters);

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }
    
    public static int executeUpdate(String query, Object... parameters){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(query);
            setParameters(preparedStatement, parameters);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(preparedStatement);
        }

    }
    
    public static int insertData(String tableName, String[] columns, Object[] values) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        if (columns.length != values.length) {
            throw new IllegalArgumentException("Number of columns must match the number of values.");
        }

        StringBuilder insertQuery = new StringBuilder("INSERT INTO " + tableName + " (");
        for (int i = 0; i < columns.length; i++) {
            insertQuery.append(columns[i]);
            if (i < columns.length - 1) {
                insertQuery.append(", ");
            }
        }
        insertQuery.append(") VALUES (");
        for (int i = 0; i < values.length; i++) {
            insertQuery.append("?");
            if (i < values.length - 1) {
                insertQuery.append(", ");
            }
        }
        insertQuery.append(")");

        connection = connect();
        preparedStatement = connection.prepareStatement(insertQuery.toString());
        setParameters(preparedStatement, values);

        return preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        close(connection);
        close(preparedStatement);
    }

    return 0; // or -1 to indicate failure
}

    
    
    
}
