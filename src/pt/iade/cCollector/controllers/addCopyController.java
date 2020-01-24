package pt.iade.cCollector.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pt.iade.cCollector.WindowManager;
import pt.iade.cCollector.models.Collection;
import pt.iade.cCollector.models.Edition;
import pt.iade.cCollector.models.Item;
import pt.iade.cCollector.models.User;
import pt.iade.models.dao.ItemDao;

/** Controls the add new copy screen**/

public class addCopyController {

    @FXML
    private TextField descriptionBox;

    @FXML
    private ChoiceBox<Edition> choiceBox;

    @FXML
    private Button doneButton;

    @FXML
    private Button backButton;
    
    private User user;
    private Item item;
    private Collection collection;
    private ObservableList<Edition> editions;
    
    
    public addCopyController(User user, Item item, Collection collection) {
    	this.user = user;
    	this.item = item;
    	this.collection = collection;
    	editions = ItemDao.getEditions(item.getItemId());
    }
    
    /**
     * Defines the choicebox with the editions of the seleted book.
     */
    
    public void initialize() {
    	choiceBox.setItems(editions);
    	
    }
    
    /**
     * Returns to the previous screen (the book view)
     * @param event
     */

    @FXML
    void backClick(ActionEvent event) {
    	WindowManager.openBookPageView(user, item, collection);
    }

    @FXML
    void choiceClick(MouseEvent event) {

    }

    /** Item is added to collection, the users collections are refreshed and returns to the book view
     * 
     * @param event
     */
    @FXML
    void doneClick(ActionEvent event) {
    	
    	ItemDao.insertUserBook(item.getItemId(), choiceBox.getValue().getId(), collection.getId(), descriptionBox.getText());
    	user.setCollections();
    	for (int i = 0; i < user.getCollections().size(); i++) {
    		if (collection.getId() == user.getCollections().get(i).getId())
    			collection = user.getCollections().get(i);
    	}
    	
    	for (int i = 0; i < collection.getItems().size(); i++) {
    		if(item.getItemId() == collection.getItems().get(i).getItemId())
    			item = collection.getItems().get(i);
    	}
    	
    	WindowManager.openBookPageView(user, item, collection);
    }

}
