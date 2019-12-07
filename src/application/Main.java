package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import controller.KassaController;
import view.KassaView;
import view.KlantView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		KassaController kassaController = new KassaController();
		KlantView klantView = new KlantView();
		kassaController.registerObserver(klantView);
		KassaView kassaView = new KassaView(kassaController);


	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
