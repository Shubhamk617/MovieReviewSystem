/**
 * 
 */
package com.moviereviewsystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviereviewsystem.dao.ReviewRepository;
import com.moviereviewsystem.model.Review;

/**
 * @author shukumar5
 * @Date Feb 11, 2023
 * @fileName ReviewServicesImpl.java
 * @Description
 */
@Service
public class ReviewServicesImpl implements ReviewServices {

	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public Review saveReview(Review review) {
		// TODO Auto-generated method stub
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getReviews() {
		// TODO Auto-generated method stub
		return reviewRepository.findAll();
	}

	@Override
	public Optional<Review> getReviewById(int reviewId) {
		// TODO Auto-generated method stub
		return reviewRepository.findById(reviewId);
	}

	@Override
	public void updateReview(Review review) {
		// TODO Auto-generated method stub
		reviewRepository.save(review);

	}

	@Override
	public void deleteReview(Review review) {
		// TODO Auto-generated method stub
		reviewRepository.delete(review);

	}

	@Override
	public void deleteReviewById(int reviewId) {
		// TODO Auto-generated method stub
		reviewRepository.deleteById(reviewId);
	}

	@Override
	public List<Review> saveReviewAll(List<Review> review) {
		// TODO Auto-generated method stub
		return reviewRepository.saveAll(review);
	}

}
