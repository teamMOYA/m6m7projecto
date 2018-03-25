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

	private Usuaris LogedUser;


	@FXML
	public void initialize() {
		System.out.println("menucontroler initialize");



	}

	public void carregarVista(Pane vista) {
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

	public void setLogedUser(Usuaris usuario) {
		this.LogedUser = usuario;
		lb_nom_menu.setText("Bienvenido " + this.LogedUser.getIdUsuari());
	}

}
