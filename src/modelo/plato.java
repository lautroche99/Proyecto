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
public class plato extends conexion implements sentencias  {
    private int IDPlato,precio_pla,cant_pla;
    private String nom_pla;    
    
    public plato(int IDPlato, int precio_pla, int cant_pla, String nom_pla){
        this.IDPlato =IDPlato;
        this. precio_pla=precio_pla;
        this.cant_pla=cant_pla;
        this.nom_pla=nom_pla;
    }
    public plato(){
        
    }
    
    public int getIDPlato() {
        return IDPlato;
    }

    public void setIDPlato(int IDPlato) {
        this.IDPlato = IDPlato;
    }

    public int getPrecio_pla() {
        return precio_pla;
    }

    public void setPrecio_pla(int precio_pla) {
        this.precio_pla = precio_pla;
    }

    public int getCant_pla() {
        return cant_pla;
    }

    public void setCant_pla(int cant_pla) {
        this.cant_pla = cant_pla;
    }

    public String getNom_pla() {
        return nom_pla;
    }

    public void setNom_pla(String nom_pla) {
        this.nom_pla = nom_pla;
    }
    
    @Override
    public boolean insertar() {
        try {
            //INSERT INTO `proyecto`.`plato` (`precio_pla`, `cant_pla`, `nom_pla`) VALUES ('20000', '3', 'ravioli');
            String sql="insert into `proyecto`.`plato` (`precio_pla`, `cant_pla`, `nom_pla`) VALUES ('"+getPrecio_pla()+"','"+getCant_pla()+"','"+getNom_pla()+"');";
            System.out.println(sql);
            Statement query=getCon().createStatement();
            query.executeUpdate(sql);
            System.out.println("verificacion nombre plato");
            System.out.println(getNom_pla());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(plato.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean insertarConIngredientes(ArrayList<ingredientes> ingreList, ArrayList<Detalle_Plato> detallePlatoList) {
        //INSERT INTO `proyecto`.`plato` (`precio_pla`, `cant_pla`, `nom_pla`) VALUES ('20000', '3', 'ravioli');
        try {
            /*
            String sql="Insert into `proyecto`.`plato` (`precio_pla`, `cant_pla`, `nom_pla`) VALUES ('"+getPrecio_pla()+"','"+getCant_pla()+"','"+getNom_pla()+"'); \n"
            + "SET @PrimaryKeyValue = LAST_INSERT_ID(); \n"
            + "INSERT INTO `proyecto`.`detalle_plato` (`Cod_ingre`, `IDPlato`) VALUES ";

            String contInsertDetallePlato = "";
            for (Detalle_Plato item : detallePlatoList) {
                contInsertDetallePlato = contInsertDetallePlato +"('" + String.valueOf(item.getCod_ingre()) +"', @PrimaryKeyValue), ";
            }

            contInsertDetallePlato = contInsertDetallePlato.substring(0,contInsertDetallePlato.length()-2) + "; \n";

            String contUpdate = "";

            for (Detalle_Plato item : detallePlatoList) {
                contUpdate = contUpdate + "UPDATE `proyecto`.`ingredientes` SET `Cant_ingre` = `Cant_ingre` - " + String.valueOf(item.getCant_ingre()) +" WHERE (`Cod_ingre` = '" + String.valueOf(item.getCod_ingre()) + "'); \n";
            }
            
            sql = sql + contInsertDetallePlato + contUpdate;
            System.out.println(sql);
*/
            Statement query =getCon().createStatement();
            
            // Define your SQL queries as strings
            ArrayList<String> queries = new ArrayList<>();
            queries.add("Insert into `proyecto`.`plato` (`precio_pla`, `cant_pla`, `nom_pla`) VALUES ('"+getPrecio_pla()+"','"+getCant_pla()+"','"+getNom_pla()+"'); ");
            queries.add("SET @PrimaryKeyValue = LAST_INSERT_ID(); ");

            String contInsertDetallePlato = "INSERT INTO `proyecto`.`detalle_plato` (`Cod_ingre`, `IDPlato`) VALUES ";
            for (Detalle_Plato item : detallePlatoList) {
                contInsertDetallePlato = contInsertDetallePlato +"('" + String.valueOf(item.getCod_ingre()) +"', @PrimaryKeyValue), ";
            }
            contInsertDetallePlato = contInsertDetallePlato.substring(0,contInsertDetallePlato.length()-2) + "; \n";
            queries.add(contInsertDetallePlato);

            for (Detalle_Plato item : detallePlatoList) {
                queries.add("UPDATE `proyecto`.`ingredientes` SET `Cant_ingre` = `Cant_ingre` - " + String.valueOf(item.getCant_ingre()) +" WHERE (`Cod_ingre` = '" + String.valueOf(item.getCod_ingre()) + "'); ");
            }
            
            // Add queries to the batch
            for (String q : queries) {
                query.addBatch(q);
            }

            // Execute the batch
            int[] results = query.executeBatch();
            
            
            //query.executeUpdate(sql);
            System.out.println("verificacion nombre plato");
            System.out.println(getNom_pla());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(plato.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList consultar() {
        ArrayList<plato> pla=new ArrayList<>();
        try {
            String sql="Select * from plato";
            Statement query=getCon().createStatement();
            ResultSet rs=query.executeQuery(sql);
            while(rs.next()){
                int codigo=rs.getInt(1);
                int precio =rs.getInt(2);
                int cantidad = rs.getInt(3);
                String nombre=rs.getString(4);

                //crear un objeto de la clase alumno
                plato a=new plato(codigo, precio, cantidad,nombre);
                pla.add(a);
            }//fin del while
        } catch (SQLException ex) {
            Logger.getLogger(plato.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("respuesta del select: ");
        System.out.println(pla);
        
        return pla;
    }

       @Override
    public boolean modificar() {
        //UPDATE `proyecto`.`plato` SET `precio_pla` = '6', `cant_pla` = '5', `nom_pla` = 'g' WHERE (`IDPlato` = '4');
        try {
            String sql= "UPDATE `proyecto`.`plato` SET `precio_pla` = '"+getPrecio_pla()+"', `cant_pla` = '"+getCant_pla()+"', `nom_pla` = '"+getNom_pla()+"' WHERE (`IDPlato` = '"+getIDPlato()+"');";
            Statement query=getCon().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(plato.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public boolean eliminar() {
        try {
            //DELETE FROM `proyecto`.`plato` WHERE (`IDPlato` = '7');
            String sql= "DELETE FROM `proyecto`.`plato` WHERE (`IDPlato` = '" + getIDPlato() +"');";
            Statement query=getCon().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(plato.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
