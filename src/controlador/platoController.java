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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import modelo.plato;
import modelo.ingredientes;
/**
 * FXML Controller class
 *
 * @author hugoi
 */
public class platoController implements Initializable {
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
    private TableView<plato> table;
    @FXML
    private TableColumn<plato,Integer> columCod;
    @FXML
    private TableColumn<plato,String> columNom;
    @FXML
    private TableColumn<plato,Integer> columprecio;
    @FXML
    private TableColumn<plato,Integer> columCantidad;
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

    ObservableList<plato> registroFill;
    ObservableList<plato> registros;
    ObservableList<ingredientes> registrosIngredientes ;
    plato pla = new plato();
    ArrayList<plato> plaList = new ArrayList<>();
    ingredientes ingre = new ingredientes(); 
    ArrayList<ingredientes> ingrelist = new ArrayList<>(); 
    boolean modificar=false;
    private Button Pedidos;
    private Button inventplatos;
    @FXML
    private TableColumn<ingredientes, String> columNomIngrediente;
    @FXML
    private TableColumn<ingredientes, Integer> columCantIngrediente;
    @FXML
    private TableColumn<ingredientes, Integer> columCantUtilizadaIngrediente;
    @FXML
    private TableColumn<ingredientes, Integer> columCodigoIngrediente;
    @FXML
    private TableColumn<ingredientes, Integer> columPrecioIngredientes;
    @FXML
    private TableView<ingredientes> tableIngredientes;
    /**?
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarDatos();
        cargarDatosIngredientesTabla();
    }    

    @FXML
    private void buscarplato(KeyEvent event) {
        registroFill=FXCollections.observableArrayList();
        String cadena=Buscar.getText();
        if(cadena.isEmpty()){
            table.setItems(registros);
        }else{
            plaList.clear();
            // recorre objeto por objeto para ver si coincide lo que escribio con un objeto
            registros.stream().filter(registro -> (registro.getNom_pla().toLowerCase().contains(cadena.toLowerCase()))).forEachOrdered(registro -> {
                registroFill.add(registro); //si enocntro una coincidencia nos arroja el resultado
            });
            table.setItems(registroFill);
        }
    }

    @FXML
    private void MostrarFilas(MouseEvent event) {
        System.out.println("chau");
        pla=table.getSelectionModel().getSelectedItem();
        txtPrecio.setText(String.valueOf(pla.getPrecio_pla()));
        txtCantidad.setText(String.valueOf(pla.getCant_pla()));
        txtNombre.setText(String.valueOf(pla.getNom_pla()));
        txtCodigo.setText(String.valueOf(pla.getIDPlato()));

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
            pla.setIDPlato(Integer.parseInt(txtCodigo.getText()));
            if(pla.eliminar()){
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
            pla.setIDPlato(Integer.parseInt(txtCodigo.getText()));
            pla.setCant_pla(Integer.parseInt(txtCantidad.getText()));
            pla.setNom_pla(txtNombre.getText());
            pla.setPrecio_pla(Integer.parseInt(txtPrecio.getText()));
            //verificamos la modificacion, (esto dalta al codigo)
            if(pla.modificar()){
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
            pla.setNom_pla(txtNombre.getText());
            pla.setCant_pla(Integer.parseInt(txtCantidad.getText()));
            pla.setPrecio_pla(Integer.parseInt(txtPrecio.getText()));  
            System.out.println("titulo de comprobacion de llamada de insertar");
            System.out.println(pla.getNom_pla());
            if(pla.insertar()){
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
        plaList=pla.consultar();
        registros=FXCollections.observableArrayList(plaList);
        columCod.setCellValueFactory(new PropertyValueFactory<>("IDPlato"));
        columNom.setCellValueFactory(new PropertyValueFactory<>("nom_pla"));
        columprecio.setCellValueFactory(new PropertyValueFactory<>("precio_pla"));
        columCantidad.setCellValueFactory(new PropertyValueFactory<>("cant_pla"));
        //Agregamos a la tabla el registro que contiene la lista de objetos
        table.setItems(registros);
    }
        private void limpiarTexto() {
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtNombre.setText("");
  }

    private void Ventas(ActionEvent event) {
        Pedidos.setDisable(true);
        inventplatos.setDisable(false);
    }

    private void platosdis(ActionEvent event) {
        inventplatos.setDisable(true);
        Pedidos.setDisable(false);
    }

    private void cargarDatosIngredientesTabla() {
        ingrelist=ingre.consultar();
        registrosIngredientes=FXCollections.observableArrayList(ingrelist);
        columCodigoIngrediente.setCellValueFactory(new PropertyValueFactory<>("Cod_ingre"));
        columNomIngrediente.setCellValueFactory(new PropertyValueFactory<>("nom_ingre"));
        columPrecioIngredientes.setCellValueFactory(new PropertyValueFactory<>("precio_ingre"));
        columCantIngrediente.setCellValueFactory(new PropertyValueFactory<>("Cant_ingre"));
        columCantUtilizadaIngrediente.setEditable(true);
        columCantUtilizadaIngrediente.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));  
        columCantUtilizadaIngrediente.setOnEditCommit(e-> String.valueOf(e) );
//        //Agregamos a la tabla el registro que contiene la lista de objetos
        tableIngredientes.setItems(registrosIngredientes);
        tableIngredientes.setEditable(true);
    }
}
