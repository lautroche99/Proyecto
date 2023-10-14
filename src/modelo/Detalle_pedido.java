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
public class Detalle_pedido extends conexion implements sentencias {
    private int IDPlato, IDPedido,subtotal,cant_ped ;

    public Detalle_pedido(int IDPlato, int IDPedido, int subtotal, int cant_ped) {
        this.IDPlato = IDPlato;
        this.IDPedido = IDPedido;
        this.subtotal = subtotal;
        this.cant_ped = cant_ped;
    }
    public Detalle_pedido() {

    }

    public int getIDPlato() {
        return IDPlato;
    }

    public void setIDPlato(int IDPlato) {
        this.IDPlato = IDPlato;
    }

    public int getIDPedido() {
        return IDPedido;
    }

    public void setIDPedido(int IDPedido) {
        this.IDPedido = IDPedido;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getCant_ped() {
        return cant_ped;
    }

    public void setCant_ped(int cant_ped) {
        this.cant_ped = cant_ped;
    }
    
    public void Detalle_pedido (){
        
    }

    @Override
    public boolean insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList consultar() {
 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList consultar(Pedido P) {
        ArrayList<Detalle_pedido> pedi=new ArrayList<>();
        try {
            System.out.println("Se llama a consuktat cliente ");
            String sql="SELECT * FROM proyecto.detalle_pedido WHERE IDPedido = '" + P.getIDPedidos() + "';";
            Statement query=getCon().createStatement();
            ResultSet rs=query.executeQuery(sql);
            System.out.println("Se llama a consuktat cliente Despues del execute");
            while(rs.next()){
                int IDPlato = rs.getInt(1);
                int IDPedido = rs.getInt(2);
                int subtotal = rs.getInt(3);
                int cant_ped = rs.getInt(4);
                //crear un objeto de la clase alumno
                Detalle_pedido a=new Detalle_pedido(IDPlato,IDPedido,subtotal,cant_ped);
                pedi.add(a);
            }//fin del while
        } catch (SQLException ex) {
            Logger.getLogger(clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("respuesta del select: ");
        System.out.println(pedi);
        
        return pedi;
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
