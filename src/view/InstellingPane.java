package view;

import database.*;
import database.Enum.ArtikelDBEnum;
import database.Enum.LoadSaveEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import controller.KassaController;

import javax.swing.*;
import java.util.Arrays;

/**
 * @author Rafael Polfliet
 */
public class InstellingPane extends GridPane {
    private GridPane gridPane= new GridPane();
    private ComboBox comboBox1;
    private ComboBox comboBox2;
    private Button button;

    public InstellingPane() {

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        gridPane.getColumnConstraints().addAll(col1,col2,col3);
        
        ObservableList<String> artikelDBContexts = FXCollections.observableList(ArtikelDBEnum.valuesToString());
        comboBox1 = new ComboBox();
        comboBox1.setItems(artikelDBContexts);

        gridPane.add(comboBox1,0,0);

        button = new Button("Save");
        gridPane.add(button,2,0);

        comboBox1.setOnAction((optionselected)  ->{
           comboBox1Selected();
        });

        button.setOnAction((Save) ->{
           savePreferences();
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
            gridPane.add(comboBox2,1,0);
        }else{
            gridPane.getChildren().remove(comboBox2);
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
