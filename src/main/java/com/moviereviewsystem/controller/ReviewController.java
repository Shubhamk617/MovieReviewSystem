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
import com.moviereviewsystem.exceptions.ReviewNotFoundException;
import com.moviereviewsystem.model.Review;
import com.moviereviewsystem.services.ReviewServices;

import jakarta.validation.Valid;

/**
 * @author shukumar5
 * @Date Feb 11, 2023
 * @fileName ReviewController.java
 * @Description
 */
@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewServices reviewServices;

	@GetMapping("/getAllReviews")
	private List<Review> getAllReviews() {
		// TODO Auto-generated method stub
		return reviewServices.getReviews();
	}

	@GetMapping("/getAllFilteredDataReviews")
	private MappingJacksonValue getAllFilteredDataReviews() {
		// TODO Auto-generated method stub
		List<Review> reviews = reviewServices.getReviews();

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(reviews);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("reviewName", "budgetInCrores",
				"boxOfficeCollectionInCrores");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("reviewBeanFilters", filter);
		mappingJacksonValue.setFilters(filterProvider);

		return mappingJacksonValue;
	}

	@GetMapping("/getReviewById")
	private Optional<Review> getReviewById(@RequestParam int reviewId) {
		// TODO Auto-generated method stub
		Optional<Review> review = reviewServices.getReviewById(reviewId);

		if (review.isEmpty())
			throw new ReviewNotFoundException("Review with ID:" + reviewId + " does not exist.");

		return review;

	}

	@GetMapping("/getReviewByIdPathVar/{reviewId}")
	private Optional<Review> getReviewByIdPathVar(@PathVariable int reviewId) {
		// TODO Auto-generated method stub
		Optional<Review> review = reviewServices.getReviewById(reviewId);

		if (review.isEmpty())
			throw new ReviewNotFoundException("Review with ID:" + reviewId + " does not exist.");

		return review;
	}

	@PostMapping("/saveReview")
	private ResponseEntity<Review> saveReview(@Valid @RequestBody Review review) {
		// TODO Auto-generated method stub
		Review savedReview = reviewServices.saveReview(review);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedReview.getReviewId()).toUri();
		return ResponseEntity.created(location).build();
		// reviewServices.saveReview(review);
	}

	@PostMapping("/saveAllReviews")
	private ResponseEntity<List<Review>> saveAllReviews(@RequestBody List<Review> review) {
		// TODO Auto-generated method stub
		List<Review> savedReview = reviewServices.saveReviewAll(review);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedReview)
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/updateReview")
	private void updateReview(@RequestBody Review review) {
		// TODO Auto-generated method stub
		Optional<Review> existingReview = reviewServices.getReviewById(review.getReviewId());
		if (existingReview.isEmpty())
			throw new ReviewNotFoundException("Review with ID:" + review.getReviewId() + " does not exist.");

		reviewServices.saveReview(review);
	}

	@DeleteMapping("/deleteReview")
	private void deleteReview(@RequestBody Review review) {
		// TODO Auto-generated method stub
		Optional<Review> existingReview = reviewServices.getReviewById(review.getReviewId());
		if (existingReview.isEmpty())
			throw new ReviewNotFoundException("Review with ID:" + review.getReviewId() + " does not exist.");
		reviewServices.deleteReview(review);
	}

	@DeleteMapping("/deleteReviewById")
	private void deleteReview(@RequestParam int reviewId) {
		// TODO Auto-generated method stub
		Optional<Review> existingReview = reviewServices.getReviewById(reviewId);
		if (existingReview.isEmpty())
			throw new ReviewNotFoundException("Review with ID:" + reviewId + " does not exist.");
		reviewServices.deleteReviewById(reviewId);
	}

}
