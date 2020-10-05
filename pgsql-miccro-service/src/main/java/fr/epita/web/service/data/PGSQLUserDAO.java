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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.epita.web.datamodel.Address;
import fr.epita.web.datamodel.Contact;
import fr.epita.web.datamodel.Movie;
import fr.epita.web.datamodel.User;
@Repository
public class PGSQLUserDAO {
	String postsqlurl = "jdbc:postgresql://192.168.1.77:10532/postgres";
	public List<User> listAll(){
			
			List<User> users = new ArrayList<>();
			try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
					PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM public.users");) {
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setU_id(rs.getInt("u_id"));
				user.setUsername(rs.getString("username"));
				
				users.add(user);
			}
			while(rs.next()) {
				System.out.println(rs.getString("u_id"));
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return users;	
			
		}
	public List<User> searchname(String name){
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM public.users where username = ? ";
		try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			User user = new User();
			user.setU_id(rs.getInt("u_id"));
			users.add(user);
		}
		while(rs.next()) {
			System.out.println(rs.getString("name"));
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return users;	
		
	}
	public void createUser(String username) {
		  // TODO Auto-generated method stub
		  String sql = "insert into public.users (username) values (?)";
		  try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
		    PreparedStatement ps = connection.prepareStatement(sql);) { 
		   ps.setString(1, username); 
		   ps.executeQuery();
		     } catch (Exception e) {
		       e.printStackTrace();
		     }
		    //return movie;
		 }
	public List<User>  searchInfo() {
		List<User> users = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(postsqlurl, "postgres","postgres");
				PreparedStatement pstmt = connection.prepareStatement("select u.u_id,u.username,c.birthdate,c.gender,c.email,a.country,"
						+ "a.area,a.street,\r\n" + 
						"a.number,a.city\r\n" + 
						"from users u inner join contact c\r\n" + 
						"on c.user_id = u.u_id\r\n" + 
						"inner join address a \r\n" + 
						"on a.c_id= c.contract_id");) {
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			User user = new User();
			Contact contact = new Contact();
			Address address = new Address();
			user.setU_id(rs.getInt("u_id"));
			user.setUsername(rs.getString("username"));
			contact.setBirthdate(rs.getDate("birthdate"));
			contact.setGender(rs.getString("gender"));
			contact.setEmail(rs.getString("email"));
			address.setCountry(rs.getString("country"));
			address.setArea(rs.getString("area"));
			address.setStreet(rs.getString("street"));
			address.setCity(rs.getString("city"));
			user.setContact(contact);
			user.setAddress(address);
			users.add(user);
		}
		while(rs.next()) {
			System.out.println(rs.getString("u_id"));
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return users;	
	}

}
