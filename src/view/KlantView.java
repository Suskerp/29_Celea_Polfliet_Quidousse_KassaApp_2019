package view;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Artikel;
import model.Verkoop;

import java.util.*;

/**
 * @author Rafael Polfliet & Jef Quidousse & Luca Celea
 * */

public class KlantView implements KassaObserver {
	private Stage stage = new Stage();
	private	TableView<Map.Entry<Artikel,Integer>> table;
	private Label sum;
	private GridPane gridPane = new GridPane();
	private ObservableList<Map.Entry<Artikel, Integer>> items;
	private Map<Artikel, Integer> map;
	private ArrayList<Artikel> artikels;

	public KlantView(){
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);
		map = new LinkedHashMap<>();
		table = new TableView<>();
		sum = new Label();
		artikels = new ArrayList<>();
		items = FXCollections.observableArrayList(new ArrayList<>());
		tableInit();

		gridPane.add(sum,0,1);



		root.getChildren().add(gridPane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
		stage.getIcons().add(new Image("bestanden/icon.png"));
		stage.setOnHiding((event) -> {
			Platform.exit();
		} );
	}

	private double calcSum(){
		Double sum = 0.0;

		for (Artikel artikel:artikels){
			sum += artikel.getVerkoopprijs();
		}
		return sum;
	}



	public void update(Object arg) {
		artikels = (ArrayList<Artikel>) arg;
		map.clear();
		for (Artikel artikel:artikels){
			map.put(artikel, Collections.frequency(artikels,artikel));
		}


		items = FXCollections.observableArrayList(map.entrySet());
		table.getItems().clear();
		table.setItems(items);
		table.refresh();
		sum.setText("Totaal: €"+String.format("%.2f",calcSum()));
	}

	private void tableInit(){

		TableColumn<Map.Entry<Artikel, Integer>, String> column1 = new TableColumn<>("Naam");
		column1.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue().getKey().getNaam()));
		column1.setMinWidth(200);
		
		TableColumn<Map.Entry<Artikel, Integer>, String> column2 = new TableColumn<>("Omschrijving");
		column2.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue().getKey().getOmschrijving()));

		TableColumn<Map.Entry<Artikel, Integer>, String> column3 = new TableColumn<>("Prijs");
		column3.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue().getKey().getVerkoopprijs().toString()));

		TableColumn<Map.Entry<Artikel, Integer>, String> column4 = new TableColumn<>("Aantal");
		column4.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue().getValue().toString()));

		table.getColumns().setAll(column1,column2,column3,column4);

		gridPane.add(table,0,0);
	}

}
