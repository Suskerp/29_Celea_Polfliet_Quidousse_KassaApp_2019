package view;
/**
 * @author Jef Quidousse
 * */

import database.*;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import model.Artikel;
import controller.KassaController;
import model.ModelException;

import javax.swing.*;


public class KassaPane{

    private GridPane gridPane = new GridPane();
    private TableView<Artikel> table = new TableView();
    private TextField searchText;
    private Label sum;
    private TextField remove;
    private KassaController kassaController;
    private Button holdButton;
    private Label holdstatus;
    private Button afsluiten;
    private Label kortingLabel;
    private Label finalSumLabel;


    public KassaPane(KassaController kassaController){

        this.kassaController = kassaController;

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(25);
        gridPane.getColumnConstraints().addAll(col1,col2,col3,col4);


        searchText = new TextField();
        searchText.setPromptText("Search here!");
        sum= new Label();
        remove = new TextField();
        remove.setPromptText("Remove a single product");
        holdButton = new Button("Place cart on hold");
        holdstatus = new Label("No cart on hold");
        afsluiten = new Button("Verkoop afsluiten");
        kortingLabel = new Label();
        finalSumLabel = new Label();

        tableInit();

        gridPane.add(sum,0,2);
        gridPane.add(table,0, 1,4,1);
        gridPane.add(remove,1,0);
        gridPane.add(holdButton,2,0);
        gridPane.add(holdstatus,3,0);
        gridPane.add(searchText,0,0);
        gridPane.add(afsluiten,3,2);
        gridPane.add(kortingLabel,1,2);
        gridPane.add(finalSumLabel,2,2);


        searchText.setOnAction((entered) ->{
            scan();
        });

        remove.setOnAction((removeItem) ->{
            remove();
        });

        holdButton.setOnAction((pressed) ->{
            hold();
        });
        afsluiten.setOnAction((pressed) ->{
            close();
        });



    }

    public GridPane getLayout() {
        return gridPane;
    }

    private void getSum(){
       sum.setText("Korting: €"+String.format("%.2f",kassaController.getSum()));
    }


    private void scan(){
        try {
            if (searchText.getText() != null) {
                kassaController.scanItem(searchText.getText());
                table.setItems(FXCollections.observableList(kassaController.getScannedItems()));
                getSum();
                searchText.clear();
                kassaController.notifyObservers();
            }
        }catch (DatabaseException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void remove(){
        if (!remove.getText().trim().isEmpty()) {
            kassaController.verwijder(remove.getText());
            table.setItems(FXCollections.observableList(kassaController.getScannedItems()));
            remove.clear();
            getSum();
        }
    }

    private void hold(){
        try {
            if (holdButton.getText().equalsIgnoreCase("Place cart on hold")) {
                kassaController.placeOnHold();
                holdButton.setText("Remove cart from hold");
                holdstatus.setText("A cart is on hold");
                table.refresh();
                getSum();
            } else {
                kassaController.returnFromHold();
                holdButton.setText("Place cart on hold");
                holdstatus.setText("No cart on hold");
                table.refresh();
                getSum();
            }
        }catch (ModelException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tableInit(){
        TableColumn<Artikel, String> colOmschrijving = new TableColumn<>("Omschrijving");
        colOmschrijving.setCellValueFactory(new PropertyValueFactory<>("omschrijving"));
        colOmschrijving.setMinWidth(200);

        TableColumn<Artikel,Double> colVerkoopprijs = new TableColumn<>("Verkoopprijs");
        colVerkoopprijs.setCellValueFactory(new PropertyValueFactory<>("verkoopprijs") );
        colVerkoopprijs.setMinWidth(50);

        table.getColumns().addAll(colOmschrijving,colVerkoopprijs);
    }

    private void close(){
        kortingLabel.setText("Korting: €"+String.format("%.2f", kassaController.getKorting()));
        finalSumLabel.setText("Eind bedrag: €"+String.format("%.2f", kassaController.getFinalSum()));
    }

}