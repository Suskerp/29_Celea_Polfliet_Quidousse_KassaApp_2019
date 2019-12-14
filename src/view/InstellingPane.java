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
    private ComboBox dbContextComboBox;
    private ComboBox dbInMemoryComboBox;
    private ComboBox kortingComboBox;
    private Label label1;
    private Label label2;
    private TextField extraVariable;
    private Button button;
    private TextField procent;

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
        dbContextComboBox = new ComboBox();
        dbContextComboBox.setValue("Selecteer");
        dbContextComboBox.setItems(artikelDBContexts);

        gridPane.add(dbContextComboBox,0,1);

        label2 = new Label();
        label2.setText("Define discount");
        gridPane.add(label2, 0, 2);

        ObservableList<String> kortingDBContexts = FXCollections.observableList(KortingEnum.valuesToString());
        kortingComboBox = new ComboBox();
        kortingComboBox.setValue("Selecteer");
        kortingDBContexts.add("Geen korting");
        kortingComboBox.setItems(kortingDBContexts);

        gridPane.add(kortingComboBox, 0,3);

        procent = new TextField();
        procent.setPromptText("Procent korting");
        gridPane.add(procent, 1, 3);



        button = new Button("Save");
        gridPane.add(button,2,1);

        dbContextComboBox.setOnAction((optionselected)  ->{
           dbContextComboBoxSelected();
        });

        button.setOnAction((Save) ->{
           savePreferences();
        });

        kortingComboBox.setOnAction((optionselected) ->{
            kortingComboBoxSelected();
        });
    }

    public GridPane getLayout() {
        return gridPane;
    }

    private void dbContextComboBoxSelected(){
        if (dbContextComboBox.getSelectionModel().getSelectedItem().toString().equals(ArtikelDBEnum.ARTIKEL_DB_MEM.toString())){
            ObservableList<String> loadSaveContext = FXCollections.observableList(LoadSaveEnum.valuesToString());
            dbInMemoryComboBox = new ComboBox();
            dbInMemoryComboBox.setValue(loadSaveContext.get(0));
            dbInMemoryComboBox.setItems(loadSaveContext);
            gridPane.add(dbInMemoryComboBox,1,1);
        }else{
            gridPane.getChildren().remove(dbInMemoryComboBox);
        }
    }

    private void kortingComboBoxSelected(){
        if (gridPane.getChildren().contains(extraVariable)) gridPane.getChildren().remove(extraVariable);

        if(kortingComboBox.getSelectionModel().getSelectedItem().toString().equals(KortingEnum.Korting_Drempel.toString())){
            extraVariable = new TextField();
            extraVariable.setPromptText("drempel waarde");
            gridPane.add(extraVariable, 2,3);
        }
        else if (kortingComboBox.getSelectionModel().getSelectedItem().toString().equals(KortingEnum.Korting_Groep.toString())){
            extraVariable = new TextField();
            extraVariable.setPromptText("Groep");
           gridPane.add(extraVariable, 2, 3);
        }
    }

    private void savePreferences(){
        try {
            String dbContextSelection = dbContextComboBox.getSelectionModel().getSelectedItem().toString();
            String kortingSelection = kortingComboBox.getSelectionModel().getSelectedItem().toString();
            int p = Integer.parseInt(procent.getText());
            String extraVar = "";

            if (gridPane.getChildren().contains(extraVariable) && !extraVariable.getText().trim().isEmpty()) {
                if (KortingEnum.Korting_Drempel.equals(KortingEnum.valueOf(kortingSelection))) {
                    extraVar = extraVariable.getText();
                    Double.parseDouble(extraVar);
                }else if(KortingEnum.Korting_Groep.equals(KortingEnum.valueOf(kortingSelection))){
                    extraVar = extraVariable.getText();
                }
            }


            if (gridPane.getChildren().contains(dbInMemoryComboBox)) {
                String dbInMemorySelection = dbInMemoryComboBox.getSelectionModel().getSelectedItem().toString();
                PropertiesLoadWrite.write(dbContextSelection,dbInMemorySelection,LoadSaveEnum.valueOf(dbInMemorySelection).getOmschrijving(),kortingSelection,p,extraVar);
            } else {
                PropertiesLoadWrite.write(dbContextSelection,kortingSelection,p,extraVar);
            }

        }catch (DatabaseException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Please fill out each menu/field",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please fill percentage field with a number",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
