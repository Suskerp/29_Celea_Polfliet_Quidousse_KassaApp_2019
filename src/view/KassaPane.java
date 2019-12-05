package view;
/**
 * @author Jef Quidousse
 * */

import database.*;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import model.Artikel;

import javax.swing.*;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class KassaPane {
    private GridPane gridPane = new GridPane();
    private TableView<Artikel> table = new TableView();
    private TextField textField;
    private Label sum;
    private ArtikelDBStrategy artikelDBStrategy;
    public KassaPane(){

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(100);
        gridPane.getColumnConstraints().addAll(col1);


        artikelDBStrategy = PropertiesLoadWrite.read();

        textField = new TextField();
        textField.setPromptText("Search here!");

        gridPane.add(textField,0,0);


        sum= new Label();
        gridPane.add(sum,0,2);

        textField.setOnAction((entered) ->{
            try {
                if (textField.getText() != null) {
                    artikelDBStrategy.scan(textField.getText());
                    table.setItems(FXCollections.observableList(artikelDBStrategy.getScanItems()));
                    sum.setText(getSum());
                    textField.clear();
                }
            }catch (DatabaseException e){
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });




        TableColumn<Artikel, String> colOmschrijving = new TableColumn<>("Omschrijving");
        colOmschrijving.setCellValueFactory(new PropertyValueFactory<>("omschrijving"));
        colOmschrijving.setMinWidth(200);

        TableColumn<Artikel,Double> colVerkoopprijs = new TableColumn<>("Verkoopprijs");
        colVerkoopprijs.setCellValueFactory(new PropertyValueFactory<>("verkoopprijs") );
        colVerkoopprijs.setMinWidth(50);

        table.getColumns().addAll(colOmschrijving,colVerkoopprijs);

        gridPane.add(table,0, 1);

    }

    public GridPane getLayout() {
        return gridPane;
    }

    private String getSum(){
        double sum = 0;

        for (Artikel artikel:artikelDBStrategy.getScanItems()){
            sum += artikel.getVerkoopprijs();
        }
        return "Total: €"+String.format("%.2f", sum);
    }


}