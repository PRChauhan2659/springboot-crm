package com.parth.springboot.crm.service;

import java.util.List;

import com.parth.springboot.crm.entity.Customer;

public interface CustomerService {

public List<Customer> getCustomers();
	
	public Customer getCustomer(int customerId);
	
	public void addCustomer(Customer customer);
	
	public void deleteCustomer(int customerId);
	
}
