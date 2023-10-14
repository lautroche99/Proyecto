/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import modelo.Pedido;

/**
 * FXML Controller class
 *
 * @author hugoi
 */
public class PedidoController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField Buscar;
    @FXML
    private TableView<Pedido> table;
    @FXML
    private TableColumn<Pedido, Integer> columCod;
    @FXML
    private TableColumn<Pedido, String> columNom;
    @FXML
    private TableColumn<?, ?> columApe;
    @FXML
    private TableColumn<?, ?> columTel;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Hyperlink generarInforme;
    @FXML
    private TextField txtCliente;
    @FXML
    private TextField txtMetodo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarAlum(KeyEvent event) {
    }

    @FXML
    private void MostrarFilas(MouseEvent event) {
    }

    @FXML
    private void nuevo(ActionEvent event) {
    }

    @FXML
    private void modificar(ActionEvent event) {
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }

    @FXML
    private void guardar(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void reporte(ActionEvent event) {
    }
    
}
