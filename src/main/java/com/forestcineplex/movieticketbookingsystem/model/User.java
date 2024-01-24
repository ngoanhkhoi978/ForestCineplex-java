/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.model;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class User {
    private String user;
    private String passwordHash;
    private String role;
    private String fullname;
    private String gender;
    private double salary;
    private String address;
    private String phone;
    private String mail;
    private Date birthDate;

    public User(String user, String passwordHash, String role, String fullname, String gender, double salary, String address, String phone, String mail, Date birthDate) {
        this.user = user;
        this.passwordHash = passwordHash;
        this.role = role;
        this.fullname = fullname;
        this.gender = gender;
        this.salary = salary;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.birthDate = birthDate;
    }
    
    
    public User(String user, String passwordHash, String role) {
        this.user = user;
        this.passwordHash = passwordHash;
        this.role = role;
    }
    
    

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    
    
    
    
    
    
}
