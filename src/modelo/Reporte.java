/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Tercero TT
 */
public class Reporte extends conexion{

    public Reporte() {
    }
    
    public void generarReporte(String ubi, String titulo){
        try {
            HashMap parametro=new HashMap();
            parametro.put("", "");
            URL direccion=getClass().getClassLoader().getResource(ubi);
            JasperReport jr=(JasperReport) JRLoader.loadObject(direccion);
            JasperPrint jp=JasperFillManager.fillReport(jr,parametro,getCon());
            JasperViewer ventana=new JasperViewer(jp,false);
            ventana.setTitle(titulo);
            ventana.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //metodo para generar informes con parametros
    public void generarReporteParametro(String ubi, String titulo, String nombrePar, String valorPar){
        try {
            HashMap parametro=new HashMap();
            parametro.put(nombrePar, valorPar);
            URL direccion=getClass().getClassLoader().getResource(ubi);
            JasperReport jr=(JasperReport) JRLoader.loadObject(direccion);
            JasperPrint jp=JasperFillManager.fillReport(jr,parametro,getCon());
            JasperViewer ventana=new JasperViewer(jp,false);
            ventana.setTitle(titulo);
            ventana.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

