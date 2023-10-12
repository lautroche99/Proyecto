/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import modelo.Detalle_Plato;
import modelo.Reporte;
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
    ObservableList<ingredientes> registrosIngredientesDisponibles;
    ObservableList<ingredientes> registrosIngredientes;
    plato pla = new plato();
    ingredientes ingre = new ingredientes(); 
    Reporte R = new Reporte();
    ArrayList<plato> plaList = new ArrayList<>();
    ArrayList<ingredientes> ingrelist = new ArrayList<>(); 
    ArrayList<ingredientes> ingreDisponibleList = new ArrayList<>();
    ArrayList<Detalle_Plato> detallePlatoList = new ArrayList<>();
    
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
    private Button btnAgregarIngredientes;
    @FXML
    private TableColumn<ingredientes, Integer> columCodIngre;
    @FXML
    private TableColumn<ingredientes, String> columNombreIngre;
    @FXML
    private TableColumn<ingredientes, Integer> columPrecioIngre;
    @FXML
    private TableColumn<ingredientes, Integer> columCantidadIngre;
    @FXML
    private TableView<ingredientes> tableIngredientesDisponibles;
    @FXML
    private TextField txtCodigoIngre;
    @FXML
    private TextField txtNombreIngre;
    @FXML
    private TextField txtPrecioIngre;
    @FXML
    private TextField txtCantidadIngre;
    private Button btnBorrar;
    @FXML
    private Button btnAgregarIngredientePlato;
    /**?
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ingreDisponibleList = ingre.consultar();
 columCantUtilizadaIngrediente.setEditable(true);
columCantUtilizadaIngrediente.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
columCantUtilizadaIngrediente.setOnEditCommit(event -> {
    ingredientes ingrediente = event.getTableView().getItems().get(event.getTablePosition().getRow());
    ingrediente.setCant_ingre(event.getNewValue());
    // Aquí puedes realizar otras acciones relacionadas con la edición, si es necesario.
});

        //ingrelist=ingre.consultar();
        cargarDatos();
        cargarDatosIngredientesTabla();
        cargarDatosIngredientesDisponibles();
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
        btnAgregarIngredientes.setDisable(false);
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

            if(ingrelist.size() > 0){
                System.out.println("Lista no vacia");
                ingrelist = removeDuplicates(ingrelist);
                for (ingredientes item : registrosIngredientes) {
                    Detalle_Plato dp = new Detalle_Plato();
                    dp.setCod_ingre(columCodigoIngrediente.getCellData(item));
                    dp.setIDPlato(1);
                    if(columCantUtilizadaIngrediente.getCellData(item) != null) 
                        dp.setCant_ingre(columCantUtilizadaIngrediente.getCellData(item));
                    else dp.setCant_ingre(0);
                    detallePlatoList.add(dp);
                }

                ingredientes ingre1 = tableIngredientes.getSelectionModel().getSelectedItem();

                if(pla.insertarConIngredientes(ingrelist, detallePlatoList)){
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
            }else{
                System.out.println("Lista vacia");
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
            //Vaciar todo
            ingrelist.clear();
            ingreDisponibleList = ingre.consultar();
            cargarDatosIngredientesTabla();
            cargarDatosIngredientesDisponibles();
        }
        cargarDatos();
        limpiarTexto();
        txtPrecio.setDisable(true);
        txtCantidad.setDisable(true);
        txtNombre.setDisable(true);
        txtCodigo.setDisable(true);
        btnGuardar.setDisable(true);
        btnCancelar.setDisable(true);
        btnNuevo.setDisable(false);
        btnEliminar.setDisable(true);
        btnModificar.setDisable(true);
    }

      @FXML
    private void cancelar(ActionEvent event) {
        limpiarTexto();
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
    }

    @FXML
    private void reporte(ActionEvent event) {
        R.generarReporte("","reporte");
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
        txtCodigo.setText("");
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
        //ingrelist=ingre.consultar();
        registrosIngredientes=FXCollections.observableArrayList(ingrelist);
        columCodigoIngrediente.setCellValueFactory(new PropertyValueFactory<>("Cod_ingre"));
        columNomIngrediente.setCellValueFactory(new PropertyValueFactory<>("nom_ingre"));
        columPrecioIngredientes.setCellValueFactory(new PropertyValueFactory<>("precio_ingre"));
        columCantIngrediente.setCellValueFactory(new PropertyValueFactory<>("Cant_ingre"));
        columCantUtilizadaIngrediente.setEditable(true);
        //columCantUtilizadaIngrediente.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));  
        //columCantUtilizadaIngrediente.setOnEditCommit(e-> String.valueOf(e) );
//        //Agregamos a la tabla el registro que contiene la lista de objetos
        tableIngredientes.setItems(registrosIngredientes);
        tableIngredientes.setEditable(true);
    }
    
    private void cargarDatosIngredientesDisponibles() {
        
        registrosIngredientesDisponibles = FXCollections.observableArrayList(ingreDisponibleList);
        columCodIngre.setCellValueFactory(new PropertyValueFactory<>("Cod_ingre"));
        columNombreIngre.setCellValueFactory(new PropertyValueFactory<>("nom_ingre"));
        columPrecioIngre.setCellValueFactory(new PropertyValueFactory<>("precio_ingre"));
        columCantidadIngre.setCellValueFactory(new PropertyValueFactory<>("Cant_ingre"));
        //Agregamos a la tabla el registro que contiene la lista de objetos
        tableIngredientesDisponibles.setItems(registrosIngredientesDisponibles);
    }
    
    public void recibirDatosIngredientes(ingredientes ingre){
        ingredientes nuevo = ingre;
        ingrelist.add(nuevo);
        cargarDatosIngredientesTabla();
    }

    private void inAgregarIngredientes(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Vista/buscarIngrediente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("buscarIngrediente");
            stage.initModality(Modality.APPLICATION_MODAL);
            //instanciamos el controlador de buscarCliente
            buscarIngredienteController buscarControlador = fxmlLoader.getController();
            //le enviamos el nombre del controlador a recibirDatos que se encuentra en el segundo formulario 
            buscarControlador.recibirDatos(this);
            stage.show();//mostramos el segundo formulari
        } catch (IOException ex) {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void seleccionarIngredienteDisponible(MouseEvent event) {
        if(tableIngredientesDisponibles.getSelectionModel().getSelectedItem() != null){
           //evento del mouse para seleccionar el registro y cerrar
            ingredientes ingre1 = tableIngredientesDisponibles.getSelectionModel().getSelectedItem();
            agregaringrelist(ingre1);
            quitaringreDisponibleList(ingre1);
            cargarDatosIngredientesTabla();
            cargarDatosIngredientesDisponibles();
        }
    }
    
    public void agregaringrelist(ingredientes ingre1){
        ingrelist.add(ingre1);
    }
    
    public void agregaringreDisponibleList(ingredientes ingre1){
        ingreDisponibleList.add(ingre1);
    }

    public void quitaringreDisponibleList(ingredientes ingre1){
        for (int j =0; j< ingreDisponibleList.size(); j++) {
            if ( ingreDisponibleList.get(j).getCod_ingre() == ingre1.getCod_ingre() ) {
                ingreDisponibleList.remove(j);
            }
        }
    }
    
    public void quitaringreList(ingredientes ingre1){
        for (int j =0; j< ingrelist.size(); j++) {
            if ( ingrelist.get(j).getCod_ingre() == ingre1.getCod_ingre() ) {
                ingrelist.remove(j);
            }
        }
    }
    
    @FXML
    private void seleccionarIngredienteUtilizados(MouseEvent event) {
        btnBorrar.setDisable(false);
        
    }

    @FXML
    private void actionbtnBorrar(ActionEvent event) {
                //evento del mouse para seleccionar el registro y cerrar
        if(tableIngredientes.getSelectionModel().getSelectedItem() != null){
            ingredientes ingre1 = tableIngredientes.getSelectionModel().getSelectedItem();
            agregaringreDisponibleList(ingre1);
            quitaringreList(ingre1);
            cargarDatosIngredientesTabla();
            cargarDatosIngredientesDisponibles();
    }
}

    @FXML
    private void IngredientesdePlato(ActionEvent event) {
    }
    
    public static ArrayList<ingredientes> removeDuplicates(ArrayList<ingredientes> list) 
    { 
  
        // Create a new ArrayList 
        ArrayList<ingredientes> newList = new ArrayList<ingredientes>(); 
  
        // Traverse through the first list 
        for (ingredientes element : list) { 
  
            // If this element is not present in newList 
            // then add it 
            if (!newList.contains(element.getCod_ingre())) { 
  
                newList.add(element); 
            } 
        } 
  
        // return the new list 
        return newList; 
    } 
}
