package pt.iade.cCollector.models;

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
	
	public String toString() {
		return publisher + " " + language + " " + publicationYear;
	}
}
