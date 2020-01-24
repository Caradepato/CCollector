package pt.iade.cCollector.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.iade.cCollector.WindowManager;
import pt.iade.cCollector.models.Collection;
import pt.iade.cCollector.models.User;
import pt.iade.models.dao.CollectionDao;

/** this controls the choose collection screen 
 * This screen lets the user add a new collection to their collections.**/

public class ChooseCollectionViewController {
		

	User user;
	@FXML
	TableView<Collection> chooseCollection = new TableView<Collection>();
	@FXML
	TableColumn<String, Collection> collectionColumn = new TableColumn<>("Name");
	@FXML
	TableColumn<String, Collection> descriptionColumn = new TableColumn<>("Description");
	@FXML
	Button backButton;
	
	public ChooseCollectionViewController(User user) {
		this.user = user;
	}

	/** Initializes the following things:
	 * A list of all the collections the user does not have **/
	public void initialize() {
		/** Sets up the list of all catalogs by fetching information from the database**/
		ObservableList<Collection> collections = CollectionDao.getAllCatalogues(user);
		collectionColumn.setCellValueFactory(new PropertyValueFactory<String, Collection>("catalogueName"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<String, Collection>("catalogueDescription"));
		chooseCollection.setItems(collections);
		
		/** Adds the chosen collection to the database, reloads the users list of collections in the application and opens the user collection view. **/
		chooseCollection.setOnMouseClicked(
				(event)->{ 
			Collection selectedCollection = chooseCollection.getSelectionModel().getSelectedItem();
				CollectionDao.addCollection(selectedCollection, user);
				user.setCollections();
				WindowManager.openUserCollectionView(user);
				});
	}
	
	/** returns to the user collection view **/
	
	public void backClick() {
		WindowManager.openUserCollectionView(user);
	}
	
	
	
}

		
