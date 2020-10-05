package fr.epita.web.service.data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import fr.epita.web.datamodel.Movie;

@Repository
public class PGSQLMovieDAO {
	
	String postsqlurl = "jdbc:postgresql://192.168.1.77:10532/postgres";
	public List<Movie> listAll(){
		
		List<Movie> movies = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM public.movie");) {
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Movie movie = new Movie();
			movie.setM_id(rs.getInt("m_id"));
			movie.setAuthor(rs.getString("author"));
			movie.setTitle(rs.getString("title"));
		    movie.setAdded(Date.valueOf(rs.getString("added")));
			movie.setExternal_id(rs.getString("external_id"));
			movies.add(movie);
		}
		while(rs.next()) {
			System.out.println(rs.getString("title"));
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return movies;	
		
	}
	
	public List<Movie> searchTitle(String m_title){
		List<Movie> movies = new ArrayList<>();
		String res = "[";
		//String sql = "SELECT * FROM public.movie where title = ? ";
		String sql = "SELECT m_id,title,to_char(added,'YYYY-MM-DD')as added,external_id ,author FROM public.movie "
				+ "where title = ? ";
		try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setString(1, m_title);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Movie movie = new Movie();
			movie.setM_id(rs.getInt("m_id"));
			movie.setAuthor(rs.getString("author"));
			movie.setTitle(rs.getString("title"));
		    movie.setAdded(Date.valueOf(rs.getString("added")));
			movie.setExternal_id(rs.getString("external_id"));
			movies.add(movie);
		}
		while(rs.next()) {
			System.out.println(rs.getString("title"));
		}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return movies;	
		
	}
	public void createMovie(String title, String added,String external_id,String author) {
	  // TODO Auto-generated method stub
	  String sql = "insert into public.movie (title,added,external_id,author) values (?,?,?,?)";
	  try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
	    PreparedStatement ps = connection.prepareStatement(sql);) { 
	   ps.setString(1, title);
	   if(added == null) {
		   System.out.println(added);
	   }
	   ps.setDate(2, java.sql.Date.valueOf(added));
	   ps.setString(3, external_id);
	   ps.setString(4, author);
	   ps.executeQuery();
	     } catch (Exception e) {
	       e.printStackTrace();
	     }
	    //return movie;
	 }
	public void updateMovie (int movieid ,String title, Date added, String external_id,String author)
	{
		Movie movie = new Movie();
		String sql ="update public.movie set title = ?, added = ?, external_id = ?, author = ? where m_id = ? ";
		 try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
				    PreparedStatement ps = connection.prepareStatement(sql);) { 
				   ps.setString(1,title); 
				   Timestamp ts = new Timestamp(added.getTime());
				   ps.setTimestamp(2, ts);
				   ps.setString(3, external_id);
				   ps.setString(4, author);
				   ps.setInt(5, movieid);
				   ps.executeQuery();
					
				     } catch (Exception e) {
				       e.printStackTrace();
				     }
	}
	public void deleteMovie(int movieid)
	{
		String sql = "delete from public.movie where m_id = ?";
		try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
			    PreparedStatement ps = connection.prepareStatement(sql);) { 
			   ps.setInt(1,movieid); 
			   ps.executeQuery();
			     } catch (Exception e) {
			       e.printStackTrace();
			     }
		
	}
	public Movie searchbyId(int movie_id){
		Movie movie = new Movie();
		String sql = "SELECT * FROM public.movie where m_id = ? ";
		try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setInt(1, movie_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				movie.setM_id(rs.getInt("m_id"));
				movie.setAuthor(rs.getString("author"));
				movie.setTitle(rs.getString("title"));
			    movie.setAdded(Date.valueOf(rs.getString("added")));
				movie.setExternal_id(rs.getString("external_id"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return movie;	
		
	}
	public List<Movie> listThree(){
		
		List<Movie> movies = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM public.movie order by added desc limit 4");) {
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Movie movie = new Movie();
			movie.setM_id(rs.getInt("m_id"));
			movie.setAuthor(rs.getString("author"));
			movie.setTitle(rs.getString("title"));
		    movie.setAdded(Date.valueOf(rs.getString("added")));
			movie.setExternal_id(rs.getString("external_id"));
			movies.add(movie);
		}
		while(rs.next()) {
			System.out.println(rs.getString("title"));
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return movies;	
		
	}
	public List<Movie> recomeMovie(String movie_id){
		
		List<Movie> movies = new ArrayList<>();
		//Movie movie = new Movie();
		System.out.println(movie_id);
		String sql = "SELECT * FROM public.movie where m_id in "+movie_id;
		try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			//pstmt.setString(1, movie_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setM_id(rs.getInt("m_id"));
				movie.setAuthor(rs.getString("author"));
				movie.setTitle(rs.getString("title"));
			    movie.setAdded(Date.valueOf(rs.getString("added")));
				movie.setExternal_id(rs.getString("external_id"));
				movies.add(movie);
			}
			while(rs.next()) {
				System.out.println(rs.getString("title"));
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return movies;	
		
	}
}