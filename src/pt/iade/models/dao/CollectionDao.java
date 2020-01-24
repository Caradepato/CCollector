package pt.iade.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.cCollector.models.Collection;
import pt.iade.cCollector.models.MysqlConnection;
import pt.iade.cCollector.models.User;

/** This class handles information about collections and catalogues **/

public class CollectionDao {
	
	static Connection connection;
	
	/** getCollection returns an arrayList containing all the collections the user with the email sent via the variable "user" has in the database.
	 * 
	 * @param user
	 * @return
	 */
	
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
	
	/** Gets a list of all the catalogues available as empty collections
	 * 
	 * @param user
	 * @return
	 */
	
	public static ObservableList<Collection> getAllCatalogues(User user) {
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
				  boolean userHas = false;
				  for (int i = 0; i < user.getCollections().size(); i++) {
					  if (name.equals(user.getCollections().get(i).getCatalogueName())) {
						  userHas = true;
						  break;
					  }
				  }
				  if(!userHas) 
					  collections.add(new Collection(name, description, id));
			  }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return collections;
	}
	
	/** adds a collection to the user 
	 * 
	 * @param collection
	 * @param user
	 */
	
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
}