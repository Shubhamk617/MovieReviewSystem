/**
 * 
 */
package com.moviereviewsystem.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.moviereviewsystem.exceptions.MovieNotFoundException;
import com.moviereviewsystem.model.Movie;
import com.moviereviewsystem.services.MovieServices;

import jakarta.validation.Valid;

/**
 * @author shukumar5
 * @Date Feb 11, 2023
 * @fileName MovieController.java
 * @Description
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	MovieServices movieServices;

	
	@GetMapping("/getAllMovies")
	private List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return movieServices.getMovies();
	}

	@GetMapping("/getAllFilteredDataMovies")
	private MappingJacksonValue getAllFilteredDataMovies() {
		// TODO Auto-generated method stub
		List<Movie> movies = movieServices.getMovies();

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(movies);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("movieName", "budgetInCrores",
				"boxOfficeCollectionInCrores");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("movieBeanFilters", filter);
		mappingJacksonValue.setFilters(filterProvider);

		return mappingJacksonValue;
	}

	@GetMapping("/getMovieById")
	private Optional<Movie> getMovieById(@RequestParam int movieId) {
		// TODO Auto-generated method stub
		Optional<Movie> movie = movieServices.getMovieById(movieId);

		if (movie.isEmpty())
			throw new MovieNotFoundException("Movie with ID:" + movieId + " does not exist.");

		return movie;

	}

	@GetMapping("/getMovieByIdPathVar/{movieId}")
	private Optional<Movie> getMovieByIdPathVar(@PathVariable int movieId) {
		// TODO Auto-generated method stub
		Optional<Movie> movie = movieServices.getMovieById(movieId);

		if (movie.isEmpty())
			throw new MovieNotFoundException("Movie with ID:" + movieId + " does not exist.");

		return movie;
	}

	@PostMapping("/saveMovie")
	private ResponseEntity<Movie> saveMovie(@Valid @RequestBody Movie movie) {
		// TODO Auto-generated method stub
		Movie savedMovie = movieServices.saveMovie(movie);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedMovie.getId()).toUri();
		return ResponseEntity.created(location).build();
		// movieServices.saveMovie(movie);
	}

	@PostMapping("/saveAllMovies")
	private ResponseEntity<List<Movie>> saveAllMovies(@RequestBody List<Movie> movie) {
		// TODO Auto-generated method stub
		List<Movie> savedMovie = movieServices.saveMovieAll(movie);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedMovie)
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/updateMovie")
	private void updateMovie(@RequestBody Movie movie) {
		// TODO Auto-generated method stub
		Optional<Movie> existingMovie = movieServices.getMovieById(movie.getId());
		if (existingMovie.isEmpty())
			throw new MovieNotFoundException("Movie with ID:" + movie.getId() + " does not exist.");

		movieServices.saveMovie(movie);
	}

	@DeleteMapping("/deleteMovie")
	private void deleteMovie(@RequestBody Movie movie) {
		// TODO Auto-generated method stub
		Optional<Movie> existingMovie = movieServices.getMovieById(movie.getId());
		if (existingMovie.isEmpty())
			throw new MovieNotFoundException("Movie with ID:" + movie.getId() + " does not exist.");
		movieServices.deleteMovie(movie);
	}

	@DeleteMapping("/deleteMovieById")
	private void deleteMovie(@RequestParam int movieId) {
		// TODO Auto-generated method stub
		Optional<Movie> existingMovie = movieServices.getMovieById(movieId);
		if (existingMovie.isEmpty())
			throw new MovieNotFoundException("Movie with ID:" + movieId + " does not exist.");
		movieServices.deleteMovieById(movieId);
	}
	
	

}
