package pt.iade.cCollector.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.iade.cCollector.WindowManager;
import pt.iade.cCollector.models.Collection;
import pt.iade.cCollector.models.Item;
import pt.iade.cCollector.models.User;
/** controls the browse collection view
 * 
 * That is, it controls an overview of all the items in a chosen collection.
 */
public class BrowseCollectionViewController {

	Collection collection;
	User user;

	@FXML
	TableView<Item> itemTable = new TableView<Item>();
	@FXML
	Button Back;
	@FXML
	Button addCollection;


	@FXML
	TableColumn<String, Item> column1;	
	@FXML
	TableColumn<String, Item> column2;
	@FXML
	TableColumn<String, Item> column3;	
	@FXML
	TableColumn<Integer, Item> column4;

	
	public BrowseCollectionViewController(User user, Collection collection) {
		this.user = user;
		this.collection = collection;
		
	}

	/** this initializes the following things:
	 * The table view showing the name, description and category of a book as well as how many copies the user owns;
	 * 
	 * Listens for the event which selects one of the books in the list.
	 */
	public void initialize() {
		/** Setting up the table view with information about items **/
		column1.setCellValueFactory(new PropertyValueFactory<String, Item>("name"));
		column2.setCellValueFactory(new PropertyValueFactory<String, Item>("description"));
		column3.setCellValueFactory(new PropertyValueFactory<String, Item>("category"));
		column4.setCellValueFactory(new PropertyValueFactory<Integer, Item>("bookSize"));
		
		ObservableList<Item> items = collection.getItems();
		itemTable.setItems(items);
		
		/** Opens the book view of the item that was selected in the list **/
		itemTable.setOnMouseClicked(
				(event)->{ 
			Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
				WindowManager.openBookPageView(user, selectedItem, collection);
				});
	}
	
	/** returns to the user collection view **/
	public void backClick() {
		WindowManager.openUserCollectionView(user);
	}

}
