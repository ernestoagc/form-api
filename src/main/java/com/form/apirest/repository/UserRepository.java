package com.form.apirest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.form.apirest.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{
	public User findByUserCode(String userCode);
}
