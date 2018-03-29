package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressIndicator;
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

		/*System.out.println("menucontroler initialize");
			// ARREGLAR FALLO CON CONTROLER LOGIN
		try {
			LoadView(FXMLLoader.load(getClass().getResource("MenuBienvenida.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}*/

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
		//TODO
		this.changeTittle("Nueva Visita");

	}
	public void menuGetVisits(){
		//TODO
		this.changeTittle("Ver Visitas");

		ControllerInteract.opcion = 1;
		cargarInteraction();


	}
	public void menuNewClient(){
		//TODO
		this.changeTittle("Nuevo Cliente");
	}
	public void menuGetClients(){
		//TODO
		this.changeTittle("Ver Clientes");

		ControllerInteract.opcion = 2;

	}
	public void menuNewUser(){
		//TODO
		this.changeTittle("Nuevo Usuario");
	}
	public void menuGetUser(){
		//TODO
		this.changeTittle("Ver Usuarios");
		ControllerInteract.opcion = 3;
	}
	public void menuNewService(){
		//TODO
		this.changeTittle("Nuevo Servicio");
	}
	public void menuGetServices(){
		//TODO
		this.changeTittle("Ver Servicios");

		ControllerInteract.opcion = 4;
		cargarInteraction();


	}
	public void menuGetPerfil(){
		//TODO
		this.changeTittle("Ver Perfil");
	}

	public void menuAbout(){
		//TODO
		this.changeTittle("Informacion");
	}

	private void cargarInteraction(){

		try {
			LoadView(FXMLLoader.load(getClass().getResource("MenuInteraction.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}











}
