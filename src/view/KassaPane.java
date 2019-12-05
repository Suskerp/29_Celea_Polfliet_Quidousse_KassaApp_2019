package view;
/**
 * @author Jef Quidousse
 * */

import database.ArtikelDBInMemory;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Artikel;

import java.util.ArrayList;
import java.util.List;

public class KassaPane {
    GridPane gridPane = new GridPane();
    TableView<Artikel> table = new TableView();
    public KassaPane(){



        TextField textField = new TextField();
        textField.setPromptText("Search here!");

        gridPane.add(textField,0,0);





        /*ArtikelDBInMemory artikelDBInMemory = new ArtikelDBInMemory();
        artikelDBInMemory.load();
        List<Artikel> list = new ArrayList<Artikel>(artikelDBInMemory.getGesorteerdeLijst());
        table.setItems(FXCollections.observableList(list));*/

        TableColumn<Artikel, String> colOmschrijving = new TableColumn<>("Omschrijving");
        colOmschrijving.setCellValueFactory(new PropertyValueFactory<>("Omschrijving"));
        colOmschrijving.setMinWidth(50);

        TableColumn<Artikel,Double> colVerkoopprijs = new TableColumn<>("Verkoopprijs");
        colVerkoopprijs.setCellValueFactory(new PropertyValueFactory<>("Verkoopprijs") );
        colVerkoopprijs.setMinWidth(50);

        table.getColumns().addAll(colOmschrijving,colVerkoopprijs);

        gridPane.add(table,0, 1);
    }

    public GridPane getLayout() {
        return gridPane;
    }
}