package com.renthouse.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renthouse.model.*;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	public User findByUsername(String username);
}
