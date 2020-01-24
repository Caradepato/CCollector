package pt.iade.cCollector.models;

/** This class models the editions that a book has. **/

public class Edition {

	private String publisher;
	private String language;
	private int publicationYear;
	private int id;
	
	public Edition(String publisher, String language, int publicationYear, int id) {
		this.publisher = publisher;
		this.language = language;
		this.publicationYear = publicationYear;
		this.id = id;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public int getPublicationYear() {
		return publicationYear;
	}
	
	public int getId() {
		return id;
	}
	
	/**
	 * Returns a string for the purpose of populating the choice box that lists the editions that a user may choose for a new book
	 */
	
	public String toString() {
		return publisher + " " + language + " " + publicationYear;
	}
}
