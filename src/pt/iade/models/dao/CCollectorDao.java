package pt.iade.models.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.cCollector.models.Collection;
import pt.iade.cCollector.models.Item;
import pt.iade.cCollector.models.MysqlConnection;
import pt.iade.cCollector.models.User;
import pt.iade.cCollector.models.userBook;




public class CCollectorDao {
	
	private static java.sql.Connection connection;
	
	
	/** getCollection returns an arrayList containing all the collections the user with the email sent via the variable "user" has in the database. **/
	
	public static ObservableList<Collection> getCollections(String user){
	  connection = MysqlConnection.getConnection();
	  String query = "SELECT Catalogue.name, Catalogue.description, Collection.idCollection FROM User JOIN Collection ON User.userId = Collection.user JOIN Catalogue ON Collection.catalogue = Catalogue.idCatalogue WHERE User.userEmail LIKE ? ;";
	  ObservableList<Collection> collections = FXCollections.observableArrayList();
	  try {
		  PreparedStatement st = connection.prepareStatement(query);
		  st.setString(1, user);
		  ResultSet rs = st.executeQuery();
		  
		  while (rs.next()) {
			  String name = rs.getString("name");
			  String description = rs.getString("description");
			  int collectionId = rs.getInt("idCollection");
			  collections.add(new Collection(collectionId, name, description));
		  }
	} catch (SQLException e) {
		e.printStackTrace();
	}		
	  return collections;
	}
	
	/** VerifyPassword controls whether the entered password matches what is registered in the database.
	 * 	It takes the entered email and password and returns a boolean : True if it matches and False if it either does not match or if the email is not registered **/
	
	public static boolean verifyPassword(String email, String password) {
		
		String query = "SELECT User.password FROM User WHERE User.userEmail LIKE ?;";
		String pass = "";
		try {
			connection = MysqlConnection.getConnection();
			PreparedStatement st = connection.prepareStatement(query);
			st.setString(1,email);
			ResultSet rs = st.executeQuery();
			if (rs.next())
				pass = rs.getString("password");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
		if(pass.equals(password) && pass != "")
			return true;
		else
			return false;
		
	}
	
	/** getItemsFromCollectionId fetches all the items in a collection
	 * It receives the ID of the collection and returns an arrayList of all items**/
	public static ObservableList<Item> getItemsFromCollectionId(int id) {
		
		ObservableList<Item> items = FXCollections.observableArrayList();
		connection = MysqlConnection.getConnection();
		String query = "SELECT Item.name, Item.description, Item.category, Item.idItem FROM Collection JOIN Item ON Collection.catalogue = Item.catalogueId WHERE Collection.idCollection LIKE ?";
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String name = rs.getString("name");
					String itemDescription = rs.getString("description");
					String category = rs.getString("category");
					int itemId = rs.getInt("idItem");
					items.add(new Item(name, itemDescription, category, itemId, id ));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return items;
	}
	
	/** getUserBooksInItem returns all userBooks of an item
	 * It receives the integers itemId and collectionId and returns the ArrayList of UserBooks userBooks **/
	
	public static ObservableList<userBook> getUserBooksInItem(int itemId, int collectionId) {
		connection = MysqlConnection.getConnection();
		ObservableList<userBook> userBooks = FXCollections.observableArrayList();
		String query = "SELECT userBook.description, Edition.publisher, Edition.langCode, Edition.publicationDate FROM userBook JOIN Edition ON userBook.idEdition = Edition.idEdition WHERE userBook.idCollection = ? AND userBook.idItem = ? ;";
		
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setInt(1, collectionId);
			st.setInt(2, itemId);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int bookCounter = 1;
				String description = rs.getString("description");
				String publisher = rs.getString("publisher");
				String langCode = rs.getString("langCode");
				int publicationDate = rs.getInt("publicationDate");
				userBooks.add(new userBook(description, publisher, langCode, publicationDate, bookCounter));
				bookCounter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userBooks;
	}

	
	public static ObservableList<String> getUsersWithBook(int idItem, String userEmail){
		connection = MysqlConnection.getConnection();
		String query = "SELECT User.userEmail, COUNT(*) AS number FROM userBook JOIN Item ON userBook.idItem = Item.idItem JOIN Collection on userBook.idCollection = Collection.idCollection JOIN User on Collection.user = User.userId WHERE Item.idItem = ? GROUP BY userEmail HAVING COUNT(*) > 1";
		ObservableList<String> EmailList = FXCollections.observableArrayList();
		
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setInt(1, idItem);
			ResultSet rs = st.executeQuery();
			while (rs.next()){
				if(userEmail != rs.getString("userEmail"))
					EmailList.add(rs.getString("userEmail") + " has " + rs.getInt("number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return EmailList;

	}
	
	/** Registers a new user on the database **/
	
	public static void registerNewUser(String email, String password) {
		connection = MysqlConnection.getConnection();
		String query = "Insert into User (userEmail, password) Values (?,?);";
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setString(1, email);
			st.setString(2, password);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	/** Gets a list of all the catalogues available as empty collections **/
	
	public static ObservableList<Collection> getAllCatalogues() {
		connection = MysqlConnection.getConnection();
		String query = "Select Catalogue.name, Catalogue.description, Catalogue.idCatalogue from Catalogue;";
		ObservableList<Collection> collections = FXCollections.observableArrayList();
		try {
			PreparedStatement st = connection.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				  String name = rs.getString("name");
				  String description = rs.getString("description");
				  int id = rs.getInt("idCatalogue");
				  collections.add(new Collection(name, description, id));
			  }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return collections;
	}
	
	/** adds a collection to the user **/
	
	public static void addCollection(Collection collection, User user) {
		connection = MysqlConnection.getConnection();
		String query = "select User.userId FROM User WHERE User.userEmail LIKE ?";
		int id = -1;
		
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setString(1, user.getEmail());
			ResultSet rs = st.executeQuery();
			if (rs.next())
				id = rs.getInt("userId");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		String insert = "insert into Collection (user, catalogue) value (?,?)";
		
		try {
			PreparedStatement st = connection.prepareStatement(insert);
			st.setInt(1, id);
			st.setInt(2, collection.getId());
			st.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** checks if a user is already registered **/
	
	public static boolean userExists(String userEmail) {
		connection = MysqlConnection.getConnection();
		String query = "SELECT User.userEmail FROM User WHERE User.userEmail LIKE ?";
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setString(1, userEmail);
			ResultSet rs = st.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
