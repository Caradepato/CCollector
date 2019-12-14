package pt.iade.cCollector.models;

public class userBook {
	private String description;
	private String publisher;
	private String language;
	private int publicationYear;
	private int bookNumber;
	
	public userBook(String description, String publisher, String language, int publicationYear, int bookNumber) {
		this.description = description;
		this.publisher = publisher;
		this.language = language;
		this.publicationYear = publicationYear;
		this.bookNumber = bookNumber;
	}
	
	public String getDescription() {
		return description;
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
	public String getSelectorText() {
		return "Book " + bookNumber;
	}

}
