package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import controller.KassaController;
import view.KassaView;
import view.KlantView;
import view.LogPane;
import view.ProductOverviewPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		KassaController kassaController = new KassaController();
		LogPane logPane = new LogPane();
		kassaController.registerObserverLog(logPane);
		ProductOverviewPane productOverviewPane = new ProductOverviewPane();
		kassaController.registerObserverInventory(productOverviewPane);
		kassaController.registerObserverKlant(new KlantView());
		new KassaView(kassaController,productOverviewPane,logPane);
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
