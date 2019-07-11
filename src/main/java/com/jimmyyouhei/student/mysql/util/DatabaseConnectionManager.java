package com.jimmyyouhei.student.mysql.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {

    private final String urlInit;
    private final Properties properties;

    private  final String databaseName = "StudentManagement";
    private final String urlDatabaseConnection;

    public DatabaseConnectionManager(String host  , String userName , String password ){
        this.urlInit = "jdbc:mysql://" + host +"/" ;

        this.urlDatabaseConnection = "jdbc:mysql://" + host + "/" + databaseName;
        this.properties = new Properties();
        this.properties.setProperty("user" , userName);
        this.properties.setProperty("password" , password);

    }

    public Connection checkConnection() throws SQLException{

        return DriverManager.getConnection(this.urlInit, this.properties);

    }

    public Connection getDatabaseConnection() throws SQLException{
        return DriverManager.getConnection(this.urlDatabaseConnection,this.properties);
    }

}
