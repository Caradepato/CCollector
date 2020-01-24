package pt.iade.cCollector.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import pt.iade.cCollector.WindowManager;
import pt.iade.cCollector.models.Collection;
import pt.iade.cCollector.models.Item;
import pt.iade.cCollector.models.User;
import pt.iade.cCollector.models.userBook;
import pt.iade.models.dao.ItemDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

import javafx.beans.value.*;

/**
 * Controls the book view
 *  **/

public class BookPageViewController {

	private User user;
	private Item item;
	private Collection collection;
	int bookIndex;
	
    @FXML
    private TextFlow itemName;
    
    @FXML
    private TextFlow bookNumber;
    
    @FXML
    private Button backButton;
    
    @FXML
    private Button addBook;
    
    @FXML
    private Button removeBook;

    @FXML
    private ImageView bookImage;

    @FXML
    private TextFlow bookDescription;

    @FXML
    private ListView<String> usersOwn;

    @FXML
    private TextFlow copyDescription;
    
    @FXML
    private ChoiceBox<String> selectBook;
	
	public BookPageViewController(User user, Item item, Collection collection) {
		this.user = user;
		this.item = item;
		this.collection = collection;
		bookIndex = 0;
	}
	

	/** Returns to the browse collection screen when the back button is clicked
	 * 
	 * @param event
	 */
    @FXML
    public void backClick(ActionEvent event) {
		user.setCollections();
    	WindowManager.openBrowseCollectionView(user, collection);
    }
    
    /**
     * Adds a new copy of a userBook 
     */
    @FXML
    public void addBook() {
    	WindowManager.openNewBookView(user, item, collection);
    }
    /**
     * Removes an owned copy of the book selected by the choicebox.
     */
    @FXML
    public void removeBook() {
    	
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("Warning");
		confirm.setHeaderText("Confirmation");
		confirm.setContentText("Are you sure you want to remove this book? ");
		Optional<ButtonType> result = confirm.showAndWait();
		
		if (result.get() == ButtonType.OK)
			ItemDao.archiveBook(item.getUserBooks().get(bookIndex));
		
    }
    /** this initialization does the following tasks:
     * Sets the item name;
     * Sets the item description;
     * Fetches the users that own more then one copy of this book;
     * Sets how many books the logged user has;
     * Sets the description of the currently selected userbook (by default the book with the lowest ID)
     * Defines the functionality of the choicebox (populates it with all the copies that the user owns).
     */
    public void initialize() {
    	
    	/** set item name **/
    	Text bookName = new Text(item.getName());
    	bookName.setFill(Color.BLACK);
    	bookName.setFont(Font.font(14.0));
    	bookName.setTextAlignment(TextAlignment.CENTER);
    	itemName.getChildren().add(bookName);
    	
    	/** set item description **/
    	
    	Text itemDescriptionText = new Text(item.getDescription());
    	itemDescriptionText.setFill(Color.BLACK);
    	itemDescriptionText.setFont(Font.font(10.0));
    	bookDescription.getChildren().add(itemDescriptionText);
    	
    	/** set users own **/
    	
    	usersOwn.setItems(ItemDao.getUsersWithBook(item.getItemId(), user.getEmail()));
    	
    	/** set book number **/
    	
    	int quantity = item.getBookSize();
    	
    	if (quantity > 1)
    		bookNumber.getChildren().add(new Text(quantity + " books"));
    	else if (quantity == 1)
    		bookNumber.getChildren().add(new Text(quantity + " book"));
    	else
    		bookNumber.getChildren().add(new Text("0 books"));
    	
    	/** set userBook description */

    	if (item.getBookSize() > 0) {
    		setDescriptionBoxText(item.getUserBooks().get(0));
    		ObservableList<String> bookNumbers = FXCollections.observableArrayList();
    		for (int i = 0; i < item.getBookSize(); i++) {
    			bookNumbers.add(item.getUserBooks().get(i).getSelectorText());
    		}
    		selectBook.setItems(bookNumbers);}
    	
    	

    
    
    /** Defines the functionality of the choice box
     * Changes description to that of the chosen userBook
     */
    
    selectBook.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
        public void changed(ObservableValue ov, Number number,  Number new_value) 
        { 
           copyDescription.getChildren().clear();
           bookIndex = new_value.intValue();
           setDescriptionBoxText(item.getUserBooks().get(new_value.intValue()));
        } 
    });
  }
    
    /** Sets the description of the users copy in the correct format and places it in the description text flow
     * 
     * @param userbook
     */
    
    private void setDescriptionBoxText(userBook userbook){
    	Text descriptionText = new Text("Condition: " + userbook.getDescription() + "\r\n");
    	Text publisherText = new Text("Publisher: " + userbook.getPublisher() + "\r\n");
    	Text languageText = new Text("Language: " + userbook.getLanguage());
		copyDescription.getChildren().add(descriptionText);
		copyDescription.getChildren().add(publisherText);
		copyDescription.getChildren().add(languageText);
    }
    
}