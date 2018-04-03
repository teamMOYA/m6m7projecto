package application;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.HibernateException;
import dao.DAOManager;
import dao.DaoAsssistencies;
import dao.DaoClients;
import dao.DaoServeis;
import dao.DaoUsuaris;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modeloH.Assistencies;
import modeloH.Clients;
import modeloH.Serveis;
import modeloH.Usuaris;

public class ControllerInteract {

	@FXML private AnchorPane mainVista;
	@FXML private Button bt_create;
	@FXML private Button bt_modify;
	@FXML private Button bt_delete;
	@FXML private GridPane grid;
	@FXML private VBox vbox2;
	@FXML private GridPane gridData;

	@SuppressWarnings("rawtypes")
	private TableView tableData;
	@SuppressWarnings("rawtypes")
	private ObservableList listaActual;

	/* opcion
	 * 0 = null
	 * 1 = visitas
	 * 2 = clientes
	 * 3 = usuarios
	 * 4 = servicios
	 */
	public static int opcion = 0;

	/* mode
	 * 0 =nada
	 * 1 =create
	 * 2 =edit
	 */
	public static int mode = 0;

	/**
	 * cargar datos dependiendo de valor opcion
	 */
	@FXML
	public void initialize(){

		//comprobar si a entrado con get o new
		if (mode==1){
			//gridData.setVisible(true);
			values(null);
		}
		switch (opcion) {
		//mostrar tabla con datos
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
	//###############################################################################################
	//botones
	public void createOnClick(){
		mode = 1;
		values(null);
	}

	@SuppressWarnings("rawtypes")
	public void modifyOnClick(){
		mode = 2;
		//obtener datos seleccion
		ObservableList selectedItems = tableData.getSelectionModel().getSelectedItems();
		//comprobar que ha seleccionado solo 1
		if (selectedItems.size()==1){
			values(selectedItems.get(0));
		}else{
			// mensage error
			ControlErrores.showError("ERROR Editar", "Imposible editar", "Para editar solo puede seleccionar 1");
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

	//#######################################################################################
	//get para mostrar contenido en las tablas
	@SuppressWarnings("unchecked")
	private void getVisits() {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//botones
		setTextButtons("Nueva Visita","Modificar Visita","Borrar Visita");
		//datos
		try {
			DaoAsssistencies daoAsist = DAOManager.getDaoAsssistencies();

			listaActual = FXCollections.observableList(daoAsist.getAssistencies());
			//tabla
			tableData = new TableView<Serveis>(listaActual);
			tableData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			tableData.prefWidthProperty().bind(grid.widthProperty());

			//columnas
			//col1
			TableColumn<Assistencies, Number> column1 = new TableColumn<>("Cod");
			column1.setCellValueFactory(param ->
			new SimpleIntegerProperty(param.getValue().getCodiAssistencia())
					);
			tableData.getColumns().add(column1);
			//col2
			TableColumn<Assistencies, String> column2 = new TableColumn<>("Servicio");
			column2.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getServeis().getDescripcio())
					);
			tableData.getColumns().add(column2);
			//col3
			TableColumn<Assistencies, String> column3 = new TableColumn<>("fecha");
			column3.setCellValueFactory(param ->
			new SimpleStringProperty(sdf.format(param.getValue().getData()))
					);
			tableData.getColumns().add(column3);
			//col4
			TableColumn<Assistencies, String> column4 = new TableColumn<>("Cliente");
			column4.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getClients().getNom() + " : " + param.getValue().getClients().getCognoms())
					);
			tableData.getColumns().add(column4);
			//col5
			TableColumn<Assistencies, String> column5 = new TableColumn<>("Assistente");
			column5.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getUsuaris().getNom() + " : " + param.getValue().getUsuaris().getCognoms())
					);
			tableData.getColumns().add(column5);

			vbox2.getChildren().add(tableData);
		} catch (Exception e) {
			System.out.println("cargadatos");
			ControlErrores.showError("Error", "problema de carga", "no se ha podido cargar los datos");
			e.printStackTrace();
		}


	}

	@SuppressWarnings("unchecked")
	private void getClients() {
		setTextButtons("Nuevo Cliente","Modificar Cliente","Borrar Cliente");
		//datos
		try {
			DaoClients daoCli = DAOManager.getDaoClients();


			listaActual = FXCollections.observableList(daoCli.getClients());
			//tabla
			tableData = new TableView<Clients>(listaActual);
			tableData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			tableData.prefWidthProperty().bind(grid.widthProperty());

			//columnas
			//col1
			TableColumn<Clients, Number> column1 = new TableColumn<>("Cod");//titulo
			column1.setCellValueFactory(param ->
			new SimpleIntegerProperty(param.getValue().getIdClient())
					);
			tableData.getColumns().add(column1);
			//col2
			TableColumn<Clients, String> column2 = new TableColumn<>("Nombre");//titulo
			column2.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getNom())
					);
			tableData.getColumns().add(column2);
			//col3
			TableColumn<Clients, String> column3 = new TableColumn<>("Apellidos");//titulo
			column3.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getCognoms())
					);
			tableData.getColumns().add(column3);
			//col4
			TableColumn<Clients, String> column4 = new TableColumn<>("Telefono");//titulo
			column4.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getTelefon())
					);
			tableData.getColumns().add(column4);
			//col5
			TableColumn<Clients, String> column5 = new TableColumn<>("Email");//titulo
			column5.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getCorreu())
					);
			tableData.getColumns().add(column5);

			vbox2.getChildren().add(tableData);
		} catch (Exception e) {
			ControlErrores.showError("Error", "problema de carga", "no se ha podido cargar los datos");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void getUsers() {
		setTextButtons("Nuevo Cliente","Modificar Cliente","Borrar Cliente");
		//datos
		try {
			DaoUsuaris daoUser = DAOManager.getDaoUsuaris();


			listaActual = FXCollections.observableList(daoUser.getUsuaris());
			//tabla
			tableData = new TableView<Usuaris>(listaActual);
			tableData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			tableData.prefWidthProperty().bind(grid.widthProperty());

			//columnas
			//col1
			TableColumn<Usuaris, String> column1 = new TableColumn<>("Id");//titulo
			column1.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getIdUsuari())
					);
			tableData.getColumns().add(column1);
			//col2
			TableColumn<Usuaris, String> column2 = new TableColumn<>("Password");//titulo
			column2.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getPassword())
					);
			tableData.getColumns().add(column2);
			//col3
			TableColumn<Usuaris, String> column3 = new TableColumn<>("Nombre");//titulo
			column3.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getNom())
					);
			tableData.getColumns().add(column3);
			//col4
			TableColumn<Usuaris, String> column4 = new TableColumn<>("Apellidos");//titulo
			column4.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getCognoms())
					);
			tableData.getColumns().add(column4);
			//col5
			TableColumn<Usuaris, String> column5 = new TableColumn<>("Email");//titulo
			column5.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getCorreu())
					);
			tableData.getColumns().add(column5);
			//col6
			TableColumn<Usuaris, String> column6 = new TableColumn<>("Perfil");//titulo
			column6.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getPerfils().getCodi() + " : " + param.getValue().getPerfils().getDescripcio())
					);
			tableData.getColumns().add(column6);
			//col7
			TableColumn<Usuaris, Number> column7 = new TableColumn<>("Num Colegiado");//titulo
			column7.setCellValueFactory(param ->
			new SimpleIntegerProperty(param.getValue().getNumcolegiat())
					);
			tableData.getColumns().add(column7);
			//col8
			TableColumn<Usuaris, String> column8 = new TableColumn<>("Especialidad");//titulo
			column8.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getEspecialitat())
					);
			tableData.getColumns().add(column8);

			vbox2.getChildren().add(tableData);
		} catch (Exception e) {
			ControlErrores.showError("Error", "problema de carga", "no se ha podido cargar los datos");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void getServices(){
		//botones
		setTextButtons("Nuevo Servicio","Modificar Servicio","Borrar Servicio");

		//datos
		try {
			DaoServeis daoServ = DAOManager.getDaoServeis();


			listaActual = FXCollections.observableList(daoServ.getServeis());
			//tabla
			tableData = new TableView<Serveis>(listaActual);
			//tableData.setEditable(true);
			tableData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			//scroll.setContent(tableData);
			//tableData.prefHeightProperty().bind(scroll.heightProperty());
			tableData.prefWidthProperty().bind(grid.widthProperty());

			//columnas
			//col1
			TableColumn<Serveis, Number> column1 = new TableColumn<>("Id");//titulo
			column1.setCellValueFactory(param ->
			new SimpleIntegerProperty(param.getValue().getCodi())
					);
			tableData.getColumns().add(column1);
			//col2
			TableColumn<Serveis, String> column2 = new TableColumn<>("Desc");//titulo
			column2.setCellValueFactory(param ->
			new SimpleStringProperty(param.getValue().getDescripcio())
					);
			tableData.getColumns().add(column2);

			vbox2.getChildren().add(tableData);
		} catch (HibernateException e) {
			ControlErrores.showError("Error", "problema de carga", "no se ha podido cargar los datos");
			e.printStackTrace();
		}
	}

	//#######################################################################################
	//deletes

	@SuppressWarnings("unchecked")
	private void delVisits() {
		DaoAsssistencies daoAsist = DAOManager.getDaoAsssistencies();
		//averiguar seleccion
		ObservableList<Assistencies> selectedItems = tableData.getSelectionModel().getSelectedItems();
		//mensage
		if (ControlErrores.showConfirmation("Borrar Assistencia/s", "Confirme Borrado ", "Seguro que desea borrar " + selectedItems.size() + " Assistencia/s?")){
			//recorrer seleccion y borrar
			try {
				for (int i = 0; i < selectedItems.size(); i++) {
					daoAsist.delAssistencia(selectedItems.get(i).getCodiAssistencia());
				}
				listaActual.removeAll(selectedItems);
				ControlErrores.showInformation("Borrar Assistencia/s", "Borrado finalizado ",  + selectedItems.size() + " Assistencia/s borrado/s");
			} catch (HibernateException e) {
				ControlErrores.showError("Borrar Assistencia/s", "Problema Borrado", "Ha Habido un problema al borrar una Assistencia");
				e.printStackTrace();
			}
		}
	}



	@SuppressWarnings("unchecked")
	private void delClients() {
		DaoClients daoCli = DAOManager.getDaoClients();
		//averiguar seleccion
		ObservableList<Clients> selectedItems = tableData.getSelectionModel().getSelectedItems();
		//mensage
		if (ControlErrores.showConfirmation("Borrar Cliente/s", "Confirme Borrado ", "Seguro que desea borrar " + selectedItems.size() + " Cliente/s?")){
			//recorrer seleccion y borrar
			try {
				for (int i = 0; i < selectedItems.size(); i++) {
					daoCli.delClients(selectedItems.get(i).getIdClient());
				}
				listaActual.removeAll(selectedItems);
				ControlErrores.showInformation("Borrar Cliente/s", "Borrado finalizado ",  + selectedItems.size() + " Cliente/s borrado/s");
			} catch (HibernateException e) {
				ControlErrores.showError("Borrar Cliente/s", "Problema Borrado", "Ha Habido un problema al borrar una Cliente");
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void delUsers() {
		try {
			DaoUsuaris daoUser = DAOManager.getDaoUsuaris();
			//averiguar seleccion
			ObservableList<Usuaris> selectedItems = tableData.getSelectionModel().getSelectedItems();
			//mensage
			if (ControlErrores.showConfirmation("Borrar Usuario/s", "Confirme Borrado ", "Seguro que desea borrar " + selectedItems.size() + " Usuario/s?")){
				//recorrer seleccion y borrar

				for (int i = 0; i < selectedItems.size(); i++) {
					daoUser.delUsuaris(selectedItems.get(i));
				}
				listaActual.removeAll(selectedItems);
				ControlErrores.showInformation("Borrar Usuario/s", "Borrado finalizado ",  + selectedItems.size() + " Usuario/s borrado/s");
			}
		} catch (HibernateException | SQLException e) {
			ControlErrores.showError("Borrar Usuario/s", "Problema Borrado", "Ha Habido un problema al borrar una Usuario");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void delServices(){
		DaoServeis daoServ = DAOManager.getDaoServeis();
		//averiguar seleccion
		ObservableList<Serveis> selectedItems = tableData.getSelectionModel().getSelectedItems();
		//mensage
		if (ControlErrores.showConfirmation("Borrar Servicio/s", "Confirme Borrado ", "Seguro que desea borrar" + selectedItems.size() + "servicio/s?")){
			//recorrer seleccion y borrar
			try {
				for (int i = 0; i < selectedItems.size(); i++) {
					daoServ.delServeis(selectedItems.get(i).getCodi());
				}
				listaActual.removeAll(selectedItems);
				ControlErrores.showInformation("Borrar Servicio/s", "Borrado finalizado ",  + selectedItems.size() + " Servicio/s borrado/s");
			} catch (HibernateException e) {
				ControlErrores.showError("Borrar Servicio/s", "Problema Borrado", "Ha Habido un problema al borrar un servicio");
				e.printStackTrace();
			}
		}
	}
	//#######################################################################################
	//modificaciones

	public void values(Object obj){
		try {
			switch (opcion) {
			case 1:
				if (mode==1){
					valuesVisits(null);
				}else{
					valuesVisits((Assistencies)obj);
				}
				break;
			case 2:
				if (mode==1){
					valueClients(null);
				}else{
					valueClients((Clients)obj);
				}
				break;
			case 3:
				if (mode==1){
					valueUsers(null);
				}else{
					valueUsers((Usuaris)obj);
				}
				break;
			case 4:
				if (mode==1){
					valuesService(null);
				}else{
					valuesService((Serveis)obj);
				}
				break;
			default:
				break;
			}
		} catch (Exception e) {
			System.out.println("error parse");
			e.printStackTrace();
		}
	}

	//area donde crear modificar datos

	private void valuesVisits(Assistencies obj) {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			DaoAsssistencies daoAsist = DAOManager.getDaoAsssistencies();
			DaoClients daoCli = DAOManager.getDaoClients();
			DaoServeis daoServ = DAOManager.getDaoServeis();
			DaoUsuaris daoUser = DAOManager.getDaoUsuaris();
			//labels
			Label l_type = new Label("Tipo Servicio");
			Label l_data = new Label("Fecha");
			Label l_cli = new Label("Cliente");
			Label l_asi = new Label("Assistente");
			Label l_obs = new Label("Observaciones");

			//data
			List<Serveis> listServObj = daoServ.getServeis();
			List<Clients> listClientObj = daoCli.getClients();
			List<Usuaris> listUserObj = daoUser.getUsuaris();

			List<String> listServValues = new LinkedList<>();
			List<String> listClientValues = new LinkedList<>();
			List<String> listUserValues = new LinkedList<>();

			for (Serveis serveis : listServObj) {
				listServValues.add(serveis.getCodi() + " - " + serveis.getDescripcio());
			}
			for (Clients clients : listClientObj) {
				listClientValues.add(clients.getNom() + " - " + clients.getCognoms());
			}
			for (Usuaris usuaris : listUserObj) {
				listUserValues.add(usuaris.getNom() + " - " + usuaris.getCognoms());
			}

			ChoiceBox<String> tf_type = new ChoiceBox<>(FXCollections.observableList(listServValues));
			DatePicker tf_data = new DatePicker();
			ChoiceBox<String> tf_cli = new ChoiceBox<>(FXCollections.observableList(listClientValues));
			ChoiceBox<String> tf_asi = new ChoiceBox<>(FXCollections.observableList(listUserValues));
			TextArea tf_obs;
			//System.out.println(gridData.getChildren().size());
			if (mode == 1){
				//tf_type
				tf_data.setValue(LocalDate.now());
				//tf_cli
				//tf_asi
				tf_obs = new TextArea();
			}else{
				// FECHA

				LocalDate date = Instant.ofEpochMilli(obj.getData().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				tf_data.setValue(date);
				try {

					int	i=0;
					while (obj.getServeis().getCodi()!=listServObj.get(i).getCodi()){
						i++;
					}
					tf_type.getSelectionModel().select(i);
					i=0;
					while (obj.getClients().getIdClient()!=listClientObj.get(i).getIdClient()){
						i++;
					}
					tf_cli.getSelectionModel().select(i);
					i=0;

					while (!obj.getUsuaris().getIdUsuari().equals(listUserObj.get(i).getIdUsuari())){
						i++;
					}
					tf_asi.getSelectionModel().select(i);
				} catch (Exception e) {
					e.printStackTrace();
				}

				tf_obs = new TextArea(obj.getObservacions());
			}
			//bt save
			Button bt_Save = new Button("Guardar");
			//grid
			gridData.getChildren().clear();

			gridData.add(l_type, 0, 0);
			gridData.add(l_data, 0, 1);
			gridData.add(l_cli, 0, 2);
			gridData.add(l_asi, 2, 0);
			gridData.add(l_obs, 2, 1);

			gridData.add(tf_type, 1, 0);
			gridData.add(tf_data, 1, 1);
			gridData.add(tf_cli, 1, 2);
			gridData.add(tf_asi, 3, 0);
			gridData.setRowSpan(tf_obs, 3);
			gridData.add(tf_obs, 3, 1);
			gridData.add(bt_Save, 0, 3);

			ColumnConstraints col1 = new ColumnConstraints();
			col1.setPercentWidth(10);
			ColumnConstraints col2 = new ColumnConstraints();
			col2.setPercentWidth(20);
			ColumnConstraints col3 = new ColumnConstraints();
			col3.setPercentWidth(15);
			ColumnConstraints col4 = new ColumnConstraints();
			col4.setPercentWidth(55);
			System.out.println("grid" + gridData.getChildren().size());
			System.out.println("griddata size " + gridData.getColumnConstraints().size());
			if (gridData.getColumnConstraints().size()<4){
				gridData.getColumnConstraints().addAll(col1,col2,col3,col4);
			}


			// guardat datos
			bt_Save.setOnAction(new EventHandler<ActionEvent>() {
				@SuppressWarnings("unchecked")
				@Override public void handle(ActionEvent e) {

					Serveis serveis = listServObj.get(tf_type.getSelectionModel().getSelectedIndex());
					Usuaris usuaris = listUserObj.get(tf_asi.getSelectionModel().getSelectedIndex());
					Clients clients = listClientObj.get(tf_cli.getSelectionModel().getSelectedIndex());

					LocalDate localDate = tf_data.getValue();
					Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
					Date data = Date.from(instant);
					String observacions = tf_obs.getText();

					if (mode==1){
						Assistencies newAsist = new Assistencies(serveis, usuaris, clients, data, observacions);
						daoAsist.addAssistencia(newAsist);
						listaActual.add(newAsist);
					}else{
						obj.setServeis(serveis);
						obj.setUsuaris(usuaris);
						obj.setClients(clients);
						obj.setData(data);
						obj.setObservacions(observacions);

						daoAsist.updateAssistencia(obj);
					}
				}
			});





		} catch (HibernateException e) {
			ControlErrores.showError("Añadir Servicio", "Problema con identificador", "Codigo repetido, introduzca otro codigo");
			e.printStackTrace();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("all")
	private void valueClients(Clients obj) {

		DaoClients daoCli = DAOManager.getDaoClients();

		//labels
		Label l_name = new Label("Nombre");
		Label l_surname = new Label("Apellidos");
		Label l_phone = new Label("Telefono");
		Label l_email = new Label("Email");
		//data
		TextField tf_name;
		TextField tf_surname;
		TextField tf_phone;
		TextField tf_email;
		if (mode == 1){
			tf_name = new TextField();
			tf_surname = new TextField();
			tf_phone = new TextField();
			tf_email = new TextField();
		}else{
			tf_name = new TextField(obj.getNom());
			tf_surname = new TextField(obj.getCognoms());
			tf_phone = new TextField(obj.getTelefon());
			tf_email = new TextField(obj.getCorreu());
		}
		//save
		Button bt_Save = new Button("Guardar");
		//grid
		gridData.getChildren().clear();

		gridData.add(l_name, 0, 0);
		gridData.add(l_surname, 0, 1);
		gridData.add(l_phone, 2, 0);
		gridData.add(l_email, 2, 1);

		gridData.add(tf_name, 1, 0);
		gridData.add(tf_surname, 1, 1);
		gridData.add(tf_phone, 3, 0);
		gridData.add(tf_email, 3, 1);

		gridData.add(bt_Save, 0, 2);

		//comprobar datos
		try {
			// guardat datos
			bt_Save.setOnAction(new EventHandler<ActionEvent>() {
				@SuppressWarnings("unchecked")
				@Override public void handle(ActionEvent e) {
					String name = tf_name.getText();
					String surname = tf_surname.getText();
					String phone = tf_phone.getText();
					String email = tf_email.getText();

					if (mode==1){
						Clients newCli = new Clients(name, surname, phone, email);

						daoCli.addClients(newCli);
						listaActual.add(newCli);
					}else{
						obj.setNom(name);
						obj.setCognoms(surname);
						obj.setTelefon(phone);
						obj.setCorreu(email);
						daoCli.updateClients(obj);
					}
				}
			});

		}catch (HibernateException e) {

			// TODO: handle exception
			ControlErrores.showError("Añadir Servicio", "Problema con identificador", "Codigo repetido, introduzca otro codigo");
			e.printStackTrace();
		}

	}

	private void valueUsers(Usuaris obj) {
		// TODO Auto-generated method stub


	}

	@SuppressWarnings("all")
	private void valuesService(Serveis serv){
		DaoServeis daoServ = DAOManager.getDaoServeis();

		//labels
		Label l_id = new Label("Id");
		Label l_desc = new Label("Descripcion");

		//data
		TextField tf_id;
		TextField tf_desc;
		if (mode == 1){
			tf_id = new TextField();
			tf_desc = new TextField();
		}else{
			//datos en textbox
			tf_id = new TextField(String.valueOf(serv.getCodi()));
			tf_id.setEditable(false);
			tf_desc = new TextField(serv.getDescripcio());
		}
		//save
		Button bt_Save = new Button("Guardar");

		//grid
		gridData.getChildren().clear();

		gridData.add(l_id, 0, 0);
		gridData.add(l_desc, 0, 1);
		gridData.add(tf_id, 1, 0);
		gridData.add(tf_desc, 1, 1);
		gridData.add(bt_Save, 0, 2);

		vbox2.prefWidthProperty().bind(grid.widthProperty());
		// comprobar datos
		try {
			// guardat datos
			bt_Save.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					int cod = Integer.parseInt(tf_id.getText());
					String descripcio = tf_desc.getText();
					if (mode==1){
						Serveis newserv = new Serveis(cod, descripcio);
						daoServ.addServeis(newserv);
						listaActual.add(newserv);
					}else{
						serv.setDescripcio(descripcio);
						daoServ.updateServeis(serv);
					}
				}
			});

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (HibernateException e) {
			// TODO: handle exception
			ControlErrores.showError("Añadir Servicio", "Problema con identificador", "Codigo repetido, introduzca otro codigo");
			e.printStackTrace();
		}
	}




	//
	private void setTextButtons(String textCreate, String textModify, String textDelete) {
		bt_create.setText(textCreate);
		bt_modify.setText(textModify);
		bt_delete.setText(textDelete);
	}





}
