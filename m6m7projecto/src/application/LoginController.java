package application;


import java.io.IOException;
import java.sql.SQLException;

import dao.DAOManager;
import dao.DaoUsuaris;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeloH.Usuaris;

public class LoginController {
	DaoUsuaris daouser;
	@FXML private TextField tb_user;

	@FXML private Button bt_login;

	@FXML private PasswordField tb_pass;

	private Usuaris LogedUser;


	@FXML
	public void initialize() {

		System.out.println("logincontroler initialize");

		bt_login.setOnAction((ActionEvent e) -> {
			System.out.println("btlogin");
			try {
				if (checkLogin()){
					//cambiar pantalla
					//FXMLLoader fxmlLoader = new FXMLLoader()

					Stage stage = new Stage();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("Plantilla.fxml"));
					Parent root = loader.load();
					stage.setScene(new Scene(root, 900, 600));

					MenuController menuController = loader.getController();
					menuController.setLogedUser(LogedUser);



					stage.show();

					((Node)(e.getSource())).getScene().getWindow().hide();
				}else{
					ControlErrores.mostrarError("Error Login", "usuario o contraseña incorrecto", "Vuelve a introducir usuario y contraseña");
				}
			} catch (SQLException e1) {
				ControlErrores.mostrarError("Error BD", "Servidor inaccesible", "No hay conexion al servidor");
			}catch (NullPointerException e2) {
				e2.printStackTrace();
				ControlErrores.mostrarError("Error null", "ERROR", e2.getMessage());

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

	//comprueba si el login es correcto
	private boolean checkLogin() throws SQLException, NullPointerException{

		daouser = DAOManager.getDaoUsuaris();

		Usuaris user = daouser.getUsuarisById(tb_user.getText());

		System.out.println(tb_pass.getText());
		if (user != null && user.getPassword().equals(tb_pass.getText())){
			//login correcto
			LogedUser = user;
			return true;
		}else{
			//login incorrecto
			return false;
		}

	}


}
