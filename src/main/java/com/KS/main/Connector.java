package com.KS.main;

import java.sql.Connection;
import java.sql.SQLException;

public class Connector {

    private static Connector instance;
    private Connection connection;

    public static Connector getInstance(){
        if(instance == null){
            instance = new Connector();
        }
        return instance;
    }

    private Connector(){

    }

    public void connectTODatavase() throws  SQLException{
        connection = java.sql.DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atm_db","root","root");
    }

    public Connection getConnection(){
        return connection;
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

}
