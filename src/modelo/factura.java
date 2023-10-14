/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.P;
import modelo.clientes;
import modelo.conexion;
import modelo.sentencias;


/**
 *
 * @author hugoi
 */
public class factura extends conexion implements sentencias  {
    private int idFactura;
    private String fecha_fac;
    private int total_fac;
    private int ruc;
    private int IDPedido;
    Statement query;
    
    public factura() {
    }
    int factId;
    public factura(int idFactura, String fecha_fac, int total_fac, int ruc, int IDPedido) {
        this.idFactura = idFactura;
        this.fecha_fac = fecha_fac;
        this.total_fac = total_fac;
        this.ruc = ruc;
        this.IDPedido = IDPedido;
    }
    public int getRuc() {
        return ruc;
    }
    public void setRuc(int ruc) {
       this.ruc = ruc;
    }
    public int getIdFactura() {
        return idFactura;
    }
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
    public int getTotal_fac() {
        return total_fac;
    }
    public void setTotal_fac(int total_fac) {
        this.total_fac = total_fac;
    }
    public String getFecha_fac() {
        return fecha_fac;
    }
    public void setFecha_fac(String fecha_fac) {
        this.fecha_fac = fecha_fac;
    }
    public int getIDPedido() {
        return IDPedido;
    }
    public void setIDPedido(int IDPedido) {
        this.IDPedido = IDPedido;
    }
    
    
    @Override
    public boolean insertar(){
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    public boolean insertar(clientes cliente_fact){
        try {
        String sql = "insert into `proyecto`.`factura` (`fecha_fac`, `total_fac`, `IDPedido`,`Pedido_IDClientes`,`ruc`) VALUES ( '" + getFecha_fac() +"', '0', '"+getIDPedido()+"', '"+ cliente_fact.getIDClientes()+"', '"+ cliente_fact.getRuc_cl() +"');" ;
            PreparedStatement query = getCon().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
         
            query.executeUpdate();
            ResultSet generatedKey=query.getGeneratedKeys();
            if(generatedKey.next()){
                factId=generatedKey.getInt(1);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(factura.class.getName()).log(Level.SEVERE, null,ex);
            return false;
        }
        
    }

    @Override
    public ArrayList consultar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int obtener(){
        return factId;
    }
    
}