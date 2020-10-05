package fr.epita.web.lancher;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import fr.epita.web.datamodel.Movie;
import fr.epita.web.datamodel.ReturnMovie;
import fr.epita.web.service.data.PGSQLMovieDAO;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "null"})
@RequestMapping("/Movies")
public class PGSQLMoviesResource {
	
	
	@Inject
	PGSQLMovieDAO dao;
	@GetMapping
	
	
	@RequestMapping("List")
	public List<Movie> listMovies(){
		System.out.println("successful");
		return dao.listAll();
		
		//return Response.ok(Arrays.asList(dao.listAll())).build();
	}
	
	//search movie title
	@RequestMapping("/")//http://localhost:20080/Movies/?title=test1
    public List<Movie> getmoviebytitle(@RequestParam(value = "title", required = false) String title) {
        System.out.println("title is  " + title);
        return dao.searchTitle(title);
        //return "redirect:/movies/getAll";
    }
	
	@RequestMapping("/searchId")//http://localhost:20080/Movies/searchId?m_id=4
    public Movie getmoviebyid(@RequestParam(value = "m_id", required = false) int movieid) {
        System.out.println("movieid is  " + movieid);
        return dao.searchbyId(movieid);
        //return "redirect:/movies/getAll";
    }

	@RequestMapping(value = "/addmovie", method = RequestMethod.POST)
	public void createMovie(@RequestBody ReturnMovie movie) {
		
		String title = movie.getTitle();
		 String added = movie.getAdded();
		 System.out.println(added);
		String author = movie.getAuthor();
		String external_id = movie.getExternal_id();
		dao.createMovie(title, added, external_id,author);
	}
	
	@RequestMapping(value = "/updatemovie", method = RequestMethod.POST)
	public void updateMovie(@RequestBody ReturnMovie movie) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		int updatem_id = movie.getM_id();
		String title = movie.getTitle();
		System.out.println(title);

		System.out.println(movie.getAdded());
		Date added = Date.valueOf(movie.getAdded());
		String author = movie.getAuthor();
		String external_id = movie.getExternal_id();
		dao.updateMovie(updatem_id,title, added, external_id,author);
		
	}
	@RequestMapping(value = "/deletemovie")
    public void deletemovie(@RequestParam(value = "movieid", required = false)  int movieid) {
        dao.deleteMovie(movieid);
        //return "redirect:/movies/getAll";
    }
	@RequestMapping("welcome")
	public List<Movie> listThree(){
		System.out.println("successful");
		return dao.listThree();
	}
	@RequestMapping("welcome/recommand")
	public List<Movie> RecommondMovie(@RequestParam(value = "movieidList", required = false) String movieidList){
		System.out.println(movieidList);
		return dao.recomeMovie(movieidList);
	}
	
}
