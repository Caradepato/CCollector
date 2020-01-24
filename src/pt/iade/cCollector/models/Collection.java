package pt.iade.cCollector.models;

import javafx.collections.ObservableList;
import pt.iade.models.dao.CCollectorDao;
import pt.iade.models.dao.ItemDao;

/** This class models a collection for two purposes:
 * 1. To model the logged users collections.
 * 2. To allow a user to choose a new collection. **/

public class Collection {

	private ObservableList<Item> items;
	private String catalogueName;
	private String catalogueDescription;
	private int id;
	
	/** This constructor builds the instances that will be stored in the user class
	 * in ObservableList observers
	 * @param collectionId
	 * @param catalogueName
	 * @param catalogueDescription
	 */
	public Collection(int collectionId, String catalogueName, String catalogueDescription) {
		this.id = collectionId;
		this.catalogueName = catalogueName;
		this.catalogueDescription = catalogueDescription;
		this.items = ItemDao.getItemsFromCollectionId(collectionId);

	}
	
	/** This constructor builds the instances that will be shown in the choose collection view
	 * 
	 * @param catalogueName
	 * @param catalogueDescription
	 * @param id
	 */
	public Collection (String catalogueName, String catalogueDescription, int id) {
		this.catalogueName = catalogueName;
		this.catalogueDescription = catalogueDescription;
		this.id = id;
	}
	
	public ObservableList<Item> getItems(){
		return items;
	}
	
	
	public String getCatalogueName() {
		return catalogueName;
	}
	
	public String getCatalogueDescription() {
		return catalogueDescription;
	}

	public int getId() {
		return id;
	}
	
}
