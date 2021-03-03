package com.form.apirest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.form.apirest.model.Role;

public interface RoleRepository  extends PagingAndSortingRepository<Role, Long>{

}
