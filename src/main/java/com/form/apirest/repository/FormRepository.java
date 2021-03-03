package com.form.apirest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.form.apirest.model.Form;

public interface FormRepository extends PagingAndSortingRepository<Form, Long>{

	@Query("select r from Form r")
	public Page<Form> getAllApplicationForm(Pageable pageable);
	
	@Query("select r from Form r where r.position like %?1%")
	public Page<Form> getAllFormByPosition(String  position, Pageable pageable);
}
