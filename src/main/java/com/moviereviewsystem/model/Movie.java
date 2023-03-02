package com.moviereviewsystem.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * @author shukumar5
 * @Date Feb 11, 2023
 * @fileName Movie.java
 * @Description
 */

@Entity
@Table(name = "Movie")
//@JsonFilter("movieBeanFilters")
public class Movie /* extends RepresentationModel<Movie> */ {

	@Id
	@Column(name = "movie_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;

	@Column(name = "movie_Name")
	@NotNull(message = "Field can't be null")
	@Size(min = 3, max = 30, message = "Minimum Size required is 5 and Maximum size can be 30")
	private String movieName;

	@Column(name = "release_Date")
	@PastOrPresent(message = "Release Date can't be in future, please provide already released movies")
	private Date releaseDate;

	@Column(name = "running_Time_In_Minutes")
	@Min(value = 60, message = "runningTimeInMinutes should not be less than 60")
	@Max(value = 200, message = "runningTimeInMinutes should not be greater than 200")
	private int runningTimeInMinutes;

	@Column(name = "country")
	private String country;

	@Column(name = "language")
	private String language;

	@Column(name = "budget_In_Crores")
	@Positive
	private double budgetInCrores;

	@Column(name = "box_Office_Collection")
	@PositiveOrZero
	private double boxOfficeCollectionInCrores;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "review_movie")
	private List<Review> review;

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movie(
			@NotNull(message = "Field can't be null") @Size(min = 3, max = 30, message = "Minimum Size required is 5 and Maximum size can be 30") String movieName,
			@PastOrPresent(message = "Release Date can't be in future, please provide already released movies") Date releaseDate,
			@Min(value = 60, message = "runningTimeInMinutes should not be less than 60") @Max(value = 200, message = "runningTimeInMinutes should not be greater than 200") int runningTimeInMinutes,
			String country, String language, @Positive double budgetInCrores,
			@PositiveOrZero double boxOfficeCollectionInCrores, List<Review> review) {
		super();
		this.movieName = movieName;
		this.releaseDate = releaseDate;
		this.runningTimeInMinutes = runningTimeInMinutes;
		this.country = country;
		this.language = language;
		this.budgetInCrores = budgetInCrores;
		this.boxOfficeCollectionInCrores = boxOfficeCollectionInCrores;
		this.review = review;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getRunningTimeInMinutes() {
		return runningTimeInMinutes;
	}

	public void setRunningTimeInMinutes(int runningTimeInMinutes) {
		this.runningTimeInMinutes = runningTimeInMinutes;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public double getBudgetInCrores() {
		return budgetInCrores;
	}

	public void setBudgetInCrores(double budgetInCrores) {
		this.budgetInCrores = budgetInCrores;
	}

	public double getBoxOfficeCollectionInCrores() {
		return boxOfficeCollectionInCrores;
	}

	public void setBoxOfficeCollectionInCrores(double boxOfficeCollectionInCrores) {
		this.boxOfficeCollectionInCrores = boxOfficeCollectionInCrores;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

}
