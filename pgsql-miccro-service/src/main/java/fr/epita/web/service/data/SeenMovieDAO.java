package fr.epita.web.service.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import fr.epita.web.datamodel.Movie;
import fr.epita.web.datamodel.SeenMovie;
import fr.epita.web.datamodel.User;

@Repository
public class SeenMovieDAO {

	String postsqlurl = "jdbc:postgresql://192.168.1.77:10532/postgres";
	public List<SeenMovie> listAll(){
		String sql = "select  * from seenmovie \r\n" + 
				"inner join users on users.u_id = seenMovie.user_id\r\n" + 
				"inner join movie on movie.m_id = seenmovie.movie_id order by watch_date desc";
		List<SeenMovie> seenmovies = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
		
		ResultSet rs = pstmt.executeQuery();
		System.out.println(rs.toString());
		while(rs.next()) {
			SeenMovie s_movies = new SeenMovie();
			User user = new User();
			Movie movie = new Movie();
			user.setU_id(rs.getInt("u_id"));
			user.setUsername(rs.getString("username"));
			movie.setM_id(rs.getInt("movie_id"));
			movie.setAuthor(rs.getString("author"));
			movie.setTitle(rs.getString("title"));
		    movie.setAdded(Date.valueOf(rs.getString("added")));
			movie.setExternal_id(rs.getString("external_id"));
			s_movies.setUser_id(rs.getInt("user_id"));
			s_movies.setMovie_id(rs.getInt("movie_id"));
			s_movies.setWatch_date(rs.getTimestamp("watch_date"));
			s_movies.setUser(user);
			s_movies.setMovie(movie);
			
			seenmovies.add(s_movies);
		}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return seenmovies;	
			
		
	}
//according to userid to search information
	public List<SeenMovie> findSeenMovie_uid(int userid) {
		// TODO Auto-generated method stub
		List<SeenMovie> smovies = new ArrayList<>();
		String sql = "SELECT DISTINCT seenmovie.movie_id,m.title, seenmovie.watch_date, m.external_id,m.added, m.author\r\n" + 
				"FROM seenmovie\r\n" + 
				"INNER JOIN\r\n" + 
				"   movie m ON seenmovie.movie_id = m.m_id\r\n" + 
				" WHERE seenmovie.movie_id in \r\n" + 
				"    (\r\n" + 
				"\r\n" + 
				"          SELECT movie_id\r\n" + 
				"\r\n" + 
				"          FROM seenmovie\r\n" + 
				"\r\n" + 
				"          WHERE user_id = ?) order by watch_date desc";
		//String sql = "select * from public.seenmovie where user_id =?" ;
				//+ " and (seenmovie.movie_id=? or ?=0) ";
		try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setInt(1, userid);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			SeenMovie s_movie = new SeenMovie();
			s_movie.setUser_id(userid);
			Movie movie = new Movie();
			movie.setM_id(rs.getInt("movie_id"));
			
			movie.setAuthor(rs.getString("author"));
			movie.setTitle(rs.getString("title"));
		    movie.setAdded(Date.valueOf(rs.getString("added")));
			movie.setExternal_id(rs.getString("external_id"));
			//s_movie.setUser_id(rs.getInt("user_id"));
			s_movie.setMovie_id(rs.getInt("movie_id"));
			s_movie.setWatch_date(rs.getTimestamp("watch_date"));
			//System.out.println(rs.getInt("movie_id"));
			 s_movie.setMovie(movie);
			smovies.add(s_movie);
		}
		/*ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
			String columnName = rsmd.getColumnName(i).toLowerCase();
			System.out.println(columnName);
		}*/
			

		}catch(Exception e) {
			e.printStackTrace();
		}

		return smovies;	
	}
	
	/*@RequestMapping(value = "/addseen", method = RequestMethod.POST)
	public void createMovie(@ModelAttribute SeenMovie seenmovie) {

		 String username = user.getUsername();
		dao.createUser(username);
	}*/
	public void createSeenMovie(int userid, int movieid, Timestamp watch_date) {
		  // TODO Auto-generated method stub
		  String sql = "insert into public.seenmovie (user_id,movie_id,watch_date) values (?,?,?)";
		  try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
		    PreparedStatement ps = connection.prepareStatement(sql);) { 
		   ps.setInt(1, userid);
		   ps.setInt(2, movieid);
		  
		   ps.setTimestamp(3, watch_date);
		  
		   ps.executeQuery();
		     } catch (Exception e) {
		       e.printStackTrace();
		     }
		    //return movie;
		 }
	public List<Movie> ListPopular(){
		String sql = "select m_id,title,author, external_id,added from \"movie\" \r\n" + 
				"left join \"seenmovie\" on m_id = movie_id\r\n" + 
				"group by m_id\r\n" + 
				"order by count(watch_date) desc limit 9";
		List<Movie> movies = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
		
		ResultSet rs = pstmt.executeQuery();
		System.out.println(rs.toString());
		while(rs.next()) {
			Movie movie = new Movie();
			movie.setM_id(rs.getInt("m_id"));
			movie.setAuthor(rs.getString("author"));
			movie.setTitle(rs.getString("title"));
		    movie.setAdded(Date.valueOf(rs.getString("added")));
			movie.setExternal_id(rs.getString("external_id"));
			
			movies.add(movie);
		}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return movies;	
			
		
	}
}
