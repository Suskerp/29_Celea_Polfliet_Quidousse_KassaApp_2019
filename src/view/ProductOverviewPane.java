package view;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Artikel;
import controller.KassaController;

import java.util.ArrayList;

/**
 * @author Rafael Polfliet - Jef Quidousse
 */

public class ProductOverviewPane extends TableView implements KassaObserver {

    private TableView<Artikel> table = new TableView<>();


    public ProductOverviewPane(KassaController kassaController) {
        TableColumn<Artikel,String> colCode = new TableColumn<>("Code");
        colCode.setCellValueFactory(new PropertyValueFactory<>("Code") );
        colCode.setMinWidth(50);
        
        TableColumn<Artikel,String> colNaam = new TableColumn<>("Naam");
        colNaam.setCellValueFactory(new PropertyValueFactory<>("Naam") );
        colNaam.setMinWidth(50);

        TableColumn<Artikel,String> colOmschrijving = new TableColumn<>("Omschrijving");
        colOmschrijving.setCellValueFactory(new PropertyValueFactory<>("Omschrijving") );
        colOmschrijving.setMinWidth(50);

        TableColumn<Artikel,Double> colVerkoopprijs = new TableColumn<>("Verkoopprijs");
        colVerkoopprijs.setCellValueFactory(new PropertyValueFactory<>("Verkoopprijs") );
        colVerkoopprijs.setMinWidth(50);

        TableColumn<Artikel,Integer> colStock = new TableColumn<>("Stock");
        colStock.setCellValueFactory(new PropertyValueFactory<>("Stock") );
        colStock.setMinWidth(50);

        table.setItems(FXCollections.observableList(kassaController.getArtikels()));

        table.getColumns().addAll(colCode,colNaam,colOmschrijving,colVerkoopprijs,colStock);
    }




    public TableView<Artikel> getLayout() {
        return table;
    }

    @Override
    public void update(Object o) {
        ArrayList<Artikel> artikels = (ArrayList<Artikel>) o;
        table.getItems().clear();
        table.setItems(FXCollections.observableList(artikels));
        table.refresh();
    }
}
