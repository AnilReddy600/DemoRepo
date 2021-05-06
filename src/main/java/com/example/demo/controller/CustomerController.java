package com.example.demo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.*;
import com.example.demo.service.CustomerService;

@Controller
@RequestMapping("/customer")

public class CustomerController {
	@Autowired
	private CustomerService service;

	@PutMapping(value = "/save", consumes = "application/json", produces = "application/json")
	public ResponseEntity ResponseEntity(@RequestBody CustomerRequest request) throws Exception {
		Boolean result = service.saveCustomer(request);
		ResponseEntity<Boolean> responseCustomer = ResponseEntity.status(HttpStatus.CREATED).body(result);
		return responseCustomer;

	}

	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity updateCustomer(@PathParam("id") int id) {
		ResponseEntity<Boolean> response = null;
		try {
			Boolean updated = service.updateCustomer(id);
			service.getCustomer(id);
			response = ResponseEntity.status(HttpStatus.OK).body(updated);
		} catch (Exception e) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		}
       return response;
	}

	@GetMapping(value = "/getCustomer", produces = "application/json", consumes = "application/json")
	public ResponseEntity getCustomer(@RequestParam(name = "id") int id) {
		ResponseEntity<CustomerResponse> responseEntity = null;
		try {
			CustomerResponse response = service.getCustomer(id);
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
			return responseEntity;
		} catch (Exception e) {
			throw new RuntimeException("customer not found");
		}

	}

	@GetMapping(value = "/allCustomers", produces = "application/json")
	public ResponseEntity allCustomers() {
		List<CustomerResponse> responseList = null;
		responseList = service.getCustomerRecords();
		ResponseEntity response = ResponseEntity.status(HttpStatus.OK).body(responseList);

		return response;
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Boolean> deletePost(@PathVariable int id) {
		try {
			Boolean isRemoved = service.delete(id);
			ResponseEntity<Boolean> response = ResponseEntity.status(HttpStatus.OK).body(isRemoved);
			return response;
		} catch (Exception e) {
			throw new RuntimeException("Invalid Id you passed");
		}
	}

}