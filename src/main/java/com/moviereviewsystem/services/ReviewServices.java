/**
 * 
 */
package com.moviereviewsystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.moviereviewsystem.model.Review;

/**
 * @author shukumar5
 * @Date Feb 11, 2023
 * @fileName ReviewServices.java
 * @Description
 */
@Service
public interface ReviewServices {

	public Review saveReview(Review review);

	public List<Review> saveReviewAll(List<Review> review);

	public List<Review> getReviews();

	public Optional<Review> getReviewById(int reviewId);

	public void updateReview(Review review);

	public void deleteReview(Review review);

	public void deleteReviewById(int reviewId);

}
