/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Detalle_pedido;
import modelo.Pedido;
import modelo.Reporte;
import modelo.clientes;

/**
 * FXML Controller class
 *
 * @author hugoi
 */
public class FacturaController implements Initializable {

    private TableColumn<Detalle_pedido, Integer> columcod;
    @FXML
    private Button btnnuevo;
    @FXML
    private Button btnguardar;
    @FXML
    private Button btncancelar;
    @FXML
    private Button btnimprimir;
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
    private TableView<Detalle_pedido> tableDetalleFactura;
    
    //private TableView<Detalle_pedido> tableDetalleFactura;
    Pedido P = new Pedido();
    ArrayList<String> pagosList = new ArrayList<>(Arrays.asList("Efectivo", "Tarjeta"));
    ArrayList<Detalle_pedido> detallePedidoList = new ArrayList();
    ObservableList<Detalle_pedido> registros;
    
    Detalle_pedido detallePedido = new Detalle_pedido();
    @FXML
    private Button btnBuscarPedido;
    @FXML
    private TextField idpedido;
    @FXML
    private TableColumn<Detalle_pedido, Integer> columIdPedido;
    @FXML
    private TableColumn<Detalle_pedido, Integer> columSubTotal;
    @FXML
    private TableColumn<Detalle_pedido, Integer> columCantidad;
    @FXML
    private TableColumn<Detalle_pedido, Integer> columIdPlato;
    @FXML
    private Hyperlink hyper;
    @FXML
    private TextField nroFactura;
    LocalDate fechaActual = LocalDate.now();
    @FXML
    private Label etitotal;
    
    clientes cliente_fac = new clientes();
    //ArrayList<Detalle_pedido> detallePedidoList = new ArrayList();
    //Pedido pedido = new Pedido();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarFecha();
        cargarCombo();
     etiFecha.setText(fechaActual.toString());
    }    

    @FXML
    private void nuevo(ActionEvent event) {
    }

    @FXML
    private void guardar(ActionEvent event) {
//        f.setFecha_fac(fechaActual.toString());
//        f.setIdFactura(id);
//        f.setIDPedido(Integer.parseInt(idpedido.getText()));
//        f.setRuc(Integer.parseInt(acliente.getText()));
//        f.setTotal_fac(Integer.parseInt(etitotal.getText()));
//            if(f.insertar(cliente_fac)){
//                    Alert alerta=new Alert(Alert.AlertType.INFORMATION);
//                    alerta.setHeaderText(null);
//                    alerta.setTitle("El sistema comunica");
//                    alerta.setContentText("Datos insertados correctamente");
//                    alerta.show();
//                }else{
//                  Alert alerta=new Alert(Alert.AlertType.ERROR);
//                    alerta.setHeaderText(null);
//                    alerta.setTitle("El sistema comunica");
//                    alerta.setContentText("Datos no insertados");
//                    alerta.show();  
//                }
//         
//    
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }
    int id;
//    factura f= new factura();
//    Reporte re = new Reporte();
    @FXML
    private void imprimir(ActionEvent event) {
//        f.consultar();
//        id=f.obtener();
//        System.out.println("id>; "+id);
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
            stage.setResizable(false);
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
        if(P.getIDPedidos() != 0 ){
            
            detallePedidoList = detallePedido.consultar(P);
            registros = FXCollections.observableArrayList(detallePedidoList);
            columIdPlato.setCellValueFactory(new PropertyValueFactory<>("IDPlato"));
            columIdPedido.setCellValueFactory(new PropertyValueFactory<>("IDPedido"));
            columSubTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
            columCantidad.setCellValueFactory(new PropertyValueFactory<>("cant_ped"));
            //Agregamos a la tabla el registro que contiene la lista de objetos
            tableDetalleFactura.setItems(registros);
        }
        /*
        int total = 0;
        for (Detalle_pedido item : registros) {
            total+=item.getCant_ped() * item.getSubtotal();
        }
        etiTotal.setText(Integer.toString(total));

        int total = 0;
        for (Detalle_pedido item : registros) {
                    total = total + ( Integer.valueOf(columSubTotal.getCellData(item)) * Integer.valueOf(columCantidad.getCellData(item)) );
        }
         etiTotal.setText(Integer.toString(total));
*/
    }

    public void recibirDatosPedido(Pedido P){
        this.P = P;
        idpedido.setText(String.valueOf(P.getIDPedidos()));
    }
    
    
    @FXML
    private void BuscarPedido(ActionEvent event) {
            try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("/Vista/buscarPedido.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Pedido");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            //instanciamos el controlador de buscarCliente
            buscarPedidoController buscarControlador = fxmlLoader.getController();
            //le enviamos el nombre del controlador a recibirDatos que se encuentra en el segundo formulario 
            buscarControlador.recibirDatos(this);
            cargarTabla();
            stage.show();//mostramos el segundo formulari
        } catch (IOException ex) {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mostrarTabla(MouseEvent event) {
        cargarTabla();
    }

    @FXML
    private void factura(ActionEvent event) {
          //  re.generarReporteParametro("informes/factura.jasper","factura",id);
    }
    public void recibirDatosCliente(clientes c){
        cliente_fac = c;
    }
}

