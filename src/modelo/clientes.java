/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hugoi
 */
public class clientes extends conexion implements sentencias  {
    private int IDClientes,ruc_cl,tel_cl;
    private String nom_cl,apellido_cl,dire_cl;    

    public clientes(int IDClientes, int ruc_cl, int tel_cl, String nom_cl, String apellido_cl, String dire_cl) {
        this.IDClientes = IDClientes;
        this.ruc_cl = ruc_cl;
        this.tel_cl = tel_cl;
        this.nom_cl = nom_cl;
        this.apellido_cl = apellido_cl;
        this.dire_cl = dire_cl;
    }
    

    public clientes(){
        
    }

    public int getIDClientes() {
        return IDClientes;
    }

    public void setIDClientes(int IDClientes) {
        this.IDClientes = IDClientes;
    }

    public int getRuc_cl() {
        return ruc_cl;
    }

    public void setRuc_cl(int ruc_cl) {
        this.ruc_cl = ruc_cl;
    }

    public int getTel_cl() {
        return tel_cl;
    }

    public void setTel_cl(int tel_cl) {
        this.tel_cl = tel_cl;
    }

    public String getNom_cl() {
        return nom_cl;
    }

    public void setNom_cl(String nom_cl) {
        this.nom_cl = nom_cl;
    }

    public String getApellido_cl() {
        return apellido_cl;
    }

    public void setApellido_cl(String apellido_cl) {
        this.apellido_cl = apellido_cl;
    }

    public String getDire_cl() {
        return dire_cl;
    }

    public void setDire_cl(String dire_cl) {
        this.dire_cl = dire_cl;
    }

    
    @Override
    public boolean insertar() {
        try {
            //INSERT INTO `proyecto`.`clientes` (`nom_cl`, `apellido_cl`, `ruc_cl`, `dire_cl`, `tel_cl`) VALUES ('sdsd', 'fff', '32', '1', '74');
            String sql="insert into `proyecto`.`clientes` (`nom_cl`, `apellido_cl`, `ruc_cl`,`dire_cl`,`tel_cl`) VALUES ('"+getNom_cl()+"','"+getApellido_cl()+"','"+getRuc_cl()+"','"+getDire_cl()+"','"+getTel_cl()+"');";
            System.out.println(sql);
            Statement query=getCon().createStatement();
            query.executeUpdate(sql);
            System.out.println("verificacion nombre clientes");
            System.out.println(getNom_cl());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(clientes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList consultar() {
        ArrayList<clientes> cli=new ArrayList<>();
        try {
            System.out.println("Se llama a consuktat cliente ");
            String sql="Select * from clientes";
            Statement query=getCon().createStatement();
            ResultSet rs=query.executeQuery(sql);
            System.out.println("Se llama a consuktat cliente Despues del execute");
            while(rs.next()){
                int codigo=rs.getInt(1);
                int telefono =rs.getInt(6);
                int ruc = rs.getInt(4);
                String nombre=rs.getString(2);
                String apellido=rs.getString(3);
                String direccion=rs.getString(5);
                //crear un objeto de la clase alumno
                clientes a=new clientes(codigo,ruc,telefono,nombre,apellido,direccion);
                cli.add(a);
            }//fin del while
        } catch (SQLException ex) {
            Logger.getLogger(clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("respuesta del select: ");
        System.out.println(cli);
        
        return cli;
    }

       @Override
    public boolean modificar() {
        //UPDATE `proyecto`.`clientes` SET `nom_cl` = 'fdfd', `apellido_cl` = 'asdasdsa', `ruc_cl` = '323', `dire_cl` = 'ff', `tel_cl` = '43' WHERE (`IDClientes` = '1');
        try {
            String sql= "UPDATE `proyecto`.`clientes` SET `nom_cl` = '"+getNom_cl()+"', `apellido_cl` = '"+getRuc_cl()+"', `dire_cl` = '"+getDire_cl()+"' WHERE (`IDClientes` = '"+getIDClientes()+"');";
            Statement query=getCon().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(clientes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public boolean eliminar() {
        try {
            //DELETE FROM `proyecto`.`clientes` WHERE (`IDClientes` = '7');
            String sql= "DELETE FROM `proyecto`.`clientes` WHERE (`IDClientes` = '" + getIDClientes() +"');";
            Statement query=getCon().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(clientes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
