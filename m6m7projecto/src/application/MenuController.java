package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.EventObject;

import dao.DAOManager;
import dao.DaoUsuaris;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modeloH.Usuaris;

public class MenuController {
	@FXML private MenuBar menuBar;
	@FXML private BorderPane main;
	@FXML private AnchorPane mainVista;
	@FXML private AnchorPane vistaCentral;
	@FXML private Label lb_nom_menu;

	@FXML private Menu menu_User;
	@FXML private MenuItem menu_newServ;
	@FXML private MenuItem menu_newClient;

	private static Usuaris LogedUser = null;

	@FXML
	public void initialize()  {

	}

	//funcion para cambiar zona central pantalla
	public void LoadView(Pane vista) {
		if (vista == null) return;

		this.mainVista.getChildren().clear();
		this.mainVista.getChildren().add(vista);

		AnchorPane.setTopAnchor(vista,0.0);
		AnchorPane.setBottomAnchor(vista,0.0);
		AnchorPane.setLeftAnchor(vista, 0.0);
		AnchorPane.setRightAnchor(vista, 0.0);
	}

	//funcion para cambiar nombre de cabecera
	private void changeTittle(String titulo){
		lb_nom_menu.setText(titulo);
	}

	//get/set
	public void setLogedUser(Usuaris usuario) {
		LogedUser = usuario;
		changeTittle("Bienvenido " + LogedUser.getIdUsuari());
		//TODO restringir opciones dependiendo de perfil usuario
		if (!"ADMINISTRADOR".equals(LogedUser.getPerfils().getDescripcio())){
			menu_User.setVisible(false);
			menu_newServ.setVisible(false);
		}
		if (!"ADMINISTRADOR".equals(LogedUser.getPerfils().getDescripcio()) && !"GESTIÓ".equals(LogedUser.getPerfils().getDescripcio())){
			menu_newClient.setVisible(false);
		}
	}
	public static Usuaris getLogedUser() {
		return MenuController.LogedUser;
	}
	//########################################################################
	//funciones para menu bar
	public void menuNewVisit(){
		this.changeTittle("Visitas");
		ControllerInteract.opcion = 1;
		ControllerInteract.mode = 1;
		cargarMenuInteraction();

	}
	public void menuGetVisits(){
		this.changeTittle("Visitas");
		ControllerInteract.opcion = 1;
		ControllerInteract.mode = 0;
		cargarMenuInteraction();

	}
	public void menuNewClient(){
		this.changeTittle("Clientes");
		ControllerInteract.opcion = 2;
		ControllerInteract.mode = 1;
		cargarMenuInteraction();
	}
	public void menuGetClients(){
		this.changeTittle("Clientes");
		ControllerInteract.opcion = 2;
		ControllerInteract.mode = 0;
		cargarMenuInteraction();
	}
	public void menuNewUser(){
		this.changeTittle("Usuarios");
		ControllerInteract.opcion = 3;
		ControllerInteract.mode = 1;
		cargarMenuInteraction();
	}
	public void menuGetUser(){
		this.changeTittle("Usuarios");
		ControllerInteract.opcion = 3;
		ControllerInteract.mode = 0;
		cargarMenuInteraction();
	}
	public void menuNewService(){
		this.changeTittle("Servicios");
		ControllerInteract.opcion = 4;
		ControllerInteract.mode = 1;
		cargarMenuInteraction();
	}
	public void menuGetServices(){
		this.changeTittle("Servicios");
		ControllerInteract.opcion = 4;
		ControllerInteract.mode = 0;
		cargarMenuInteraction();
	}
	//mostrar informacion de usuario actual
	public void menuGetPerfil(){
		this.changeTittle("Ver Perfil");
		//ControlErrores.showErrorPantalla();

		try {
			DaoUsuaris	daoUser = DAOManager.getDaoUsuaris();

			GridPane gridData = new GridPane();

			//labels
			Label l_id = new Label("Id Usuario");
			Label l_pass = new Label("Contraseña");
			Label l_name = new Label("Nombre");
			Label l_surname = new Label("Apellido");
			Label l_email = new Label("Email");
			Label l_perf = new Label("Perfil");
			Label l_col = new Label("Numero Colegiado");
			Label l_esp = new Label("Especialidad");

			//data
			TextField	tf_id = new TextField(LogedUser.getIdUsuari());
			TextField	tf_pass = new TextField(LogedUser.getPassword());
			TextField	tf_name = new TextField(LogedUser.getNom());
			TextField	tf_surname = new TextField(LogedUser.getCognoms());
			TextField	tf_email = new TextField(LogedUser.getCorreu());
			TextField	tf_perf = new TextField(LogedUser.getPerfils().getCodi() + " : " +LogedUser.getPerfils().getDescripcio() );
			TextField	tf_col = new TextField(String.valueOf(LogedUser.getNumcolegiat()));
			TextField	tf_esp = new TextField(LogedUser.getEspecialitat());

			//campos no editables
			tf_id.setEditable(false);
			tf_name.setEditable(false);
			tf_surname.setEditable(false);
			tf_email.setEditable(false);
			tf_perf.setEditable(false);
			tf_col.setEditable(false);
			tf_esp.setEditable(false);

			//save
			Button bt_Save = new Button("Guardar");

			//grid
			gridData.getChildren().clear();

			gridData.add(l_id, 0, 0);
			gridData.add(l_pass, 0, 1);
			gridData.add(l_name, 0, 2);
			gridData.add(l_surname, 0, 3);
			gridData.add(l_email, 2, 0);
			gridData.add(l_perf, 2, 1);
			gridData.add(l_col, 2, 2);
			gridData.add(l_esp, 2, 3);

			gridData.add(tf_id, 1, 0);
			gridData.add(tf_pass, 1, 1);
			gridData.add(tf_name, 1, 2);
			gridData.add(tf_surname, 1, 3);
			gridData.add(tf_email, 4, 0);
			gridData.add(tf_perf, 4, 1);
			gridData.add(tf_col, 4, 2);
			gridData.add(tf_esp, 4, 3);

			gridData.add(bt_Save, 0, 5);

			this.mainVista.getChildren().clear();
			this.mainVista.getChildren().add(gridData);

			bt_Save.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					try {
						String pass = tf_pass.getText();
						LogedUser.setPassword(pass);
						daoUser.updateUsuaris(LogedUser);
						ControlErrores.showInformation("Cambio de contraseña", "", "Contraseña cambiada");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			});
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// TODO Auto-generated method stub
	}


	public void menuAbout(){
		ControlErrores.showInformation("Informacion", "Informacion creador", "Hecho por DAVID MOYA \n informacion de contacto:\n \t tlf:655929229 \n \t email:email@email.com");
		//TODO
	}

	private void cargarMenuInteraction(){
		try {
			LoadView(FXMLLoader.load(getClass().getResource("MenuInteraction.fxml")));
		} catch (IOException e) {
			ControlErrores.showErrorPantalla();
			e.printStackTrace();
		}
	}

	public void cargarLogin(){
		//cambiar pantalla
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			Parent root;
			root = loader.load();
			stage.setScene(new Scene(root, 900, 600));
			stage.setResizable(false);
			stage.show();
			//esconder pantalla login
			main.getScene().getWindow().hide();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}










}
