package view;

import database.ArtikelDBInMemory;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Artikel;

import java.util.*;

/**
 * @author Jef Quidousse & Luca Celea
 * */

public class KlantView implements Observer {
	private Stage stage = new Stage();
	private	TableView<Map.Entry<Artikel,Integer>> table;
	private Label sum;
	private GridPane gridPane = new GridPane();
	private ObservableList<Map.Entry<Artikel, Integer>> items;
	private Map<Artikel, Integer> map;

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



	private String getSum(){
		double sum = 0;

		for (Map.Entry<Artikel,Integer> entry:map.entrySet()){
			sum += entry.getKey().getVerkoopprijs() * entry.getValue();
		}
		return "Total: €"+ String.format("%.2f", sum);
	}

	public void update(Object arg) {
		map = (LinkedHashMap<Artikel,Integer>) arg;;
		items = FXCollections.observableArrayList(map.entrySet());
		table.setItems(items);
		table.refresh();
		sum.setText(getSum());
	}

	private void tableInit(){
		TableColumn<Map.Entry<Artikel, Integer>, String> column1 = new TableColumn<>("Omschrijving");
		column1.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue().getKey().getOmschrijving()));

		TableColumn<Map.Entry<Artikel, Integer>, String> column2 = new TableColumn<>("Prijs");
		column2.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue().getKey().getVerkoopprijs().toString()));

		TableColumn<Map.Entry<Artikel, Integer>, String> column3 = new TableColumn<>("Aantal");
		column3.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue().getValue().toString()));

		table.getColumns().setAll(column1,column2,column3);

		gridPane.add(table,0,0);
	}

}
