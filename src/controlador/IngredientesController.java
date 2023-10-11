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
import modelo.ingredientes;
import modelo.plato;

/**
 * FXML Controller class
 *
 * @author hugoi
 */
public class IngredientesController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField Buscar;
    @FXML
    private TableView<ingredientes> table;
    @FXML
    private TableColumn<ingredientes,Integer> columCod;
    @FXML
    private TableColumn<ingredientes,String> columNom;
    @FXML
    private TableColumn<ingredientes,Integer> columprecio;
    @FXML
    private TableColumn<ingredientes,Integer> columCantidad;
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

    
    ObservableList<ingredientes> registros;
    ObservableList<ingredientes> registroFill;
    ingredientes ingre = new ingredientes();
    ArrayList<ingredientes> ingreList = new ArrayList<>();
     boolean modificar=false;
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
            ingreList.clear();
            // recorre objeto por objeto para ver si coincide lo que escribio con un objeto
            registros.stream().filter(registro -> (registro.getNom_ingre().toLowerCase().contains(cadena.toLowerCase()))).forEachOrdered(registro -> {
                registroFill.add(registro); //si enocntro una coincidencia nos arroja el resultado
            });
            table.setItems(registroFill);
        }
    }

    @FXML
    private void MostrarFilas(MouseEvent event) {
        System.out.println("chau");
        ingre=table.getSelectionModel().getSelectedItem();
        txtPrecio.setText(String.valueOf(ingre.getPrecio_ingre()));
        txtCantidad.setText(String.valueOf(ingre.getCant_ingre()));
        txtNombre.setText(String.valueOf(ingre.getNom_ingre()));
        txtCodigo.setText(String.valueOf(ingre.getCod_ingre()));

        btnModificar.setDisable(false);
        btnCancelar.setDisable(false);
        btnEliminar.setDisable(false);
        btnNuevo.setDisable(true);

    }

    @FXML
    private void nuevo(ActionEvent event) {
        btnGuardar.setDisable(false);
        btnCancelar.setDisable(false);
        //habilitar campos
        txtCantidad.setDisable(false);
        txtNombre.setDisable(false);
        txtPrecio.setDisable(false);
        txtNombre.requestFocus();//pasar el foco al campo
    }

      @FXML
    private void modificar(ActionEvent event) {
        btnGuardar.setDisable(false);
        btnCancelar.setDisable(false);
        btnEliminar.setDisable(true);
        //habilitar campos de texto
        txtCodigo.setDisable(true);
        txtCantidad.setDisable(false);
        txtNombre.setDisable(false);
        txtPrecio.setDisable(false);
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
            ingre.setCod_ingre(Integer.parseInt(txtCodigo.getText()));
            if(ingre.eliminar()){
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
            ingre.setCod_ingre(Integer.parseInt(txtCodigo.getText()));
            ingre.setCant_ingre(Integer.parseInt(txtCantidad.getText()));
            ingre.setNom_ingre(txtNombre.getText());
            ingre.setPrecio_ingre(Integer.parseInt(txtPrecio.getText()));
            //verificamos la modificacion, (esto dalta al codigo)
            if(ingre.modificar()){
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
            ingre.setNom_ingre(txtNombre.getText());
            ingre.setCant_ingre(Integer.parseInt(txtCantidad.getText()));
            ingre.setPrecio_ingre(Integer.parseInt(txtPrecio.getText()));  
            System.out.println("titulo de comprobacion de llamada de insertar");
            System.out.println(ingre.getNom_ingre());
            if(ingre.insertar()){
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
    }

      @FXML
    private void cancelar(ActionEvent event) {
        btnGuardar.setDisable(true);
        btnCancelar.setDisable(true);
        btnNuevo.setDisable(false);
        btnEliminar.setDisable(true);
        btnModificar.setDisable(true);
        //deshabilitar campos
        txtPrecio.setDisable(true);
        txtCantidad.setDisable(true);
        txtNombre.setDisable(true);
        txtCodigo.setDisable(true);
        limpiarTexto();
    }

    @FXML
    private void reporte(ActionEvent event) {
    }

    private void cargarDatos() {
        
        System.out.println("holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        ArrayList<ingredientes> ingreList=ingre.consultar();
        System.out.println("Luego del consultar");
        System.out.println(ingreList);
        registros=FXCollections.observableArrayList(ingreList);
        columCod.setCellValueFactory(new PropertyValueFactory<>("Cod_ingre"));
        columNom.setCellValueFactory(new PropertyValueFactory<>("nom_ingre"));
        columprecio.setCellValueFactory(new PropertyValueFactory<>("precio_ingre"));
        columCantidad.setCellValueFactory(new PropertyValueFactory<>("cant_ingre"));
        //Agregamos a la tabla el registro que contiene la lista de objetos
        table.setItems(registros); 
    }
        private void limpiarTexto() {
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtNombre.setText("");
  }
    
}
