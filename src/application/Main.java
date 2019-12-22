package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import controller.KassaController;
import view.KassaView;
import view.KlantView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		new KassaController();
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
