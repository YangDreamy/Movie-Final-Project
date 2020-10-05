package fr.epita.web.datamodel;

import java.sql.Date;
//import java.sql.Timestamp;


//import java.time.LocalDate;


public class Movie {
	
	private int m_id;
	
	private String title;
	private Date added;
	//private Timestamp added;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	//private Date added;
	/*public LocalDate setDate(String inputDate) {
	    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
	            .parseCaseInsensitive()
	            .appendPattern("yyyy-MM-d")
	            .toFormatter(Locale.ENGLISH);
	    formatter = formatter.withLocale(Locale.ENGLISH);
	    LocalDate outputDate = LocalDate.parse(inputDate, formatter);
	    return outputDate;

	}*/
	private String external_id;
	
	private String author;
	
	public Movie() {
	}
	public Movie(int m_id,String title, Date added,String external_id,String author)
	{
		this.m_id = m_id;
		this.title=title;
		this.added = added;
		this.external_id = external_id;
		this.author = author;
	}
	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getAdded() {
		return added;
	}
	

	public void setAdded(Date added) {
		//this.added = setDate(timestamp);
		this.added = added;
	}
	public String getExternal_id() {
		return external_id;
	}

	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String toString() {
		return "{'title' = '" + title + "', 'author' = '" + author +"', 'added' = '"+ added + "', 'external_id' = '"+ external_id +"'}";
		
	}


	
	
	

}
