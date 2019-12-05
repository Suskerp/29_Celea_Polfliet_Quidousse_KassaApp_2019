package view;


import database.ArtikelDBInMemory;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Artikel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafael Polfliet - Jef Quidousse
 */
public class KassaMainPane extends BorderPane {
	public KassaMainPane(){
		
	    TabPane tabPane = new TabPane();
	    KassaPane kassaPane = new KassaPane();
        Tab kassaTab = new Tab("Kassa", kassaPane.getLayout());
        ProductOverviewPane productOverviewPane = new ProductOverviewPane();
        Tab artikelTab = new Tab("Artikelen",productOverviewPane.getLayout());
        InstellingPane instellingPane = new InstellingPane();
        Tab instellingTab = new Tab("Instellingen",instellingPane.getLayout());
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);

        /*GridPane gridPane = new GridPane();


        TextField textField = new TextField();
        textField.setPromptText("Scan here!");

        gridPane.getChildren().add(textField);



        TableView<Artikel> table = new TableView<>();

        ArtikelDBInMemory artikelDBInMemory = new ArtikelDBInMemory();
        artikelDBInMemory.load();
        List<Artikel> list = new ArrayList<Artikel>(artikelDBInMemory.getGesorteerdeLijst());
        table.setItems(FXCollections.observableList(list));

        TableColumn<Artikel, String> colOmschrijving = new TableColumn<>("Omschrijving");
        colOmschrijving.setCellValueFactory(new PropertyValueFactory<>("Omschrijving"));
        colOmschrijving.setMinWidth(50);

        TableColumn<Artikel,Double> colVerkoopprijs = new TableColumn<>("Verkoopprijs");
        colVerkoopprijs.setCellValueFactory(new PropertyValueFactory<>("Verkoopprijs") );
        colVerkoopprijs.setMinWidth(50);

        table.getColumns().addAll(colOmschrijving,colVerkoopprijs);*/



	    this.setCenter(tabPane);
	}
}
