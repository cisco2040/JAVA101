package com.softtek.javaweb.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.softtek.javaweb.domain.model.State;
import com.softtek.javaweb.exception.impl.OperationNotSupportedException;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.service.jpa.StateService;

@RestController
@RequestMapping("/states")
public class StateRestController {
	
	@Autowired
	StateService stateService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public State getOne(@PathVariable Long id) throws ResourceNotAvailableException {
		return stateService.getOne(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<State> getList() throws ResourceNotAvailableException {
		return stateService.getList();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	@ResponseStatus(HttpStatus.CREATED)
	public State add(@RequestBody State state) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("POST operation not supported for State resource");
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updatePut(@RequestBody State state, @PathVariable Long id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("PUT operation not supported for State resource");
	}
	
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updatePatch(@RequestBody State state, @PathVariable Long id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("PATCH operation not supported for State resource");
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("DELETE operation not supported for State resource");
	}
}