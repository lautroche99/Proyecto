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
import modelo.Pedido;
import modelo.clientes;

/**
 * FXML Controller class
 *
 * @author hugoi
 */
public class buscarPedidoController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableView<Pedido> table;
    @FXML
    private TableColumn<Pedido,Integer> columIDPedido;
    @FXML
    private TableColumn<Pedido, Integer> columIDClientes;
    @FXML
    private TableColumn<Pedido, Integer> columCobro;
    Pedido P = new Pedido();
     ArrayList<Pedido> pedidolist = new ArrayList<>();
     boolean modificar=false;
     ObservableList<Pedido> registros;
     FacturaController factura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarDatos();
    }    
    public void recibirDatos(FacturaController facturacontrolador){
        factura = facturacontrolador;
    }
    private void cargarDatos() {
        pedidolist = P.consultar();
        registros = FXCollections.observableArrayList(pedidolist);
        columIDPedido.setCellValueFactory(new PropertyValueFactory<>("IDPedido"));
        columIDClientes.setCellValueFactory(new PropertyValueFactory<>("IDClientes"));
        columCobro.setCellValueFactory(new PropertyValueFactory<>("met_cobro"));
        //Agregamos a la tabla el registro que contiene la lista de objetos
        table.setItems(registros);
    }
        @FXML
    private void seleccionar(MouseEvent event) {
        if(table.getSelectionModel().getSelectedItem() != null){
            //evento del mouse para seleccionar el registro y cerrar
            Pedido P= table.getSelectionModel().getSelectedItem();
            //obtenemos el valor de la cedul
            factura.recibirDatosPedido(P);
            //cerramos la ventana buscar alumno
            Node ventana=(Node) event.getSource();
            Stage stage=(Stage) ventana.getScene().getWindow();
            stage.close();
        }
    }
    
}
