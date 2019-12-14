package pt.iade.cCollector.models;

import java.util.Date;

public class Message {
	private String from;
	private String to;
	private String contents;
	private Date date;
	
	public Message(String from,String to,String contents){
		this.from = from;
		this.to = to;
		this.contents = contents;
	}
}
