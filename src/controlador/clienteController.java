/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import modelo.clientes;
import modelo.ingredientes;

/**
 * FXML Controller class
 *
 * @author hugoi
 */
public class clienteController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtRuc;
   
    @FXML
    private TextField Buscar;
    @FXML
    private TableView<clientes> table;
    @FXML
    private TableColumn<clientes,Integer> columCod;
    @FXML
    private TableColumn<clientes,String> columNom;
    @FXML
    private TableColumn<clientes,Integer> columTel;
    @FXML
    private TableColumn<clientes,Integer> columRuc;
    @FXML
    private TableColumn<clientes,String> columDireccion;

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

    
    ObservableList<clientes> registros;
    ObservableList<clientes> registroFill;

    clientes cli = new clientes();
    ArrayList<clientes> clilist = new ArrayList<>();
     boolean modificar=false;
    @FXML
    private TableColumn<?, ?> columApe;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtTel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarDatos();
    }    

    @FXML
    private void buscarAlum(KeyEvent event) {
        registroFill=FXCollections.observableArrayList();
        String cadena=Buscar.getText();
        if(cadena.isEmpty()){
            table.setItems(registros);
        }else{
            clilist.clear();
            // recorre objeto por objeto para ver si coincide lo que escribio con un objeto
            registros.stream().filter(registro -> (registro.getNom_cl().toLowerCase().contains(cadena.toLowerCase()))).forEachOrdered(registro -> {
                registroFill.add(registro); //si enocntro una coincidencia nos arroja el resultado
            });
            table.setItems(registroFill);
        }
    }

    @FXML
    private void MostrarFilas(MouseEvent event) {
        System.out.println("chau");
        cli=table.getSelectionModel().getSelectedItem();
        txtTel.setText(String.valueOf(cli.getTel_cl()));
        txtRuc.setText(String.valueOf(cli.getRuc_cl()));
        txtNombre.setText(String.valueOf(cli.getNom_cl()));
        txtCodigo.setText(String.valueOf(cli.getIDClientes()));
        txtDireccion.setText(String.valueOf(cli.getDire_cl()));
        txtApellido.setText(String.valueOf(cli.getApellido_cl()));

                
        btnModificar.setDisable(false);
        btnCancelar.setDisable(false);
        btnEliminar.setDisable(false);
        btnNuevo.setDisable(true);

    }

    @FXML
    private void nuevo(ActionEvent event) {
        btnGuardar.setDisable(false);
        btnCancelar.setDisable(false);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        //habilitar campos
        txtRuc.setDisable(false);
        txtNombre.setDisable(false);
        txtTel.setDisable(false);
        txtDireccion.setDisable(false);
        txtApellido.setDisable(false);    

        txtNombre.requestFocus();
//pasar el foco al campo
    }

      @FXML
    private void modificar(ActionEvent event) {
        btnGuardar.setDisable(false);
        btnCancelar.setDisable(false);
        btnEliminar.setDisable(true);
        //habilitar campos de texto
        txtCodigo.setDisable(true);
        txtRuc.setDisable(false);
        txtNombre.setDisable(false);
        txtTel.setDisable(false);
        txtDireccion.setDisable(false);
        txtApellido.setDisable(false);    
        txtNombre.requestFocus();//pasar el foco al campo
        modificar=true;
    }
    
    
    @FXML
    private void eliminar(ActionEvent event) {
        Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Atención");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Desea eliminar el registro selecionado?");
        Optional<ButtonType> opcion=alerta.showAndWait();
        if(opcion.get()==ButtonType.OK){
            cli.setIDClientes(Integer.parseInt(txtCodigo.getText()));
            if(cli.eliminar()){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("El sistema comunica que");
                alert.setHeaderText(null);
                alert.setContentText("Se elimino correctamente el registro ");
                alert.show();
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("El sistema comunica que");
                alert.setHeaderText(null);
                alert.setContentText("No se puedo eliminar correctamente el registro ");
                alert.show();
            }//fin if opcion
            cargarDatos();
            cancelar(event);
        }
    }

    @FXML
    private void guardar(ActionEvent event) {
         if(modificar){
            cli.setIDClientes(Integer.parseInt(txtCodigo.getText()));
            cli.setRuc_cl(Integer.parseInt(txtRuc.getText()));
            cli.setNom_cl(txtNombre.getText());
            cli.setTel_cl(Integer.parseInt(txtTel.getText()));
            cli.setApellido_cl(txtApellido.getText());
            cli.setDire_cl(txtDireccion.getText());
            
            //verificamos la modificacion, (esto dalta al codigo)
            if(cli.modificar()){
                Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setTitle("El sistema comunica");
                alerta.setContentText("Datos modificados correctamente");
                alerta.show();
            }else{
                Alert alerta=new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("El sistema comunica");
                alerta.setContentText("Datos no modificados");
                alerta.show();
            }
        }else{
            cli.setNom_cl(txtNombre.getText());
            cli.setRuc_cl(Integer.parseInt(txtRuc.getText()));
            cli.setTel_cl(Integer.parseInt(txtTel.getText()));  
            cli.setApellido_cl(txtApellido.getText());
            cli.setDire_cl(txtDireccion.getText());
            
            System.out.println("titulo de comprobacion de llamada de insertar");
            System.out.println(cli.getNom_cl());
            if(cli.insertar()){
                    Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                    alerta.setHeaderText(null);
                    alerta.setTitle("El sistema comunica");
                    alerta.setContentText("Datos insertados correctamente");
                    alerta.show();
                }else{
                  Alert alerta=new Alert(Alert.AlertType.ERROR);
                    alerta.setHeaderText(null);
                    alerta.setTitle("El sistema comunica");
                    alerta.setContentText("Datos no insertados");
                    alerta.show();  
                }
         }
        cargarDatos();
                txtTel.setDisable(true);
        txtRuc.setDisable(true);
        txtNombre.setDisable(true);
        txtCodigo.setDisable(true);
        txtDireccion.setDisable(true);
        txtApellido.setDisable(true);
        limpiarTexto();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        btnGuardar.setDisable(true);
        btnCancelar.setDisable(true);
        btnNuevo.setDisable(false);
        btnEliminar.setDisable(true);
        btnModificar.setDisable(true);
        //deshabilitar campos
        txtTel.setDisable(true);
        txtRuc.setDisable(true);
        txtNombre.setDisable(true);
        txtCodigo.setDisable(true);
        txtDireccion.setDisable(true);
        txtApellido.setDisable(true);
        limpiarTexto();
    }

    @FXML
    private void reporte(ActionEvent event) {
    }

    private void cargarDatos() {
        clilist = cli.consultar();
        registros = FXCollections.observableArrayList(clilist);
        columCod.setCellValueFactory(new PropertyValueFactory<>("IDClientes"));
        columNom.setCellValueFactory(new PropertyValueFactory<>("nom_cl"));
        columApe.setCellValueFactory(new PropertyValueFactory<>("apellido_cl"));
        columTel.setCellValueFactory(new PropertyValueFactory<>("tel_cl"));
        columRuc.setCellValueFactory(new PropertyValueFactory<>("ruc_cl"));
        columDireccion.setCellValueFactory(new PropertyValueFactory<>("dire_cl"));
        //Agregamos a la tabla el registro que contiene la lista de objetos
        table.setItems(registros);
    }
        private void limpiarTexto() {
        txtTel.setText("");
        txtRuc.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtApellido.setText("");
        txtCodigo.setText("");
  }
    
}
