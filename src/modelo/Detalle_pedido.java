/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

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

    @Override
    public boolean modificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
