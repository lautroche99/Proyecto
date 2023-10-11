/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private ComboBox<String> cmbPagos;
    @FXML
    private Button buscarCliente;
    @FXML
    private TextField clienteNombreCompleto;
    @FXML
    private Label etiTotal;
    @FXML
    private TableView<?> tableDetalleFactura;
    //private TableView<Detalle_pedido> tableDetalleFactura;

    ArrayList<String> pagosList = new ArrayList<>(Arrays.asList("Efectivo", "Tarjeta"));
    ArrayList<?> detallePedidoList = new ArrayList();
    //ArrayList<Detalle_pedido> detallePedidoList = new ArrayList();
    //Pedido pedido = new Pedido();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarFecha();
        cargarCombo();
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
     

    private void cargarCombo() {
        cmbPagos.getItems().clear();
        for (String obj : pagosList) {
            cmbPagos.getItems().add(obj);
        }
    }

    @FXML
    private void inBuscarCliente(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("/Vista/buscarCliente.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Factura");
            stage.initModality(Modality.APPLICATION_MODAL);
            //instanciamos el controlador de buscarCliente
            buscarClienteController buscarControlador = fxmlLoader.getController();
            //le enviamos el nombre del controlador a recibirDatos que se encuentra en el segundo formulario 
            buscarControlador.recibirDatos(this);
            stage.show();//mostramos el segundo formulari
        } catch (IOException ex) {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarFecha(){
        Date objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate
        String strDateFormat = "dd/MMM/yyyy"; // El formato de fecha está especificado
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un argumento al objeto
        etiFecha.setText(objSDF.format(objDate));
    }
    
    public void recibirCodigoCliente(int ruc){
        acliente.setText(String.valueOf(ruc));
    }
    
    public void recibirNombreCompletoCliente(String nombre, String apellido){
        clienteNombreCompleto.setText(nombre + " " + apellido);
    }

    public void cargarTabla(){
        //etiTotal.setText(pedido.getTotal());
    }
}

