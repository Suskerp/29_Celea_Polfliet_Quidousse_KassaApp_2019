package view;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Artikel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * @author Rafael Polfliet
 */

public class LogPane implements KassaObserver {
    private GridPane gridPane= new GridPane();
    private javafx.scene.control.Label log;



    public LogPane() {
        log = new Label();

        gridPane.add(log,0,0);
    }


    @Override
    public void update(Object o) {
        ArrayList<Artikel> items = (ArrayList<Artikel>) o;
        String out = log.getText();
        for (Artikel artikel:items){
            out += artikel.getCode() + " " + artikel.getNaam() + " " + artikel.getOmschrijving() + " " + artikel.getVerkoopprijs() + "\t\t\t\t\t\t\tTijdstip van verkoop: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))+ "\n\n";
        }
        log.setText(out);
    }

    public GridPane getLayout() {
        return gridPane;
    }
}
