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
		//boton login
		bt_login.setOnAction((ActionEvent e) -> {
			try {
				//check login
				if (checkLogin()){

					//cambiar pantalla
					Stage stage = new Stage();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
					Parent root = loader.load();
					stage.setScene(new Scene(root, 900, 600));

					//pasamos el usuario con que ha hecho login
					MenuController menuController = loader.getController();
					menuController.setLogedUser(LogedUser);

					stage.setResizable(false);
					stage.show();
					//esconder pantalla login
					((Node)(e.getSource())).getScene().getWindow().hide();
				}else{
					ControlErrores.showInformation("Error Login", "usuario o contraseña incorrecto", "Vuelve a introducir usuario y contraseña");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				ControlErrores.showInformation("Error BD", "Servidor inaccesible", "No hay conexion al servidor");
			} catch (IOException e1) {
				e1.printStackTrace();
				ControlErrores.showErrorPantalla();
			}
		});
	}

	//comprueba si el login es correcto
	private boolean checkLogin() throws SQLException{
		daouser = DAOManager.getDaoUsuaris();

		Usuaris user = daouser.getUsuarisById(tb_user.getText());
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
