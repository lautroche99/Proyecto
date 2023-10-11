/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.ingredientes;

/**
 * FXML Controller class
 *
 * @author laura
 */
public class buscarIngredienteController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableView<ingredientes> table;
    @FXML
    private TableColumn<ingredientes, String> columCodIngre;
    @FXML
    private TableColumn<ingredientes, String> columNombreIngre;
    @FXML
    private TableColumn<ingredientes, Integer> columPrecioIngre;
    @FXML
    private TableColumn<ingredientes, Integer> columCantidadIngre;
    
    platoController plato;
    
    ObservableList<ingredientes> registros;
    ingredientes ingre = new ingredientes();
    ArrayList<ingredientes> ingreList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }    

    private void cargarDatos() {
        ingreList=ingre.consultar();
        registros = FXCollections.observableArrayList(ingreList);
        columCodIngre.setCellValueFactory(new PropertyValueFactory<>("Cod_ingre"));
        columNombreIngre.setCellValueFactory(new PropertyValueFactory<>("nom_ingre"));
        columPrecioIngre.setCellValueFactory(new PropertyValueFactory<>("precio_ingre"));
        columCantidadIngre.setCellValueFactory(new PropertyValueFactory<>("cant_ingre"));
        //Agregamos a la tabla el registro que contiene la lista de objetos
        table.setItems(registros);
    }


    public void recibirDatos(platoController platocontrolador){
        plato = platocontrolador;
    }
    
    @FXML
    private void seleccionar(MouseEvent event) {
        //evento del mouse para seleccionar el registro y cerrar
        ingredientes ingre= table.getSelectionModel().getSelectedItem();
        //obtenemos el valor de la cedula
        //enviamos a la factura por medio del metodo recibirCodigo
        plato.recibirDatosIngredientes(ingre);
        //cerramos la ventana buscar alumno
        Node ventana=(Node) event.getSource();
        Stage stage=(Stage) ventana.getScene().getWindow();
        stage.close();
    }
    
}
