package application;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ControlErrores {


	public static boolean showConfirmation(String titulo, String header, String body){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(titulo);
		//alert.getDialogPane().setId("paneid");
		alert.setHeaderText(header);
		alert.setContentText(body);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    // ... user chose OK
			return true;
		} else {
		    // ... user chose CANCEL or closed the dialog
			return false;
		}
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
