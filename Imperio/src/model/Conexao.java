/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.*;

/**
 *
 * @author will
 */
public class Conexao {
    
    private static final String USUARIO = "root";
    private static final String SENHA = "123456";
    private static final String URL = "jdbc:mysql://localhost:3306/imperio?autoReconnect=true&useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static Connection conn;
    //private static final String DRIVER = "org.gjt.mm.mysql.jdbc.Driver";
    

    
    public static Connection getConnection(){
        if (conn == null){
           conectar(); 
        }
        return conn;
       
    }
    
    // Conectar ao banco
    private static void conectar() {
        
        try {
        Class.forName(DRIVER);
       
       
        conn = DriverManager.getConnection(URL, USUARIO, SENHA);
              
        } catch (Exception e) {
            System.out.println("é nois erro - erro de conexão de BD"+e);
        }
   
        
    
}
}
