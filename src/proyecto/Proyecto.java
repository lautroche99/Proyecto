/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import modelo.conexion;

/**
 *
 * @author hugoi
 */
public class Proyecto extends Application {
    conexion conect=new conexion();
    @Override
    public void start(Stage stage) throws Exception {
        if(conect.getCon()==null){
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("El sistema comunica");
            alerta.setHeaderText(null);
            alerta.setContentText("No conectado a la BD. Contacte al Admin");
            alerta.show();
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("/Vista/FXMLDocument.fxml"));
        Alert alerta=new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("El sistema comunica");
            alerta.setHeaderText(null);
            alerta.setContentText("Se ha CONECTADO a la base de datos");
            alerta.showAndWait();
            
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sistema comedor-Menu Principal");
        stage.setMaximized(false);
        stage.show();
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
    
    

