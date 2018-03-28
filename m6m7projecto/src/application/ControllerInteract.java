package application;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.hibernate.HibernateException;
import dao.DAOManager;
import dao.DaoAsssistencies;
import dao.DaoServeis;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modeloH.Assistencies;
import modeloH.Serveis;

public class ControllerInteract {

    @FXML private AnchorPane mainVista;
    @FXML private Button bt_create;
    @FXML private Button bt_modify;
    @FXML private Button bt_delete;
    @FXML private GridPane grid;

    @FXML private VBox vbox2;

    private boolean modeNew = false;

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
	/**
	 * cargar datos dependiendo de valor opcion
	 */
	public void initialize(){
		if (modeNew){
			createOnClick();
		}
		switch (opcion) {
		case 1:
			getVisits();
			break;
		case 2:
			getClients();
			break;
		case 3:
			getUsers();
			break;
		case 4:
			getServices();
			break;
		default:
			break;
		}

	}


	public void createOnClick(){
		switch (opcion) {
		case 1:
			//TODO
			break;
		case 2:
			//TODO
			break;
		case 3:
			//TODO
			break;
		case 4:
			newService();
			break;
		default:
			break;
		}
	}


	public void deleteOnclick(){
		switch (opcion) {
		case 1:
			delVisits();
			break;
		case 2:
			delClients();
			break;
		case 3:
			delUsers();
			break;
		case 4:
			delServices();
			break;

		default:
			break;
		}
	}




	@SuppressWarnings("unchecked")
	private void getVisits() {
		// TODO Auto-generated method stub
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//botones
		setTextButons("Nueva Visita","Modificar Visita","Borrar Visita");
		//datos
		DaoAsssistencies daoAss = DAOManager.getDaoAsssistencies();
		listaActual = FXCollections.observableList(daoAss.getAssistencies());
		//tabla
		tableActual = new TableView<Serveis>(listaActual);
		tableActual.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		vbox2.getChildren().add(tableActual);
		tableActual.prefHeightProperty().bind(grid.heightProperty());
		tableActual.prefWidthProperty().bind(grid.widthProperty());


		//columnas
		//col1
        TableColumn<Assistencies, Number> column1 = new TableColumn<>("Cod");//titulo
        column1.setCellValueFactory(param ->
        	new SimpleIntegerProperty(param.getValue().getCodiAssistencia())
        );
        tableActual.getColumns().add(column1);
        //col2
        TableColumn<Assistencies, String> column2 = new TableColumn<>("Servicio");//titulo
        column2.setCellValueFactory(param ->
        	new SimpleStringProperty(param.getValue().getServeis().getDescripcio())
        );
        tableActual.getColumns().add(column2);
        //col3
        TableColumn<Assistencies, String> column3 = new TableColumn<>("fecha");//titulo
        column3.setCellValueFactory(param ->
                new SimpleStringProperty(sdf.format(param.getValue().getData()))
        );
        tableActual.getColumns().add(column3);
        //col4
        TableColumn<Assistencies, String> column4 = new TableColumn<>("Cliente");//titulo
        column4.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getClients().getNom() + " : " + param.getValue().getClients().getCognoms())
        );
        tableActual.getColumns().add(column4);
        //col5
        TableColumn<Assistencies, String> column5 = new TableColumn<>("Assistente");//titulo
        column5.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getClients().getCognoms() + " : " + param.getValue().getUsuaris().getCognoms())
        );
        tableActual.getColumns().add(column5);
        //col6
        TableColumn<Assistencies, String> column6 = new TableColumn<>("Observaciones");//titulo
        column6.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getObservacions())
        );
        tableActual.getColumns().add(column6);

	}

	private void getClients() {
		// TODO Auto-generated method stub
		setTextButons("Nuevo Cliente","Modificar Cliente","Borrar Cliente");
	}

	private void getUsers() {
		// TODO Auto-generated method stub
		setTextButons("Nuevo Usuario","Modificar Usuario","Borrar Usuario");
	}


	private void delVisits() {
		// TODO Auto-generated method stub

	}



	private void delClients() {
		// TODO Auto-generated method stub

	}

	private void delUsers() {
		// TODO Auto-generated method stub

	}

	private void newService(){
		Label l_id = new Label("Id");
		Label l_desc = new Label("Descripcion");
		TextField tf_id = new TextField();
		TextField tf_desc = new TextField();
		Button bt_Save = new Button("Guardar");

		GridPane gridadd = new GridPane();
		gridadd.prefWidthProperty().bind(grid.widthProperty());

		gridadd.add(l_id, 0, 0);
		gridadd.add(l_desc, 0, 1);
		gridadd.add(tf_id, 1, 0);
		gridadd.add(tf_desc, 1, 1);
		gridadd.add(bt_Save, 0, 2);


		vbox2.prefWidthProperty().bind(grid.widthProperty());
		vbox2.getChildren().add(gridadd);
	}


	@SuppressWarnings("unchecked")
	private void getServices(){
		//botones
		setTextButons("Nuevo Servicio","Modificar Servicio","Borrar Servicio");

		//datos
		DaoServeis daoServ = DAOManager.getDaoServeis();

		try {
			listaActual = FXCollections.observableList(daoServ.getServeis());
			//tabla
			tableActual = new TableView<Serveis>(listaActual);
			//tableActual.setEditable(true);
			tableActual.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			//scroll.setContent(tableActual);
			//tableActual.prefHeightProperty().bind(scroll.heightProperty());
			tableActual.prefWidthProperty().bind(grid.widthProperty());
			vbox2.getChildren().add(tableActual);

			//columnas
			//col1
			TableColumn<Serveis, Number> column1 = new TableColumn<>("Id");//titulo
			column1.setCellValueFactory(param ->
				new SimpleIntegerProperty(param.getValue().getCodi())
			);
			tableActual.getColumns().add(column1);
			//col2
			TableColumn<Serveis, String> column2 = new TableColumn<>("Desc");//titulo
			column2.setCellValueFactory(param ->
			        new SimpleStringProperty(param.getValue().getDescripcio())
			);
			tableActual.getColumns().add(column2);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println("error getvis");
			e.printStackTrace();
		}
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
			if (ControlErrores.showConfirmation("Borrar Servicio/s", "Esta ", "Seguro que desea borrar" + selectedItems.size() + "servicio/s?")){
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





}
