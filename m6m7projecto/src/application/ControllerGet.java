package application;


import java.awt.Checkbox;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.mapping.Column;

import dao.DAOManager;
import dao.DaoAsssistencies;
import dao.DaoServeis;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import modeloH.Assistencies;
import modeloH.Serveis;

public class ControllerGet {


    @FXML
    private AnchorPane mainVista;

    @FXML
    private Button bt_create;

    @FXML
    private Button bt_modify;

    @FXML
    private Button bt_delete;

    @FXML
    private GridPane grid;

    private TableView tableActual;
    private ObservableList listaActual;

	/*
	 * 0 = null
	 * 1 = visitas
	 * 2 = clientes
	 * 3 = usuarios
	 * 4 = servicios
	 */
	public static int opcion = 0;

	@FXML
	public void initialize(){
		switch (opcion) {
		case 1:
			visit();
			break;
		case 2:

			break;
		case 3:

			break;
		case 4:
			getServices();
			break;

		default:
			break;
		}
	}

	public void deleteOnclick(){
		switch (opcion) {
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:
			delServices();
			break;

		default:
			break;
		}
	}


	@SuppressWarnings("unchecked")
	private void visit(){
		bt_create.setText("Nueva Visita");
		bt_modify.setText("Modificar Visita");
		bt_delete.setText("Borrar Visita");
		DaoAsssistencies daoAssist = DAOManager.getDaoAsssistencies();


	}

	@SuppressWarnings("unchecked")
	private void getServices(){
		//botones
		setTextButons("Nuevo Servicio","Modificar Servicio","Borrar Servicio");

		//datos
		DaoServeis daoServ = DAOManager.getDaoServeis();

		ObservableList<Serveis> observableList = FXCollections.observableList(daoServ.getServeis());
		listaActual = observableList;
		//tabla
		TableView<Serveis> table = new TableView<Serveis>(observableList);
		tableActual = table;
		table.setEditable(true);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		grid.add(table, 1, 0);

        //columnas
        /*
        //checkbox funciona pero no se como obtener los seleccionados
        TableColumn<Serveis, Boolean> check = new TableColumn<>();
        check.setCellFactory(column -> new CheckBoxTableCell<>());
        check.setEditable(true);
        table.getColumns().add(check);
        */
        //col1
        TableColumn<Serveis, Number> column1 = new TableColumn<>("Id");//titulo
        column1.setCellValueFactory(param ->
        	new SimpleIntegerProperty(param.getValue().getCodi())
        );
        table.getColumns().add(column1);
        //col2
        TableColumn<Serveis, String> column2 = new TableColumn<>("Desc");//titulo
        column2.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getDescripcio())
        );
        table.getColumns().add(column2);
	}

	private void setTextButons(String textCreate, String textModify, String textDelete) {
		bt_create.setText(textCreate);
		bt_modify.setText(textModify);
		bt_delete.setText(textDelete);
	}

	private void delServices(){

			DaoServeis daoServ = DAOManager.getDaoServeis();
			//averiguar seleccion
			ObservableList<Serveis> selectedItems = tableActual.getSelectionModel().getSelectedItems();
			//mensage
			//ControlErrores.showConfirmation("Borrar Servicio", "Esta ", "Seguro que desea borrar" + selectedItems.size() + "servicios?");

			//recorrer seleccion y borrar
			try {
				for (int i = 0; i < selectedItems.size(); i++) {
					daoServ.delServeis(selectedItems.get(i).getCodi());
				}
				listaActual.removeAll(selectedItems);
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



	}





}
