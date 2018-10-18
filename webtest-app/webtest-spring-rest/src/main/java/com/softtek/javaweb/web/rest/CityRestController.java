package com.softtek.javaweb.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.softtek.javaweb.domain.model.City;
import com.softtek.javaweb.exception.impl.OperationNotSupportedException;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.service.jpa.CityService;

@RestController
@RequestMapping("/cities")
public class CityRestController {
	
	@Autowired
	CityService cityService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public City getOne(@PathVariable Long id) throws ResourceNotAvailableException {
		return cityService.getOne(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<City> getList() throws ResourceNotAvailableException {
		return cityService.getList();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	@ResponseStatus(HttpStatus.CREATED)
	public City add(@RequestBody City city) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("POST operation not supported for City resource");
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updatePut(@RequestBody City city, @PathVariable Long id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("PUT operation not supported for City resource");
	}
	
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updatePatch(@RequestBody City city, @PathVariable Long id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("PATCH operation not supported for City resource");
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("DELETE operation not supported for City resource");
	}
}