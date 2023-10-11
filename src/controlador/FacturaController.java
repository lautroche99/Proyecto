/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hugoi
 */
public class FacturaController implements Initializable {

    @FXML
    private TableColumn<?, ?> columcod;
    @FXML
    private TableColumn<?, ?> columdesc;
    @FXML
    private TableColumn<?, ?> columpu;
    @FXML
    private TableColumn<?, ?> columcant;
    @FXML
    private TableColumn<?, ?> columtotal;
    @FXML
    private Button btnnuevo;
    @FXML
    private Button btnguardar;
    @FXML
    private Button btncancelar;
    @FXML
    private Button btnimprimir;
    @FXML
    private TextField afactura;
    @FXML
    private TextField acantidad;
    @FXML
    private TextField acliente;
    @FXML
    private Label etiFecha;
    @FXML
    private ComboBox<?> cmbPagos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void nuevo(ActionEvent event) {
    }

    @FXML
    private void guardar(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void imprimir(ActionEvent event) {
    }

    @FXML
    private void cargarcombo(ActionEvent event) {
    }
    
public void cargarFecha(){
Date objDate = new Date(); // Sistema actual La fecha y la
//hora se asignan a objDate
String strDateFormat = "dd/MMM/yyyy"; // El formato de
//fecha está especificado
SimpleDateFormat objSDF = new
SimpleDateFormat(strDateFormat); // La cadena de formato de fecha
//se pasa como un argumento al objeto
etiFecha.setText(objSDF.format(objDate));
}  
//    
//private void cargarCombo() {
//    cmbPagos.getItems().clear();
//    ArrayList<pagos> lista=p.consulta();
//    for (pagos obj : lista) {
//        cmbPagos.getItems().add(obj.getConcepto());
//}

}

