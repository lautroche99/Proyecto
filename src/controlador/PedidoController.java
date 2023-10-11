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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.Pedido;

/**
 * FXML Controller class
 *
 * @author hugoi
 */
public class PedidoController implements Initializable {

    @FXML
    private TableView<Pedido> tablePedidos;
    @FXML
    private TableColumn<Pedido, Integer> coludIDPedido;
    @FXML
    private TableColumn<Pedido, Integer> columIDCliente;
    @FXML
    private TableColumn<Pedido, Integer> columMetodoCobro;
    @FXML
    private Button btnClientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

//private void inBuscarCliente(ActionEvent event) {
////        try {
////            FXMLLoader fxmlLoader = new FXMLLoader();
////
////            fxmlLoader.setLocation(getClass().getResource("/Vista/buscarCliente.fxml"));
////
////            Scene scene = new Scene(fxmlLoader.load());
////            Stage stage = new Stage();
////            stage.setScene(scene);
////            stage.setTitle("Factura");
////            stage.initModality(Modality.APPLICATION_MODAL);
////            //instanciamos el controlador de buscarCliente
////            buscarClienteController buscarControlador = fxmlLoader.getController();
////            //le enviamos el nombre del controlador a recibirDatos que se encuentra en el segundo formulario 
////            buscarControlador.recibirDatos(this);
////            stage.show();//mostramos el segundo formulari
////        } catch (IOException ex) {
////            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
////        }
////    }
    
}
