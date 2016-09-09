package com.example;

import java.net.URI;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.model.Order;

@RestController
public class ConsumerService {
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value = "/orderPost", method = RequestMethod.POST)
	public ResponseEntity<String> postData()
	{
		List<ServiceInstance> list = client.getInstances("order-microservice");
		if(list!= null && list.size()>0)
		{
			URI uri = list.get(0).getUri();
			if(uri != null)
			{
				Order order1 = new Order("Order1", 1, new Date(), true);
				Order order2 = new Order("Order2", 2, new Date(), true);
				List<Order> orderList = new LinkedList<>();
				orderList.add(order1);
				orderList.add(order2);
				return (new RestTemplate()).postForEntity(uri, orderList.get(0), String.class);
			}
		}
		return null;
	}
	
	@RequestMapping("/order")
	public List<Order> getData()
	{
		List<ServiceInstance> list = client.getInstances("order-microservice");
		if(list!= null && list.size()>0)
		{
			URI uri = list.get(0).getUri();
			if(uri != null)
			{
				return (new RestTemplate()).getForObject(uri, List.class);
			}
		}
		return null;
	}

}
