/**
 * 
 */
package com.moviereviewsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviereviewsystem.model.Review;

/**
 * @author shukumar5
 * @Date Feb 11, 2023
 * @fileName MovieRepository.java
 * @Description
 */

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
