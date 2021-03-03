package com.form.apirest.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.form.apirest.model.*;
import com.form.apirest.repository.FormRepository;
import com.form.apirest.service.FormService;

@Service
public class FormServiceImpl implements FormService{
	
	@Autowired
	FormRepository formRepository;
	
	
	public Form saveForm(Form form) {
		Form FormNew = formRepository.save(form);
		return FormNew;
	}

	@Override
	public List<Form> getAllForm() {
		// TODO Auto-generated method stub
		return ((List<Form>)formRepository.findAll());
	}


	@Override
	public Page<Form> getAllFormPagination(Pageable pageable) {
		// TODO Auto-generated method stub
		return  formRepository.findAll(pageable);
	}
	
	@Override
	public Page<Form> getAllFormByPositionPagination(String  position,Pageable pageable) {
		// TODO Auto-generated method stub
		return  formRepository.getAllFormByPosition(position,pageable);
	}
	
	
	@Override
	public Form findById(long id) {
		return  formRepository.findById(id).orElse(null);
	}
	
	
	
	@Override
	public boolean deleteForm(Long id) {
		
		try {
			formRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	
}
