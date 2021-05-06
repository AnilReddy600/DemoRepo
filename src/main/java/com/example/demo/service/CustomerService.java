package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomerRequest;
import com.example.demo.model.CustomerResponse;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repository;

	public boolean saveCustomer(CustomerRequest customerRequest) throws Exception {

		try {
			Boolean result = repository.saveCustomer(customerRequest);
			return result;
		} catch (Exception e) {
			throw new Exception("unable to process the request");
		}

	}

	public Boolean updateCustomer(int id) {
		Boolean rowsupdated = repository.updateCustomer(id);
		return rowsupdated;

	}

	public CustomerResponse getCustomer(int id) {
		CustomerResponse response = null;
		response = repository.getCustomer(id);

		return response;
	}

	public List<CustomerResponse> getCustomerRecords() {
		List<CustomerResponse> listOfCustomers = null;

		listOfCustomers = repository.getCustomerRecords();

		return listOfCustomers;

	}

	public Boolean delete(int id) {
		Boolean isRowDeleted = false;
		isRowDeleted = repository.delete(id);
		return isRowDeleted;
	}

}
