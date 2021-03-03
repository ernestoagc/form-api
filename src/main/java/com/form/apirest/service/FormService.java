package com.form.apirest.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.form.apirest.model.*;


public interface FormService {
	public Form saveForm(Form form);	
	public List<Form> getAllForm();	
	public Page<Form> getAllFormPagination(Pageable pageable);
	public Page<Form> getAllFormByPositionPagination(String  position,Pageable pageable);
	public Form findById(long id);
	public boolean deleteForm(Long id) ;
}
