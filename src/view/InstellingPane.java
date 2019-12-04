package view;

import database.ArtikelDBContext;
import database.ArtikelDBEnum;
import database.ArtikelDBInMemory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

/**
 * @author Rafael Polfliet
 */
public class InstellingPane extends GridPane {
    private GridPane gridPane= new GridPane();
    private ComboBox comboBox1;
    private ComboBox comboBox2;
    public InstellingPane() {

        
        ObservableList<String> artikelDBContexts = FXCollections.observableList(ArtikelDBContext.getContexts());
        comboBox1 = new ComboBox();
        comboBox1.setItems(artikelDBContexts);

        gridPane.add(comboBox1,0,0);

        comboBox1.setOnAction((optionselected)  ->{
            if (comboBox1.getSelectionModel().getSelectedItem().equals("ARTIKEL_DB_MEM")){
                ObservableList<String> loadSaveContext = FXCollections.observableList(ArtikelDBInMemory.getContexts());
                comboBox2 = new ComboBox();
                comboBox2.setItems(loadSaveContext);
                gridPane.add(comboBox2,1,0);
            }else{
                gridPane.getChildren().remove(comboBox2);
            }
        });
    }

    public GridPane getLayout() {
        return gridPane;
    }
}
