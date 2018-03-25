package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControlErrores {

	public static void mostrarError(String titulo, String header, String body){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		//alert.getDialogPane().setId("paneid");
		alert.setHeaderText(header);
		alert.setContentText(body);
		alert.showAndWait();
	}
}
