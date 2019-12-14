package pt.iade.cCollector.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.models.dao.CCollectorDao;

public class User {
	private String email;
	private int reputation;
	private ObservableList<Message> messages;
	private ObservableList<Collection> collections;
	
	public User(String email) {
		this.email = email;
		this.reputation = 0;
		this.messages = FXCollections.observableArrayList();
		this.collections = CCollectorDao.getCollections(email);
	}
	
	public ObservableList<Collection> getCollections(){
		return collections;
	}

	public String getEmail() {
		return email;
	}
	
	/** Synchronizes the collections with the ones currently available in the database
	 * used when a new collection is added by the user **/
	public void setCollections() {
		collections = CCollectorDao.getCollections(email);
	}
	
}

