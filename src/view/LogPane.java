package view;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import model.States.InBetaal;
import model.Verkoop;
import model.Verkoop;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author Rafael Polfliet
 */

public class LogPane implements KassaObserver {
    private GridPane gridPane= new GridPane();
    private TableView<Map.Entry<Verkoop,String>> table;
    private Map<Verkoop, String> map;
    private ObservableList<Map.Entry<Verkoop, String>> items;
    private ArrayList<Verkoop> verkopen;



    public LogPane() {

        map = new LinkedHashMap<>();
        table = new TableView<>();
        verkopen = new ArrayList<>();
        items = FXCollections.observableArrayList(new ArrayList<>());
        tableInit();
    }


    @Override
    public void update(Object arg) {
        ArrayList<Verkoop> nieuw = new ArrayList<>();
        for (Verkoop verkoop:(ArrayList<Verkoop>)arg){
            if (!verkopen.contains(verkoop) && verkoop.getVerkoopState() instanceof InBetaal){
                nieuw.add(verkoop);
                verkopen.add(verkoop);
            }
        }

        
        for (Verkoop verkoop:nieuw){
            if (verkoop.getVerkoopState() instanceof InBetaal) {
                map.put(verkoop, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            }
        }


        items = FXCollections.observableArrayList(map.entrySet());
        table.getItems().clear();
        table.setItems(items);
        table.refresh();
        
    }

    private void tableInit(){


        TableColumn<Map.Entry<Verkoop, String>, String> column1 = new TableColumn<>("Tijdstip");
        column1.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue().getValue()));
        column1.setMinWidth(187.5);


        TableColumn<Map.Entry<Verkoop, String>, String> column2 = new TableColumn<>("Totaal bedrag");
        column2.setCellValueFactory(cd -> Bindings.createStringBinding(() -> String.format("%.2f",cd.getValue().getKey().getSum())));
        column2.setMinWidth(187.5);


        TableColumn<Map.Entry<Verkoop, String>, String> column3 = new TableColumn<>("Korting");
        column3.setCellValueFactory(cd -> Bindings.createStringBinding(() -> String.format("%.2f", cd.getValue().getKey().getVerkoopState().korting())));
        column3.setMinWidth(187.5);


        TableColumn<Map.Entry<Verkoop, String>, String> column4 = new TableColumn<>("Te betalen bedrag");
        column4.setCellValueFactory(cd -> Bindings.createStringBinding(() -> String.format("%.2f",cd.getValue().getKey().getVerkoopState().finalSum())));
        column4.setMinWidth(187.5);



        table.getColumns().setAll(column1,column2,column3,column4);

        gridPane.add(table,0,0);
    }
    
    
    public GridPane getLayout() {
        return gridPane;
    }
}

