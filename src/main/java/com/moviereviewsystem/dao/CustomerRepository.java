/**
 * 
 */
package com.moviereviewsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviereviewsystem.model.Customer;

/**
 * @author shukumar5
 * @Date Feb 11, 2023
 * @fileName MovieRepository.java
 * @Description
 */

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
