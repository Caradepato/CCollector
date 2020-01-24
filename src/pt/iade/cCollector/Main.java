package pt.iade.cCollector;

import javafx.application.*;
import javafx.stage.*;
import pt.iade.cCollector.WindowManager;

/**
 * Main class.
 * 
 * @author Tomas Martins and Carlos Vigário
 *
 */
public class Main extends Application {

	@Override	
	
	public void start(Stage primaryStage) {
		WindowManager.setPrimaryStage(primaryStage);
		WindowManager.openMainWindow();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
