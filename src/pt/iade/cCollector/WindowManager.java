package pt.iade.cCollector;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pt.iade.cCollector.controllers.BookPageViewController;
import pt.iade.cCollector.controllers.BrowseCollectionViewController;
import pt.iade.cCollector.controllers.ChooseCollectionViewController;
import pt.iade.cCollector.controllers.CreateCollectionViewController;
import pt.iade.cCollector.controllers.LoginController;
import pt.iade.cCollector.controllers.UserCollectionViewController;
import pt.iade.cCollector.models.Collection;
import pt.iade.cCollector.models.Item;
import pt.iade.cCollector.models.User;


public class WindowManager {

	private static Stage primaryStage;
	
	private static Stage secondaryStage;
	
	private static Scene scene;

	public static void setPrimaryStage(Stage primaryStage) {
		WindowManager.primaryStage = primaryStage;
	}
	
	
	
	public static void backToMainWindow() {
		openMainWindow();
		secondaryStage.close();
	}
	
	/** Opens the main log in window **/
	
	public static void openMainWindow() {
		openWindow("scenes/login.fxml",
				primaryStage,new LoginController());
		primaryStage.show();
	}
	
	/** Opens create collection screen
	 * This screen is the first screen a user sees upon registering 
	 * @param user
	 **/
	public static void openCreateCollectionView(User user) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("scenes/createCollectionView.fxml"));
			loader.setController(new CreateCollectionViewController(user));
			Pane root;
			root = loader.load();
			scene = new Scene(root);
			primaryStage.close();
			secondaryStage = new Stage();
			secondaryStage.setScene(scene);
			secondaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/** Opens the choose Collection View screen.
	 * This screen allows the user to add a collection by choosing from a list of available catalogs.
	 * @param user
	 */
	
	public static void openChooseCollectionView(User user) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("scenes/chooseCollectionView.fxml"));
			loader.setController(new ChooseCollectionViewController(user));
			Pane root;
			root = loader.load();
			scene = new Scene(root);
			primaryStage = new Stage();
			primaryStage.setScene(scene);
			secondaryStage.close();
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/** Opens the collection view.
	 * this screen shows a user a list of their collections
	 * @param user
	 */
	
	public static void openUserCollectionView(User user) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("scenes/userCollectionView.fxml"));
			loader.setController(new UserCollectionViewController(user));
			Pane root;
			root = loader.load();
			scene = new Scene(root);
			primaryStage.close();
			secondaryStage = new Stage();
			secondaryStage.setScene(scene);
			secondaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Open Browse Collection View
	 * This screen shows a user all the items within a collection, and how many of each they own.
	 * @param user
	 * @param collection
	 */
	
	public static void openBrowseCollectionView(User user, Collection collection) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("scenes/browseCollectionView.fxml"));
			loader.setController(new BrowseCollectionViewController(user, collection));
			Pane root;
			root = loader.load();
			scene = new Scene(root);
			secondaryStage.close();
			primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Opens the book page view.
	 * This page shows the user information about a specific Item and to view what copies they have of it, if any.
	 * The page also shows the user any other users who have duplicate copies of the book
	 * @param user
	 * @param item
	 * @param collection
	 */
	
	public static void openBookPageView(User user, Item item, Collection collection) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("scenes/bookPageView.fxml"));
			loader.setController(new BookPageViewController(user, item, collection));
			Pane root;
			root = loader.load();
			scene = new Scene(root);
			primaryStage.close();
			secondaryStage = new Stage();
			secondaryStage.setScene(scene);
			secondaryStage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void openWindow(String viewPath, Stage window, 
			Object controller) {
		try {
			Parent root = createNewNodeTree(viewPath, controller);
			Scene scene = new Scene(root);
			window.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Parent createNewNodeTree(String viewPath, Object controller) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				Main.class.getResource(viewPath));
		loader.setController(controller);;
		Parent root = loader.load();
		return root;
	}
	
}
