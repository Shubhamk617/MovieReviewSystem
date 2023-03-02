package com.moviereviewsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private int reviewId;

	@Column(name = "review_desc")
	private String reviewDesc;

	@Column(name = "review_likes")
	private int likes;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "review_movieId", referencedColumnName = "movie_Id")
	@JsonBackReference(value = "review_movie")
	private Movie movie;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "review_customerId", referencedColumnName = "cust_id")
	@JsonManagedReference(value = "review_cust")
	private Customer customer;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(String reviewDesc, int likes, Movie movie, Customer customer) {
		super();
		this.reviewDesc = reviewDesc;
		this.likes = likes;
		this.movie = movie;
		this.customer = customer;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewDesc() {
		return reviewDesc;
	}

	public void setReviewDesc(String reviewDesc) {
		this.reviewDesc = reviewDesc;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
