package pt.iade.cCollector.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.iade.cCollector.WindowManager;
import pt.iade.cCollector.models.Collection;
import pt.iade.cCollector.models.User;
import pt.iade.models.dao.CCollectorDao;

public class ChooseCollectionViewController {
		

	User user;
	@FXML
	TableView<Collection> chooseCollection = new TableView<Collection>();
	@FXML
	TableColumn<String, Collection> collectionColumn = new TableColumn<>("Name");
	@FXML
	TableColumn<String, Collection> descriptionColumn = new TableColumn<>("Description");
	
	
	public ChooseCollectionViewController(User user) {
		this.user = user;
	}


	public void initialize() {
		/** Sets up the list of all catalogs by fetching information from the database **/
		ObservableList<Collection> collections = CCollectorDao.getAllCatalogues();
		collectionColumn.setCellValueFactory(new PropertyValueFactory<String, Collection>("catalogueName"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<String, Collection>("catalogueDescription"));
		chooseCollection.setItems(collections);
		
		/** Adds the chosen collection to the database, reloads the users list of collections in the application and opens the user collection view. **/
		chooseCollection.setOnMouseClicked(
				(event)->{ 
			Collection selectedCollection = chooseCollection.getSelectionModel().getSelectedItem();
				CCollectorDao.addCollection(selectedCollection, user);
				user.setCollections();
				WindowManager.openUserCollectionView(user);
				});
	}
	
	
	
}

		
