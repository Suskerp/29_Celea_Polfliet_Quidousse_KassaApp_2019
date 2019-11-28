package view;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Artikel;
import model.ArtikelDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author Rafael Polfliet - Jef Quidousse
 */

public class ProductOverviewPane extends TableView {

    private TableView<Artikel> table = new TableView<>();
    private ArtikelDB artikelDB;

    public ProductOverviewPane() {
        artikelDB = new ArtikelDB();
        artikelDB.load("src\\bestanden\\artikel.txt");
        List<Artikel> list = new ArrayList<Artikel>(artikelDB.getArtikelHashMap().values());
        table.setItems(FXCollections.observableList(list));
        
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

    public TableView<Artikel> getTable() {
        return table;
    }
}
