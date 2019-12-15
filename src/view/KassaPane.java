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
import model.States.InAfsluit;
import model.States.StateException;

import javax.swing.*;


public class KassaPane{

    private GridPane gridPane = new GridPane();
    private TableView<Artikel> table = new TableView();
    private TextField searchText;
    private Label sum;
    private TextField remove;
    private KassaController kassaController;
    private Button afsluiten;
    private Label kortingLabel;
    private Label finalSumLabel;
    private Button annuleren;
    private Button hold;
    private Button betaal;



    public KassaPane(KassaController kassaController){

        this.kassaController = kassaController;

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(16.66666666666666);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(16.66666666666666);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(16.66666666666666);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(16.66666666666666);
        ColumnConstraints col5 = new ColumnConstraints();
        col5.setPercentWidth(16.66666666666666);
        ColumnConstraints col6 = new ColumnConstraints();
        col6.setPercentWidth(16.66666666666666);
        gridPane.getColumnConstraints().addAll(col1,col2,col3,col4,col5,col6);


        searchText = new TextField();
        searchText.setPromptText("Search here!");
        sum= new Label();
        remove = new TextField();
        remove.setPromptText("Remove a single product");
        afsluiten = new Button("Verkoop afsluiten");
        kortingLabel = new Label();
        finalSumLabel = new Label();
        annuleren = new Button("Annuleer verkoop");
        hold = new Button("Place on hold");
        betaal = new Button("Betalen");
        betaal.setDisable(true);

        tableInit();

        gridPane.add(sum,0,2);
        gridPane.add(table,0, 1,6,1);
        gridPane.add(remove,1,0);
        gridPane.add(searchText,0,0);
        gridPane.add(afsluiten,3,0);
        gridPane.add(kortingLabel,1,2);
        gridPane.add(finalSumLabel,2,2);
        gridPane.add(annuleren,4,0);
        gridPane.add(hold,2,0);
        gridPane.add(betaal,5,0);


        searchText.setOnAction((entered) ->{
            scan();
        });

        remove.setOnAction((removeItem) ->{
            remove();
        });

        afsluiten.setOnAction((pressed) ->{
            close();
        });

        annuleren.setOnAction((pressed) ->{
            annuleren();
        });

        hold.setOnAction((pressed) ->{
            hold();
        });

        betaal.setOnAction((pressed)->{
            betaal();
        });

    }

    public GridPane getLayout() {
        return gridPane;
    }

    private void getSum(){
       sum.setText("Totaal: €"+String.format("%.2f",kassaController.getSum()));
    }


    private void scan(){
        try {
            if (searchText.getText() != null) {
                kassaController.scanItem(searchText.getText());
                table.setItems(FXCollections.observableList(kassaController.getScannedItems()));
                getSum();
                searchText.clear();
            }
        }catch (DatabaseException | StateException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void remove(){
        if (!remove.getText().trim().isEmpty()) {
            kassaController.verwijder(remove.getText());
            table.setItems(FXCollections.observableList(kassaController.getScannedItems()));
            remove.clear();
            table.refresh();
            getSum();

            if (kassaController.getHuidigeVerkoopState() instanceof InAfsluit){
                kortingEnEindSom();
            }
        }
    }

    private void tableInit(){

        TableColumn<Artikel, String> colName = new TableColumn<>("Naam");
        colName.setCellValueFactory(new PropertyValueFactory<>("Naam"));
        colName.setMinWidth(200);
        
        TableColumn<Artikel, String> colOmschrijving = new TableColumn<>("Omschrijving");
        colOmschrijving.setCellValueFactory(new PropertyValueFactory<>("omschrijving"));
        colOmschrijving.setMinWidth(200);
        

        TableColumn<Artikel,Double> colVerkoopprijs = new TableColumn<>("Verkoopprijs");
        colVerkoopprijs.setCellValueFactory(new PropertyValueFactory<>("verkoopprijs") );
        colVerkoopprijs.setMinWidth(50);

        table.getColumns().addAll(colName,colOmschrijving,colVerkoopprijs);
    }

    private void close(){
        try {
            kassaController.afsluiten();
            kortingLabel.setText("Korting: €"+String.format("%.2f", kassaController.getKorting()));
            finalSumLabel.setText("Eind bedrag: €"+String.format("%.2f", kassaController.getFinalSum()));

            betaal.setDisable(false);
        }catch (StateException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void annuleren(){
        int reply = JOptionPane.showConfirmDialog(null, "Weet u zeker dat u wilt annuleren?", "Annuleren", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {

            kassaController.annuleren();
            table.getItems().clear();
            table.refresh();
            table.setItems(FXCollections.observableList(kassaController.getScannedItems()));
            getSum();
            kortingLabel.setText("");
            finalSumLabel.setText("");
            betaal.setDisable(true);
        }
    }

    private void kortingEnEindSom(){
        kortingLabel.setText("Korting: €"+String.format("%.2f",kassaController.getKorting()));
        finalSumLabel.setText("Eindsom: €"+String.format("%.2f",kassaController.getFinalSum()));
    }


    private void hold(){
        int reply = JOptionPane.showConfirmDialog(null, "Weet u zeker dat u dit item op hold wilt zetten?", "Annuleren", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            kassaController.placeOnHold();
            table.getItems().clear();
            table.refresh();
            getSum();
            kortingLabel.setText("");
            finalSumLabel.setText("");
            betaal.setDisable(true);
        }
    }

    private void betaal(){
        try {
            kassaController.betalen();
            table.getItems().clear();
            table.refresh();
            table.setItems(FXCollections.observableList(kassaController.getScannedItems()));
            getSum();
            kortingLabel.setText("");
            finalSumLabel.setText("");
            betaal.setDisable(true);
        }catch (StateException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}