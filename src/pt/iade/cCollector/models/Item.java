package pt.iade.cCollector.models;

import javafx.collections.ObservableList;
import pt.iade.models.dao.ItemDao;

/** Models a particular comic book as well as containing a list of the individual copies a user owns **/
public class Item {
	String name;
	String description;
	String category;
	ObservableList<userBook> userBooks;
	String bookSize;
	int itemId;	
	int collectionId;
	public Item(String name, String description, String category, int id, int collectionId ) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.itemId = id;
		this.collectionId = collectionId;
		this.userBooks = ItemDao.getUserBooksInItem(id, collectionId);
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getBookSize() {
		return userBooks.size();
	}
	
	public int getItemId() {
		return itemId;
	}

	public ObservableList<userBook> getUserBooks() {

		return userBooks;
	}
	
}
