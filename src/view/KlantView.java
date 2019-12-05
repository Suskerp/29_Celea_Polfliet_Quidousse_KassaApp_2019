package view;

import database.ArtikelDBInMemory;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Artikel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author Jef Quidousse & Luca Celea
 * */

public class KlantView implements Observer {
	private Stage stage = new Stage();
	private ArrayList<Artikel> artikels = new ArrayList<>();
	private TableView<Artikel> table;
	private Label sum;
	private GridPane gridPane = new GridPane();


	public KlantView(){
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);


		sum = new Label();
		gridPane.add(sum,0,1);

		table = new TableView();
		gridPane.add(table,0,0);

		table.setItems(FXCollections.observableList(artikels));

		TableColumn<Artikel, String> colOmschrijving = new TableColumn<>("Omschrijving");
		colOmschrijving.setCellValueFactory(new PropertyValueFactory<>("Omschrijving"));
		colOmschrijving.setMinWidth(200);

		TableColumn<Artikel,Double> colVerkoopprijs = new TableColumn<>("Verkoopprijs");
		colVerkoopprijs.setCellValueFactory(new PropertyValueFactory<>("Verkoopprijs") );
		colVerkoopprijs.setMinWidth(50);

		TableColumn<Artikel, Integer> colAantal = new TableColumn<>("Aantal");


		table.getColumns().addAll(colOmschrijving,colVerkoopprijs, colAantal);

		root.getChildren().add(gridPane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();		
	}



	private String getSum(){
		double sum = 0;

		for (Artikel artikel:artikels){
			sum += artikel.getVerkoopprijs();
		}
		return "Total: €"+ String.format("%.2f", sum);
	}

	public void update(Object arg) {
		ArrayList<Artikel> a = (ArrayList<Artikel>) arg;
		this.artikels =a;
		table.setItems(FXCollections.observableList(artikels));
		sum.setText(getSum());
	}
}
