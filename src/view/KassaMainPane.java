package view;


import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import controller.KassaController;

/**
 * @author Rafael Polfliet - Jef Quidousse
 */
public class KassaMainPane extends BorderPane {
	public KassaMainPane(KassaController kassaController){
		TabPane tabPane = new TabPane();
	    KassaPane kassaPane = new KassaPane(kassaController);
        Tab kassaTab = new Tab("Kassa", kassaPane.getLayout());
        ProductOverviewPane productOverviewPane = kassaController.getProductOverviewPane();
        Tab artikelTab = new Tab("Artikelen",productOverviewPane.getLayout());
        InstellingPane instellingPane = new InstellingPane();
        Tab instellingTab = new Tab("Instellingen",instellingPane.getLayout());
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);




	    this.setCenter(tabPane);
	}
}
