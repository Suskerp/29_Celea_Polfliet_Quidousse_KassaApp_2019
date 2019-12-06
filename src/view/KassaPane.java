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
import controller.KassaController;

import javax.swing.*;


/**
 * @author Jef Quidousse
 */

public class KassaPane{

    private GridPane gridPane = new GridPane();
    private TableView<Artikel> table = new TableView();
    private TextField textField;
    private Label sum;
    private TextField remove;
    private KassaController kassaController;


    public KassaPane(KassaController kassaController){

        this.kassaController = kassaController;

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(col1,col2);




        textField = new TextField();
        textField.setPromptText("Search here!");

        gridPane.add(textField,0,0);


        sum= new Label();
        gridPane.add(sum,0,2);

        textField.setOnAction((entered) ->{
            try {
                if (textField.getText() != null) {
                    kassaController.scan(textField.getText());
                    table.setItems(FXCollections.observableList(kassaController.getScannedItems()));
                    sum.setText(getSum());
                    textField.clear();
                    kassaController.notifyObservers();
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

        gridPane.add(table,0, 1,2,1);

        remove = new TextField();
        remove.setPromptText("Remove a single product");
        gridPane.add(remove,1,0);

        remove.setOnAction((removeItem) ->{
            if (!remove.getText().trim().isEmpty()) {
                kassaController.verwijderFromScannedItems(remove.getText());
                table.setItems(FXCollections.observableList(kassaController.getScannedItems()));
                remove.clear();

            }
        });

    }

    public GridPane getLayout() {
        return gridPane;
    }

    private String getSum(){
        double sum = 0;

        for (Artikel artikel: kassaController.getScannedItems()){
            sum += artikel.getVerkoopprijs();
        }
        return "Total: €"+String.format("%.2f", sum);
    }


}