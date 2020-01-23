package pt.iade.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.cCollector.models.Edition;
import pt.iade.cCollector.models.Item;
import pt.iade.cCollector.models.MysqlConnection;
import pt.iade.cCollector.models.userBook;

public class ItemDao {
	public static Connection connection; 
	
	/** getItemsFromCollectionId fetches all the items in a collection It 
	 * receives the ID of the collection and returns an arrayList of all items
	 * 
	 * @param id
	 * @return
	 */
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
	 * It receives the integers itemId and collectionId and returns the ArrayList of UserBooks userBooks
	 * 
	 * @param itemId
	 * @param collectionId
	 * @return
	 */
	
	public static ObservableList<userBook> getUserBooksInItem(int itemId, int collectionId) {
		connection = MysqlConnection.getConnection();
		ObservableList<userBook> userBooks = FXCollections.observableArrayList();
		String query = "SELECT userBook.description, userBook.bookId, Edition.publisher, Edition.langCode, Edition.publicationDate FROM userBook JOIN Edition ON userBook.idEdition = Edition.idEdition WHERE userBook.archived = 0 AND userBook.idCollection = ? AND userBook.idItem = ? ;";
		
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setInt(1, collectionId);
			st.setInt(2, itemId);
			int bookCounter = 1;   
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String description = rs.getString("description");
				String publisher = rs.getString("publisher");
				String langCode = rs.getString("langCode");
				int publicationDate = rs.getInt("publicationDate");
				int bookId = rs.getInt("bookId");
				userBooks.add(new userBook(description, publisher, langCode, publicationDate, bookCounter, bookId));
				bookCounter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userBooks;
	}
	
	/** Returns a list of users with more then one copy of the item defined by idItem
	 * 
	 * @param idItem
	 * @param userEmail
	 * @return
	 */
	
	public static ObservableList<String> getUsersWithBook(int idItem, String userEmail){
		connection = MysqlConnection.getConnection();
		String query = "SELECT User.userEmail, COUNT(*) AS number FROM userBook JOIN Item ON userBook.idItem = Item.idItem JOIN Collection on userBook.idCollection = Collection.idCollection JOIN User on Collection.user = User.userId WHERE Item.idItem = ? AND userBook.archived = 0 GROUP BY userEmail HAVING COUNT(*) > 1";
		ObservableList<String> EmailList = FXCollections.observableArrayList();
		
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setInt(1, idItem);
			ResultSet rs = st.executeQuery();
			while (rs.next()){
				if(!userEmail.equals(rs.getString("userEmail")))
					EmailList.add(rs.getString("userEmail") + " has " + rs.getInt("number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return EmailList;

	}
	
	/** Archives a userBook when a user removes it from their collection
	 * 
	 * @param userbook
	 */
	
	public static void archiveBook(userBook userbook) {
		String update = "UPDATE userBook SET archived = 1 WHERE userBook.bookId = ? ;";
		try {
			PreparedStatement st = connection.prepareStatement(update);
			st.setInt(1, userbook.getId());
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** Returns editions of an item as defined by itemId
	 * 
	 * @param itemId
	 * @return
	 */
	
	public static ObservableList<Edition> getEditions(int itemId){
		connection = MysqlConnection.getConnection();
		String query = "SELECT publisher, langCode, publicationDate, idEdition FROM Edition WHERE idItem = ?";
		ObservableList<Edition> editions = FXCollections.observableArrayList();
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setInt(1, itemId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String publisher = rs.getString("publisher");
				String langCode = rs.getString("langCode");
				int publicationDate = rs.getInt("publicationDate");
				int edition = rs.getInt("idEdition");
				editions.add(new Edition(publisher, langCode, publicationDate, edition));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return editions;
	}
	
	/** inserts a new userBook into a users collection
	 * 
	 * @param idItem
	 * @param idEdition
	 * @param idCollection
	 * @param description
	 */
	
	public static void insertUserBook(int idItem, int idEdition, int idCollection, String description) {
		connection = MysqlConnection.getConnection();
		String insert = "insert into userBook (idItem, idCollection, idEdition, description) value (?,?,?,?)";
		
		try {
			PreparedStatement st = connection.prepareStatement(insert);
			st.setInt(1, idItem);
			st.setInt(2, idCollection);
			st.setInt(3, idEdition);
			st.setString(4, description);
			st.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
