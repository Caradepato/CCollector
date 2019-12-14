package pt.iade.cCollector.models;

import javafx.collections.ObservableList;
import pt.iade.models.dao.CCollectorDao;

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
		this.catalogueName = catalogueName;
		this.catalogueDescription = catalogueDescription;
		this.items = CCollectorDao.getItemsFromCollectionId(collectionId);

	}
	
	/** This constructor builds the instances that will be chone in the choose collection view
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