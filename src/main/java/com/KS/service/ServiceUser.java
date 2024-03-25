package com.KS.service;


import com.KS.main.Connector;
import com.KS.model.ModleUser;

import java.sql.*;

public class ServiceUser {

    private Connection con;


    public ServiceUser(){
        con = Connector.getInstance().getConnection();
    }

    public void inserUser(ModleUser user) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into `users` (name, lastname, email , `password`) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        p.setString(1, user.getName());
        p.setString(2, user.getLastname());
        p.setString(3, user.getEmail());
        p.setString(4, user.getPassword());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        r.first();
        int userID = r.getInt(1);
        r.close();
        p.close();
        user.setUserId(userID);
    }

    public boolean chechDuplicateUser(String user) throws SQLException{
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

}
