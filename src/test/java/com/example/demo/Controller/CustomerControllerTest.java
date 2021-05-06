package com.example.demo.Controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.CustomerController;
import com.example.demo.service.CustomerService;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class CustomerControllerTest {
	@InjectMocks
	CustomerController controller;
	
	@Mock
	private CustomerService service;
	
	@Test
	public void updateCustomerSuccessTest() {
		Mockito.when(service.updateCustomer(Mockito.anyInt())).thenReturn(Boolean.TRUE);
		ResponseEntity<Boolean> entity = controller.updateCustomer(1);
		Assert.assertNotNull(entity);
		Assert.assertTrue(entity.getBody());
		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
		//Mockito.verify(service, Mockito.times(1)).updateCustomer(Mockito.anyInt());
	}
	
	@Test
	public void updateCustomerFailTest() {
		Mockito.when(service.updateCustomer(Mockito.anyInt())).thenThrow(new RuntimeException());
		ResponseEntity<Boolean> entity = controller.updateCustomer(1);
		Assert.assertNotNull(entity);
		Assert.assertFalse(entity.getBody());
		Assert.assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
	}
}
