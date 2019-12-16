package view;

import database.PropertiesLoadWrite;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import controller.KassaController;

public class KassaView {
	private Stage stage = new Stage();		
		
	public KassaView(KassaController kassaController){
		stage.setTitle("KASSA VIEW");
		stage.setResizable(false);		
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 750, 500);
		BorderPane borderPane = new KassaMainPane(kassaController);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();

		stage.getIcons().add(new Image("bestanden/icon.png"));
		stage.setOnHiding((event) -> {
			PropertiesLoadWrite.getInstance().saveProperties();
			Platform.exit();
		} );

	}

}
