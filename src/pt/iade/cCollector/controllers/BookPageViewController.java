package pt.iade.cCollector.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
import pt.iade.models.dao.CCollectorDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookPageViewController {

	private User user;
	private Item item;
	private Collection collection;
	
    @FXML
    private TextFlow itemName;
    
    @FXML
    private TextFlow bookNumber;
    
    @FXML
    private Button backButton;

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
	}
	

	/** Returns to the browse collection screen when the back button is clicked
	 * 
	 * @param event
	 */
    @FXML
    public void backClick(ActionEvent event) {
    	WindowManager.openBrowseCollectionView(user, collection);
    }
    
    /** Initializes the scene**/
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
    	
    	usersOwn.setItems(CCollectorDao.getUsersWithBook(item.getItemId(), user.getEmail()));
    	
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

    }
    
    /** Sets the description of the users copy in the correct format and places it in the textflow
     * 
     * @param userbook
     */
    
    private void setDescriptionBoxText(userBook userbook){
    	Text descriptionText = new Text("Condition: " + userbook.getDescription());
    	Text publisherText = new Text("Publisher: " + userbook.getPublisher());
    	Text languageText = new Text("Language: " + userbook.getLanguage());
		copyDescription.getChildren().add(descriptionText);
		copyDescription.getChildren().add(publisherText);
		copyDescription.getChildren().add(languageText);
    }
    
}