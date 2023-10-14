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
public class Pedido extends conexion implements sentencias  {
    private int IDPedidos, IDClientes, met_cobro ;

    public Pedido(int IDPedidos, int IDClientes, int met_cobro) {
        this.IDPedidos = IDPedidos;
        this.IDClientes = IDClientes;
        this.met_cobro = met_cobro;
    }

    public int getIDPedidos() {
        return IDPedidos;
    }

    public void setIDPedidos(int IDPedidos) {
        this.IDPedidos = IDPedidos;
    }

    public int getIDClientes() {
        return IDClientes;
    }

    public void setIDClientes(int IDClientes) {
        this.IDClientes = IDClientes;
    }

    public int getMet_cobro() {
        return met_cobro;
    }

    public void setMet_cobro(int met_cobro) {
        this.met_cobro = met_cobro;
    }
    
    public Pedido(){
        
    }

    @Override
    public boolean insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList consultar() {
        ArrayList<Pedido> P=new ArrayList<>();
        try {
            System.out.println("Se llama a consuktat cliente ");
            String sql="SELECT * FROM proyecto.pedido;";
            Statement query=getCon().createStatement();
            ResultSet rs=query.executeQuery(sql);
            while(rs.next()){
                int IDPedido = rs.getInt(1);
                int IDClientes = rs.getInt(2);
                int met_cobro = rs.getInt(3);

                //crear un objeto de la clase alumno
                Pedido a=new Pedido(IDPedido,IDClientes,met_cobro);
                P.add(a);
            }//fin del while
        } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("respuesta del select: ");
        System.out.println(P);
        
        return P;
    }

    @Override
    public boolean modificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}  
