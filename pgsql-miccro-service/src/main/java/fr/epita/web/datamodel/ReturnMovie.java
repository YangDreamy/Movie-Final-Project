package fr.epita.web.datamodel;

//import java.sql.Timestamp;


//import java.time.LocalString;


public class ReturnMovie {
	
	private int m_id;
	
	private String title;
	private String added;
	//private Timestamp added;
	//@StringTimeFormat(pattern = "yyyy-MM-dd")
	//private String added;
	/*public LocalString setString(String inputString) {
	    StringTimeFormatter formatter = new StringTimeFormatterBuilder()
	            .parseCaseInsensitive()
	            .appendPattern("yyyy-MM-d")
	            .toFormatter(Locale.ENGLISH);
	    formatter = formatter.withLocale(Locale.ENGLISH);
	    LocalString outputString = LocalString.parse(inputString, formatter);
	    return outputString;

	}*/
	private String external_id;
	
	private String author;
	
	public ReturnMovie() {
	}
	public ReturnMovie(int m_id,String title, String added,String external_id,String author)
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

	public String getAdded() {
		return added;
	}
	

	public void setAdded(String added) {
		//this.added = setString(timestamp);
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
		return "Movie [title = " + title + " author " + author +" added time = "+ added + "external_id = "+ external_id +"]";
		
	}


	
	
	

}
