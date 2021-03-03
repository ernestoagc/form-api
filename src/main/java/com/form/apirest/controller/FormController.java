package com.form.apirest.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.form.apirest.exception.ValidateException;
import com.form.apirest.model.Form;
import com.form.apirest.service.FormService;
import com.form.apirest.util.Constant;
import com.form.apirest.controller.BaseController;

import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="api/")
public class FormController extends BaseController{
Logger logger = LoggerFactory.getLogger(FormController.class);
	
	@Autowired
	FormService formService;
	
	@PostMapping("/")
	public ResponseEntity<?> saveForm(@RequestBody Form form){
		
		logger.info("===NEW FORM==== BEGIN ====");	   	
		
			if( StringUtils.isEmpty(form.getName()))  {
			
			throw new ValidateException(Constant.ERROR_CODE.REQUIRED_FIELDS,MessageFormat.format(Constant.ERROR_MESSAGE.REQUIRED_FIELDS,"name"));
		}
			
			logger.info("===NEW FORM==== BEGIN ====");	   				
			if( StringUtils.isEmpty(form.getEmail()))  {			
			throw new ValidateException(Constant.ERROR_CODE.REQUIRED_FIELDS,MessageFormat.format(Constant.ERROR_MESSAGE.REQUIRED_FIELDS,"email"));
		}
		
		
			if( StringUtils.isEmpty(form.getPosition()))  {
			
			throw new ValidateException(Constant.ERROR_CODE.REQUIRED_FIELDS,MessageFormat.format(Constant.ERROR_MESSAGE.REQUIRED_FIELDS,"position"));
		}
		
		Form newForm = formService.saveForm(form);
		logger.info("===NEW FORM==== END ====");
		return ResponseEntity.status(HttpStatus.OK).body(newForm);
	}
	
	@GetMapping("/{id}")	
	@ResponseBody
	public ResponseEntity<?> getDetail(@PathVariable Long id) throws Exception{

		logger.info("===GET FORM==== BEGIN ====");   		
		Form form =formService.findById(id);
		
		if(form==null) {
			throw new ValidateException(Constant.ERROR_CODE.NO_CONTENT, new Object[]{});			
		}
		
		logger.info("===GET FORM==== END ====");
		return ResponseEntity.status(HttpStatus.OK).body(form);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteForm(@PathVariable long id) 
	{
		logger.info("===DELETE FORM==== BEGIN ===="); 
		Form form =formService.findById(id);
		
		if(form==null) {
			
			throw new ValidateException(Constant.ERROR_CODE.BAD_REQUEST,"Application does not exits");
		}
		
		formService.deleteForm(form.getId());
		logger.info("===DELETE FORM==== END ====");
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/findall")	
	@ResponseBody
	public ResponseEntity<?> findall(@RequestParam Map<String,String> parametros) throws FileNotFoundException  {

		logger.info("===LIST FORM==== BEGIN ====");   		
		List<Form> FormList =formService.getAllForm();
		
		
		logger.info("===LIST FORM==== END ====");
		return ResponseEntity.status(HttpStatus.OK).body(FormList);
	}
	
	@GetMapping("/page/{page}")	
	@ResponseBody
	public ResponseEntity<?> findAllPage(@PathVariable int page,@RequestParam Map<String,String> parametros) throws FileNotFoundException  {
		
		
		logger.info("===LIST PAGE FORM==== BEGIN ====");		
		String positionParameter =parametros.get("position")==null?"":parametros.get("position");
		
		logger.info("positionParameter: "+ positionParameter);

		Page<Form> listPaginable= formService.getAllFormByPositionPagination(positionParameter, PageRequest.of(page, 15));
		logger.info("===LIST PAGE FORM==== END ====");   
		return ResponseEntity.status(HttpStatus.OK).body(listPaginable);
		
	}
}
