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
public class Detalle_Plato extends conexion implements sentencias {
    private int Cod_ingre, IDPlato ;

    public Detalle_Plato(int Cod_ingre, int IDPlato) {
        this.Cod_ingre = Cod_ingre;
        this.IDPlato = IDPlato;
    }

    public int getCod_ingre() {
        return Cod_ingre;
    }

    public void setCod_ingre(int Cod_ingre) {
        this.Cod_ingre = Cod_ingre;
    }

    public int getIDPlato() {
        return IDPlato;
    }

    public void setIDPlato(int IDPlato) {
        this.IDPlato = IDPlato;
    }

    public void Detalle_plato (){
        
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
