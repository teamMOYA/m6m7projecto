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

		//paneArrel.setPrefHeight(paneArrel.getHeight() - 80.0);

		this.mainVista.getChildren().add(vista);

		AnchorPane.setTopAnchor(vista,0.0);
		AnchorPane.setBottomAnchor(vista,0.0);
		AnchorPane.setLeftAnchor(vista, 0.0);
		AnchorPane.setRightAnchor(vista, 0.0);
		//this.paneVista.setVisible(true);
	}

	//funcion para cambiar nombre de cabecera
	private void changeTittle(String titulo){
		lb_nom_menu.setText(titulo);
	}

	//get/set
	public void setLogedUser(Usuaris usuario) {
		MenuController.LogedUser = usuario;
		this.changeTittle("Bienvenido " + MenuController.LogedUser.getIdUsuari());

	}
	public Usuaris getLogedUser() {
		return MenuController.LogedUser;
	}

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
		//TODO
		this.changeTittle("Ver Perfil");
	}

	public void menuAbout(){
		//TODO
		this.changeTittle("Informacion");

	}

	private void cargarMenuInteraction(){

		try {
			LoadView(FXMLLoader.load(getClass().getResource("MenuInteraction.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}











}
