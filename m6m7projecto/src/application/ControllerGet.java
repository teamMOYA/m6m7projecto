package application;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ControllerGet {


    @FXML
    private AnchorPane mainVista;

    @FXML
    private Button bt_create;

    @FXML
    private Button bt_modify;

    @FXML
    private Button bt_delete;

    @FXML
    private TableView<ObservableList<String>> table;


	/*
	 * 0 = null
	 * 1 = visitas
	 * 2 = clientes
	 * 3 = usuarios
	 * 4 = servicios
	 */
	public static int opcion = 0;

	@FXML
	public void initialize(){
		switch (opcion) {
		case 1:
			visit();
			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;

		default:
			break;
		}
	}
	private void visit(){
		bt_create.setText("Nueva Visita");
		bt_modify.setText("Modificar Visita");
		bt_delete.setText("Borrar Visita");


		for (int i = 0; i < 5; i++) {
            final int finalIdx = i;
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(String.valueOf(i));
            column.setCellValueFactory(param ->
                    new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx))
            );
            table.getColumns().add(column);
        }

	}





}
