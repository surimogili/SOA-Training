package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Order;
import com.example.repository.OrderRepository;

@RestController
@ConfigurationProperties(prefix="newConfig")
@RefreshScope()
public class OrderAPI {
	
	@Autowired
	private OrderRepository manuRepo;
	
	private String data;
	
	@RequestMapping(value = "/api/order", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> add( @RequestBody Order manufacturer)
	{
		manuRepo.save(manufacturer);
		return new ResponseEntity<Order>(manufacturer, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/api/order/{id}", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> update( @RequestBody Order manufacturer, @PathVariable("id") Integer id)
	{
		Order manufactureOne = manuRepo.findOne(id);
		if(manufactureOne != null)
		{
			manufactureOne.setActive(manufacturer.isActive());
			manufactureOne.setOrderDate(manufacturer.getOrderDate());
			manufactureOne.setName(manufacturer.getName());
			manuRepo.save(manufactureOne);
		}
		return new ResponseEntity<Order>(manufacturer, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/order", method=RequestMethod.GET)
	public ResponseEntity<List<Order>> findAll()
	{
		List<Order> list = new ArrayList<>();
		list.add(new Order("localorder", 1, new Date(), true));
		return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
	}

}
