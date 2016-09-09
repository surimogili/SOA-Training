package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Manufacturer;
import com.example.repository.ManufacturerRepository;

@RestController
public class ManufacturerAPI {
	
	@Autowired
	private ManufacturerRepository manuRepo;
	
	@Autowired
	private OrderClient orderClient;
	
	@RequestMapping(value = "/api/manufacturer", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manufacturer> add( @RequestBody Manufacturer manufacturer)
	{
		manuRepo.save(manufacturer);
		return new ResponseEntity<Manufacturer>(manufacturer, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/api/manufacturer/{id}", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manufacturer> update( @RequestBody Manufacturer manufacturer, @PathVariable("id") Integer id)
	{
		Manufacturer manufactureOne = manuRepo.findOne(id);
		if(manufactureOne != null)
		{
			manufactureOne.setActive(manufacturer.isActive());
			manufactureOne.setFoundedDate(manufacturer.getFoundedDate());
			manufactureOne.setName(manufacturer.getName());
			manuRepo.save(manufactureOne);
		}
		return new ResponseEntity<Manufacturer>(manufacturer, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/manufacturer", method=RequestMethod.GET)
	public ResponseEntity<List<Manufacturer>> findAll()
	{
		return new ResponseEntity<List<Manufacturer>>(manuRepo.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping("/")
	@ResponseBody
	public List<Order> findAllOrders()
	{
		return orderClient.findAll();
	}

}
