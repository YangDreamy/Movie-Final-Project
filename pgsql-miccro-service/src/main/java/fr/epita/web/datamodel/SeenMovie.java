package fr.epita.web.datamodel;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;




public class SeenMovie {
	private int user_id;
	private int movie_id;
	
	private Timestamp watch_date;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
	private Movie movie;
	
	public SeenMovie() {
		
	}
	public SeenMovie(Movie movie,Timestamp watch_date)
	{
		this.movie = movie;
		this.watch_date = watch_date;
	}
	public Timestamp getWatch_date() {
		return watch_date;
	}
	public void setWatch_date(Timestamp watch_date) {
		this.watch_date = watch_date;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie movie)
	{
		this.movie = movie;
	}
	public User getuser() {
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	

}
