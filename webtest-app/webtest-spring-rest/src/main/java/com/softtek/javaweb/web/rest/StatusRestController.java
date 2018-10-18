package com.softtek.javaweb.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.softtek.javaweb.domain.model.Status;
import com.softtek.javaweb.exception.impl.OperationNotSupportedException;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.service.jpa.StatusService;

@RestController
@RequestMapping("/statuses")
public class StatusRestController {
	
	@Autowired
	StatusService statusService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Status getOne(@PathVariable Long id) throws ResourceNotAvailableException {
		return statusService.getOne(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Status> getList() throws ResourceNotAvailableException {
		return statusService.getList();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	@ResponseStatus(HttpStatus.CREATED)
	public Status add(@RequestBody Status status) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("POST operation not supported for Status resource");
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updatePut(@RequestBody Status status, @PathVariable Long id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("PUT operation not supported for Status resource");
	}
	
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updatePatch(@RequestBody Status status, @PathVariable Long id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("PATCH operation not supported for Status resource");
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("DELETE operation not supported for Status resource");
	}
}