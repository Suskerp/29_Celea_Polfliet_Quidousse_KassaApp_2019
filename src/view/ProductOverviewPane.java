package view;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Artikel;
import controller.KassaController;

/**
 * @author Rafael Polfliet - Jef Quidousse
 */

public class ProductOverviewPane extends TableView {

    private TableView<Artikel> table = new TableView<>();
    private KassaController kassa;

    public ProductOverviewPane(KassaController kassaController) {
        this.kassa= kassaController;

        table.setItems(FXCollections.observableList(kassaController.getArtikels()));
        
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
        
        table.getColumns().addAll(colCode,colNaam,colOmschrijving,colVerkoopprijs,colStock);
    }




    public TableView<Artikel> getLayout() {
        return table;
    }
}
