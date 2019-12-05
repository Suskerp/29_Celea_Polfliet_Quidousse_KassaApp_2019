package view;

import database.ArtikelDBInMemory;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Artikel;

import java.util.ArrayList;
import java.util.List;

public class KlantView {
	private Stage stage = new Stage();		
		
	public KlantView(){			
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);

		TableView<Artikel> table = new TableView();

		TableColumn<Artikel, String> colOmschrijving = new TableColumn<>("Omschrijving");
		colOmschrijving.setCellValueFactory(new PropertyValueFactory<>("Omschrijving"));
		colOmschrijving.setMinWidth(50);

		TableColumn<Artikel,Double> colVerkoopprijs = new TableColumn<>("Verkoopprijs");
		colVerkoopprijs.setCellValueFactory(new PropertyValueFactory<>("Verkoopprijs") );
		colVerkoopprijs.setMinWidth(50);

		TableColumn<Artikel, Integer> colAantal = new TableColumn<>("Aantal");


		table.getColumns().addAll(colOmschrijving,colVerkoopprijs, colAantal);

		root.getChildren().add(table);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();		
	}
}
