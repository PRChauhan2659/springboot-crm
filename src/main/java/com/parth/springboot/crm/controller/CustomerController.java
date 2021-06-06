package com.parth.springboot.crm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.parth.springboot.crm.entity.Customer;
import com.parth.springboot.crm.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		return "access-denied";
	}
	
	@GetMapping("/list")
	public String getCustomers(Model model) {
		
		List<Customer> customers = customerService.getCustomers();
		
		model.addAttribute("customers", customers);
		
		return "customer-list";
		
	}
	
	@GetMapping("/showFormAdd")
	public String showFormAdd(Model model) {
	
		model.addAttribute("customer", new Customer());
		
		return "customer-form";
		
	}
	
	@PostMapping("/save")
	public String saveCustomer( @ModelAttribute("customer") Customer customer) {
		
		customerService.addCustomer(customer);
		
		return "redirect:/customers/list";
		
	}
	
	@GetMapping("/showFormUpdate")
	public String showFormUpdate( @RequestParam("customerId") int customerId, Model model) {
		
		Customer customer = customerService.getCustomer(customerId);
		
		model.addAttribute("customer", customer);
		
		return "customer-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteCustomer( @RequestParam("customerId") int customerId) {
		
		customerService.deleteCustomer(customerId);
		
		return "redirect:/customers/list";
		
	}
	
}
