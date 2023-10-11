/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hugoi
 */
public class conexion {
    private String base;
    private String host;
    private String usuario;
    private String contra;
    private Connection con;

    public conexion(String base, String host, String usuario, String contra) {
        this.base = base;
        this.host = host;
        this.usuario = usuario;
        this.contra = contra;
    }
    
    public conexion() {
        this.base = "proyecto";
        this.host = "localhost";
        this.usuario = "root";
        this.contra = "";
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public Connection getCon() {
        try {
            String url="jdbc:mysql://"+host+"/"+base;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,usuario,contra);
           
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    
}

