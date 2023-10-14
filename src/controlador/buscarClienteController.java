/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.clientes;
/**
 * FXML Controller class
 *
 * @author laura
 */
public class buscarClienteController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableView<clientes> table;
    @FXML
    private TableColumn<clientes, Integer> columCod;
    @FXML
    private TableColumn<clientes, String> columNombre;
    @FXML
    private TableColumn<clientes, String> columApellido;
    @FXML
    private TableColumn<clientes, Integer> columTelefono;
    @FXML
    private TableColumn<clientes, Integer> columRuc;
    @FXML
    private TableColumn<clientes, String> columDireccion;
    @FXML
    private TextField Buscar;

    FacturaController factura;

    ObservableList<clientes> registros;
    clientes cliente = new clientes();
    ArrayList<clientes> clienteList = new ArrayList<>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }    


    @FXML
    private void buscarCliente(KeyEvent event) {
        
    }
    
    //metodo para recibir el controlador del primer formulario
    public void recibirDatos(FacturaController facturacontrolador){
        factura = facturacontrolador;
    }
    private void cargarDatos() {
        clienteList = cliente.consultar();
        registros = FXCollections.observableArrayList(clienteList);
        columCod.setCellValueFactory(new PropertyValueFactory<>("IDClientes"));
        columNombre.setCellValueFactory(new PropertyValueFactory<>("nom_cl"));
        columApellido.setCellValueFactory(new PropertyValueFactory<>("apellido_cl"));
        columTelefono.setCellValueFactory(new PropertyValueFactory<>("tel_cl"));
        columRuc.setCellValueFactory(new PropertyValueFactory<>("ruc_cl"));
        columDireccion.setCellValueFactory(new PropertyValueFactory<>("dire_cl"));
        //Agregamos a la tabla el registro que contiene la lista de objetos
        table.setItems(registros);
    }
    
    @FXML
    private void seleccionar(MouseEvent event) {
        if(table.getSelectionModel().getSelectedItem() != null){
            //evento del mouse para seleccionar el registro y cerrar
            clientes cli= table.getSelectionModel().getSelectedItem();
            //obtenemos el valor de la cedula
            int ruc = cli.getRuc_cl();
            String nombre = cli.getNom_cl();
            String apellido = cli.getApellido_cl();
            //enviamos a la factura por medio del metodo recibirCodigo
            factura.recibirCodigoCliente(ruc);
            factura.recibirNombreCompletoCliente(nombre, apellido);
           // factura.recibirDatosCliente(cli);
            //cerramos la ventana buscar alumno
            Node ventana=(Node) event.getSource();
            Stage stage=(Stage) ventana.getScene().getWindow();
            stage.close();
        }
    }
}
