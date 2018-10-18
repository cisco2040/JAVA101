package com.softtek.javaweb.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.softtek.javaweb.domain.model.ShipTo;
import com.softtek.javaweb.exception.impl.ResourceNotAddedException;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.exception.impl.ResourceNotDeletedException;
import com.softtek.javaweb.exception.impl.ResourceCouldNotBeFoundException;
import com.softtek.javaweb.exception.impl.ResourceNotUpdatedException;
import com.softtek.javaweb.service.jpa.ShipToService;

@RestController
@RequestMapping("/shipTos")
public class ShipToRestController {
	
	@Autowired
	ShipToService shipToService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ShipTo getOne(@PathVariable Long id) throws ResourceNotAvailableException {
		return shipToService.getOne(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<ShipTo> getList() throws ResourceNotAvailableException {
		return shipToService.getList();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	@ResponseStatus(HttpStatus.CREATED)
	public ShipTo add(@RequestBody ShipTo shipTo) throws ResourceNotAddedException {
		return shipToService.add(shipTo);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ShipTo updatePut(@RequestBody ShipTo shipTo, @PathVariable Long id) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		return shipToService.updateFull(shipTo, id);
	}
	
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ShipTo updatePatch(@RequestBody ShipTo shipTo, @PathVariable Long id) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		return shipToService.updatePartial(shipTo, id);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ShipTo delete(@PathVariable Long id) throws ResourceNotDeletedException {
		shipToService.delete(id);
		return null;
	}
}