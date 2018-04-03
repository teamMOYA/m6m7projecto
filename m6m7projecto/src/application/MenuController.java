package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import modeloH.Usuaris;

public class MenuController {
	@FXML private MenuBar menuBar;
	@FXML private BorderPane main;
	@FXML private AnchorPane mainVista;
	@FXML private AnchorPane vistaCentral;
	@FXML private Label lb_nom_menu;

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

	}
	public Usuaris getLogedUser() {
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
	public void menuGetPerfil(){
		this.changeTittle("Ver Perfil");
		ControlErrores.showErrorPantalla();
		//TODO
	}

	public void menuAbout(){
		this.changeTittle("Informacion");
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











}
