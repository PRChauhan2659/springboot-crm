package com.parth.springboot.crm.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.parth.springboot.crm.entity.Customer;

@Service
public class CustomerServiceRestImpl implements CustomerService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${crm.rest.url}")
	private String crmRestUrl;
	
	private Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public List<Customer> getCustomers() {
		
		ResponseEntity<List<Customer>> responseEntity
						= restTemplate.exchange(crmRestUrl, 
												HttpMethod.GET, 
												null, 
												new ParameterizedTypeReference<List<Customer>>() {});
		
		List<Customer> customers = responseEntity.getBody();
		
		logger.info("=====>> Customers : " + customers);
		
		return customers;
		
	}

	@Override
	public Customer getCustomer(int customerId) {
		
		Customer customer = restTemplate.getForObject(crmRestUrl + "/" + customerId, Customer.class);
		
		logger.info("=====>> Customer : " + customer);
		
		return customer;
		
	}

	@Override
	public void addCustomer(Customer customer) {

		int customerId = customer.getId();
		
		if(customerId == 0) {
			restTemplate.postForEntity(crmRestUrl, customer, String.class);
		}
		else {
			restTemplate.put(crmRestUrl, customer);
		}
		
		logger.info("=====>> Customer added.");
		
	}

	@Override
	public void deleteCustomer(int customerId) {

		restTemplate.delete(crmRestUrl + "/" + customerId);
		
		logger.info("=====>> Customer deleted.");
		
	}

}
