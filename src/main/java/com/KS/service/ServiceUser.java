package com.KS.service;


import com.KS.main.Connector;
import com.KS.model.ModelLogin;
import com.KS.model.ModleUser;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.sql.*;

public class ServiceUser {

    private Connection con;


    public ServiceUser(){
        con = Connector.getInstance().getConnection();
    }

    public ModleUser login(ModelLogin login) throws SQLException{

        ModleUser data = null;

        PreparedStatement p = con.prepareStatement("select idusers, `name`,`lastname`,email, pin, balance , accountnumber  from `users` where binary(email)=? and binary(`password`)=? limit 1",ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );
        p.setString(1,login.getEmail());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();

        if (r.first()){
            int userId = r.getInt(1);
            String name = r.getString(2);
            String lastname = r.getString(3);
            String email = r.getString(4);
            int pin = r.getInt(5);
            double balance = r.getDouble(6);
            String accountBalance = r.getString(7);
            data = new ModleUser(userId,name,lastname,email,"",pin,balance,accountBalance);
        }
        r.close();
        p.close();
        return data;
    }

    public void insertUser(ModleUser user) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into `users` (name, lastname, email , `password`, pin, balance, accountnumber) values (?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        p.setString(1, user.getName());
        p.setString(2, user.getLastname());
        p.setString(3, user.getEmail());
        p.setString(4, user.getPassword());
        p.setInt(5, user.getPin());
        p.setDouble(6, user.getBalance());
        p.setString(7, user.getAccountNumber());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        r.first();
        int userID = r.getInt(1);
        r.close();
        p.close();
        user.setUserId(userID);
    }

    public boolean checkDuplicateUser(String user) throws SQLException{
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select idusers from `users` where email=? limit 1", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public void updateBalance(ModleUser user) throws SQLException{
        PreparedStatement p = con.prepareStatement("UPDATE `atm_db`.`users` SET`balance` = ? WHERE `idusers` = ?");
        p.setDouble(1,user.getBalance());
        p.setInt(2,user.getUserId());
        p.execute();
        p.close();
    }
    public void withdrawMoney(ModleUser user) throws SQLException{

    }

}
