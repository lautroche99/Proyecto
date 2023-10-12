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
public class ingredientes extends conexion implements sentencias  {
    private int Cod_ingre, precio_ingre,Cant_ingre;
    private String nom_ingre;    

    public ingredientes(int Cod_ingre, int precio_ingre, int Cant_ingre, String nom_ingre) {
        this.Cod_ingre = Cod_ingre;
        this.precio_ingre = precio_ingre;
        this.Cant_ingre = Cant_ingre;
        this.nom_ingre = nom_ingre;
    }

    public int getCod_ingre() {
        return Cod_ingre;
    }

    public void setCod_ingre(int Cod_ingre) {
        this.Cod_ingre = Cod_ingre;
    }

    public int getPrecio_ingre() {
        return precio_ingre;
    }

    public void setPrecio_ingre(int precio_ingre) {
        this.precio_ingre = precio_ingre;
    }

    public int getCant_ingre() {
        return Cant_ingre;
    }

    public void setCant_ingre(int Cant_ingre) {
        this.Cant_ingre = Cant_ingre;
    }

    public String getNom_ingre() {
        return nom_ingre;
    }

    public void setNom_ingre(String nom_ingre) {
        this.nom_ingre = nom_ingre;
    }

    public ingredientes(){
        
    }
    @Override
    public boolean insertar() {
        try {
            //INSERT INTO `proyecto`.`ingredientes` (`precio_ingre`, `nom_ingre`, `Cant_ingre`) VALUES ('89', 'tomato', '7');
            String sql="insert into `proyecto`.`ingredientes` (`precio_ingre`, `nom_ingre`, `Cant_ingre`) VALUES ('"+getPrecio_ingre()+"','"+getNom_ingre()+"','"+getCant_ingre()+"');";
            System.out.println(sql);
            Statement query=getCon().createStatement();
            query.executeUpdate(sql);
            System.out.println("verificacion nombre ingredientes");
            System.out.println(getNom_ingre());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ingredientes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList consultar() {
        ArrayList<ingredientes> ingre=new ArrayList<>();
        try {
            String sql="Select * from proyecto.ingredientes;";
            Statement query=getCon().createStatement();
            ResultSet rs=query.executeQuery(sql);
            while(rs.next()){
                int codigo=rs.getInt(1);
                int precio =rs.getInt(2);
                String nombre=rs.getString(3);
                int cantidad = rs.getInt(4);

                //crear un objeto de la clase alumno
                ingredientes a=new ingredientes(codigo, precio, cantidad,nombre);
                ingre.add(a);
            }//fin del while
        } catch (SQLException ex) {
            Logger.getLogger(ingredientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("respuesta del select: ");
        System.out.println(ingre);
        
        return ingre;
    }

       @Override
    public boolean modificar() {
        //UPDATE `proyecto`.`ingredientes` SET `precio_ingre` = '690', `nom_ingre` = 'carambola', `Cant_ingre` = '845' WHERE (`Cod_ingre` = '2');
        try {
            String sql= "UPDATE `proyecto`.`ingredientes` SET `precio_ingre` = '"+getPrecio_ingre()+"', `nom_ingre` = '"+getNom_ingre()+"', `Cant_ingre` = '"+getCant_ingre()+"' WHERE (`Cod_ingre` = '"+getCod_ingre()+"');";
            Statement query=getCon().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ingredientes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public boolean eliminar() {
        try {
            String sql= "DELETE FROM `proyecto`.`ingredientes` WHERE (`Cod_ingre` = '" + getCod_ingre() +"');";
            Statement query=getCon().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ingredientes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
