/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author hugoi
 */
public class FXMLDocument implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private PasswordField contra;
    @FXML
    private Button boton;
    
  
  String contraseña = "a";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ingresar(ActionEvent event) {
          Object evt = event.getSource();
          Node ventana = (Node) event.getSource();
          Stage stage = (Stage) ventana.getScene().getWindow();
          
         if( contra.getText().equals(contraseña)){
           mostrarVentana("Interfaz","/Vista/menu.fxml");
           stage.close();
         }
         else{
            Alert error=new Alert(Alert.AlertType.ERROR);
            error.setTitle("El sistema comunica");
            error.setHeaderText(null);
            error.setContentText("Contraseña incorrecta");
            error.show();
            contra.setText("");
         }
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

}
