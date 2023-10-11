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
    @FXML
    private TextField Buscar;
    
    ObservableList<ingredientes> registros;
    ingredientes ingre = new ingredientes();
    ArrayList<ingredientes> ingreList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void MostrarFilas(MouseEvent event) {
        ArrayList<ingredientes> ingreList=ingre.consultar();
        System.out.println("Luego del consultar");
        System.out.println(ingreList);
        registros=FXCollections.observableArrayList(ingreList);
        columCodIngre.setCellValueFactory(new PropertyValueFactory<>("Cod_ingre"));
        columNombreIngre.setCellValueFactory(new PropertyValueFactory<>("nom_ingre"));
        columPrecioIngre.setCellValueFactory(new PropertyValueFactory<>("precio_ingre"));
        columCantidadIngre.setCellValueFactory(new PropertyValueFactory<>("cant_ingre"));
        //Agregamos a la tabla el registro que contiene la lista de objetos
        table.setItems(registros); 
    }

    @FXML
    private void buscarIngrediente(KeyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Vista/buscarIngrediente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ingrediente");
            stage.initModality(Modality.APPLICATION_MODAL);
            //instanciamos el controlador de buscarCliente
            buscarClienteController buscarControlador = fxmlLoader.getController();
            //le enviamos el nombre del controlador a recibirDatos que se encuentra en el segundo formulario 
            //buscarControlador.recibirDatos(this);
            stage.show();//mostramos el segundo formulari
        } catch (IOException ex) {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
