package view;

import database.ArtikelDBInMemory;
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
	private ArrayList<Artikel> artikels = new ArrayList<>();
	private TableView<MapEntry<Artikel, Integer>> table;
	private Label sum;
	private GridPane gridPane = new GridPane();
	private ObservableMap<Artikel,Integer> map;

	public KlantView(){
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);


		map = FXCollections.observableHashMap();

		table = new TableView<>();

		TableColumn<MapEntry<Artikel, Integer>, String> column1 = new TableColumn<>("Omschrijving");
		column1.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue().getKey().getOmschrijving()));

		TableColumn<MapEntry<Artikel, Integer>, String> column2 = new TableColumn<>("Prijs");
		column2.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue().getKey().getVerkoopprijs().toString()));

		TableColumn<MapEntry<Artikel, Integer>, String> column3 = new TableColumn<>("Aantal");
		column3.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue().getValue().toString()));

		table.getColumns().setAll(column1,column2,column3);

		gridPane.add(table,0,0);
		sum = new Label();
		gridPane.add(sum,0,1);



		root.getChildren().add(gridPane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();		
	}



	private String getSum(){
		double sum = 0;

		for (Map.Entry<Artikel,Integer> entry:map.entrySet()){
			sum += entry.getKey().getVerkoopprijs() * entry.getValue();
		}
		return "Total: €"+ String.format("%.2f", sum);
	}

	public void update(Object arg) {
		LinkedHashMap<Artikel,Integer> art = (LinkedHashMap<Artikel,Integer>) arg;


		ObservableList<MapEntry<Artikel, Integer>> entries = FXCollections.observableArrayList();
		map.clear();
		map.addListener((MapChangeListener.Change<? extends Artikel, ? extends Integer> change) -> {
			boolean removed = change.wasRemoved();
			if (removed != change.wasAdded()) {
				if (removed) {

					entries.remove(new MapEntry<>(change.getKey(), (Integer) null));
				} else {
					entries.add(new MapEntry<>(change.getKey(), change.getValueAdded()));
				}
			} else {
				MapEntry<Artikel, Integer> entry = new MapEntry<>(change.getKey(), change.getValueAdded());
				int index = entries.indexOf(entry);
				entries.set(index, entry);
			}
		});

		table.setItems(entries);

		table.refresh();
		for (Map.Entry<Artikel,Integer> entry:art.entrySet()){
			map.put(entry.getKey(),entry.getValue());
		}


		sum.setText(getSum());
	}
}
