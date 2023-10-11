package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hugoi
 */
public class MenuController implements Initializable {

    @FXML
    private Button pedidos;
    @FXML
    private Button cerrar;
    @FXML
    private Button plato;
    @FXML
    private Button clientes;
    @FXML
    private Button ingredientes;
    @FXML
    private Button factura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inpedidos(ActionEvent event) {
    }


    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void inplato(ActionEvent event) {
        mostrarVentana("Interfaz","/Vista/plato.fxml");
    }
     public void mostrarVentana(String titulo, String direccion) {
        try{
            Stage stage = new Stage() ;
            Parent menu = FXMLLoader.load(getClass().getResource(direccion));
            Scene scene = new Scene(menu);
            stage.setTitle(titulo);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        }catch (IOException ex){
            Logger.getLogger(FXMLDocument.class.getName()).log(Level.SEVERE,null,ex);
        }    
    }

    @FXML
    private void inclientes(ActionEvent event) {
        mostrarVentana("Interfaz","/Vista/clientes.fxml");
    }

    @FXML
    private void ininventario(ActionEvent event) {
         mostrarVentana("Interfaz","/Vista/ingredientes.fxml");
    }

    @FXML
    private void infactura(ActionEvent event) {
        mostrarVentana("Interfaz","/Vista/Factura.fxml");
    }
    
}
