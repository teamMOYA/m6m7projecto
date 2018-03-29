package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControlErrores {


	public static void showConfirmation(String titulo, String header, String body){
		showMesage(AlertType.CONFIRMATION, titulo, header, body);
	}
	public static void showError(String titulo, String header, String body){
		showMesage(AlertType.ERROR, titulo, header, body);
	}
	public static void showInformation(String titulo, String header, String body){
		showMesage(AlertType.INFORMATION, titulo, header, body);
	}
	public static void showNone(String titulo, String header, String body){
		showMesage(AlertType.NONE, titulo, header, body);
	}
	public static void showWarning(String titulo, String header, String body){
		showMesage(AlertType.WARNING, titulo, header, body);
	}

	private static void showMesage(AlertType var, String titulo, String header, String body){
		Alert alert = new Alert(var);
		alert.setTitle(titulo);
		//alert.getDialogPane().setId("paneid");
		alert.setHeaderText(header);
		alert.setContentText(body);
		alert.showAndWait();
	}
}
