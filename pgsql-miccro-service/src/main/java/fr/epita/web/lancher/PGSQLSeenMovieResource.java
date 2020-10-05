package fr.epita.web.lancher;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.epita.web.datamodel.Movie;
import fr.epita.web.datamodel.ReturnMovie;
import fr.epita.web.datamodel.SeenMovie;
import fr.epita.web.service.data.SeenMovieDAO;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "null"})
@RequestMapping("/SeenMovie")
public class PGSQLSeenMovieResource {
	@Inject
	SeenMovieDAO dao;
	
	@GetMapping
	@RequestMapping("List")
	public List<SeenMovie> listUsers(){
		System.out.println("successful");
		return dao.listAll();
		
	}
	
	@RequestMapping("/")//http://localhost:20080/SeenMovie/?userid=1
    public List<SeenMovie> findSeenMovie(@RequestParam( required = false) int userid) {
        System.out.println("userid is  " + userid);
        return dao.findSeenMovie_uid(userid);
        //return "redirect:/movies/getAll";
    }
	@RequestMapping(value = "/addseenmovie", method = RequestMethod.POST)
	public void createMovie(@RequestBody SeenMovie seenmovie) {
		int user_id = seenmovie.getUser_id();
		int movie_id = seenmovie.getMovie_id();
		Timestamp watch_date = new Timestamp(System.currentTimeMillis()); 
		dao.createSeenMovie(user_id, movie_id, watch_date);
	}
	@RequestMapping("popular")
	public List<Movie> listMovies(){
		System.out.println("successful");
		return dao.ListPopular();
		
	}

}
