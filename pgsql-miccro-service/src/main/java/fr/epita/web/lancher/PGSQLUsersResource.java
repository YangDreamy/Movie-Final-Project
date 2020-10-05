package fr.epita.web.lancher;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.epita.web.datamodel.Movie;
import fr.epita.web.datamodel.User;
import fr.epita.web.service.data.PGSQLUserDAO;



@RestController
@CrossOrigin(origins = {"http://localhost:4200", "null"})
@RequestMapping("/Users")
public class PGSQLUsersResource {

		@Inject
		PGSQLUserDAO dao;
		
		@GetMapping
		@RequestMapping("List")
		public List<User> listUsers(){
			System.out.println("successful");
			return dao.listAll();
			
		}
		//search movie title
		@RequestMapping("/")//http://localhost:20080/Movies/?title=test1
	    public List<User> getuserbyname(@RequestParam(value = "name", required = false) String name) {
	        System.out.println("name is  " + name);
	        return dao.searchname(name);
	        //return "redirect:/movies/getAll";
	    }
		
		@RequestMapping(value = "/adduser", method = RequestMethod.POST)
		public void createMovie(@ModelAttribute User user) {

			 String username = user.getUsername();
			dao.createUser(username);
		}
		@RequestMapping("Userinfo")
		public List<User> listUsersinfo(){
			System.out.println("successful");
			return dao.searchInfo();
			
		}

}
