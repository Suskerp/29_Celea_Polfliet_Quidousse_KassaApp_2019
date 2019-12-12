package view;

import database.*;
import database.Enum.ArtikelDBEnum;
import database.Enum.LoadSaveEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import model.Discount.KortingEnum;

import javax.swing.*;

/**
 * @author Rafael Polfliet
 */
public class InstellingPane extends GridPane {
    private GridPane gridPane= new GridPane();
    private ComboBox comboBox1;
    private ComboBox comboBox2;
    private ComboBox comboBox3;
    private Label label1;
    private Label label2;
    private TextField drempel;
    private Button button;

    public InstellingPane() {

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        gridPane.getColumnConstraints().addAll(col1,col2,col3);

        label1 = new Label();
        label1.setText("Define memory setting");
        gridPane.add(label1, 0, 0);
        
        ObservableList<String> artikelDBContexts = FXCollections.observableList(ArtikelDBEnum.valuesToString());
        comboBox1 = new ComboBox();
        comboBox1.setItems(artikelDBContexts);

        gridPane.add(comboBox1,0,1);

        label2 = new Label();
        label2.setText("Define discount");
        gridPane.add(label2, 0, 2);

        ObservableList<String> kortingDBContexts = FXCollections.observableList(KortingEnum.valuesToString());
        comboBox3 = new ComboBox();
        comboBox3.setItems(kortingDBContexts);

        gridPane.add(comboBox3, 0,3);

        TextField procent = new TextField();
        procent.setPromptText("Procent korting");
        gridPane.add(procent, 1, 3);



        button = new Button("Save");
        gridPane.add(button,2,0);

        comboBox1.setOnAction((optionselected)  ->{
           comboBox1Selected();
        });

        button.setOnAction((Save) ->{
           savePreferences();
        });

        comboBox3.setOnAction((optionselected) ->{
            comboBox3Selected();
        });
    }

    public GridPane getLayout() {
        return gridPane;
    }

    private void comboBox1Selected(){
        if (comboBox1.getSelectionModel().getSelectedItem().toString().equals(ArtikelDBEnum.ARTIKEL_DB_MEM.toString())){
            ObservableList<String> loadSaveContext = FXCollections.observableList(LoadSaveEnum.valuesToString());
            comboBox2 = new ComboBox();
            comboBox2.setItems(loadSaveContext);
            gridPane.add(comboBox2,1,1);
        }else{
            gridPane.getChildren().remove(comboBox2);
        }
    }

    private void comboBox3Selected(){
        if(comboBox3.getSelectionModel().getSelectedItem().toString().equals(KortingEnum.Korting_Drempel.toString())){
            drempel = new TextField();
            drempel.setPromptText("Drempel waarde");
            gridPane.add(drempel, 2,3);
        }
        else if (comboBox3.getSelectionModel().getSelectedItem().toString().equals(KortingEnum.Korting_Groep.toString())){
                drempel = new TextField();
                drempel.setPromptText("Groep");
                gridPane.add(drempel, 2, 3);
        }
        else{
            gridPane.getChildren().remove(drempel);
        }


    }

    private void savePreferences(){
        try {
            if (gridPane.getChildren().contains(comboBox2)) {
                PropertiesLoadWrite.write(comboBox1.getSelectionModel().getSelectedItem().toString(), comboBox2.getSelectionModel().getSelectedItem().toString(), LoadSaveEnum.valueOf(comboBox2.getSelectionModel().getSelectedItem().toString()).getOmschrijving());
            } else {
                PropertiesLoadWrite.write(comboBox1.getSelectionModel().getSelectedItem().toString());
            }
        }catch (DatabaseException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Please fill out each menu",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
