package com.example.demo.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.model.CustomerRequest;
import com.example.demo.model.CustomerResponse;

@Repository
public class CustomerRepository {
	@Autowired
	private HibernateTemplate template;

	public boolean saveCustomer(CustomerRequest customerRequest) {
		Boolean isCustomerSaved = false;
		CustomerEntity entity = createCustomerEntity(customerRequest);
		try {
			template.save(entity);
			isCustomerSaved = true;
		} catch (Exception e) {
			throw new RuntimeException("500, unable to process");
		}

		return isCustomerSaved;

	}

	private CustomerEntity createCustomerEntity(CustomerRequest customerRequest) {
		CustomerEntity entity = new CustomerEntity();
		entity.setCustId(customerRequest.getCustId());
		entity.setCustName(customerRequest.getCustName());
		entity.setCustNumber(customerRequest.getCustNumber());
		return entity;
	}

	public Boolean updateCustomer(int id) {
		Boolean isUpdated = false;
		try {
			template.update(id);
			isUpdated = true;
		} catch (Exception e) {
			// loggers
			// custome Exceptions we have to throw
			throw new RuntimeException("row is not found to update");
		}
		return isUpdated;
	}

	public CustomerResponse getCustomer(int id) {
		CustomerResponse response = new CustomerResponse();
		try {
			CustomerEntity entity = template.get(CustomerEntity.class, id);
			response.setCustId(entity.getCustId());
			response.setCustName(entity.getCustName());
			response.setCustNumber(entity.getCustNumber());
		} catch (Exception e) {
			// loggers
			// throw customExceptions
			throw new RuntimeException("customer not found.");
		}

		return response;
	}

	public List<CustomerResponse> getCustomerRecords() {

		SessionFactory sessionFactory = null;
		Session session = null;
		List<CustomerResponse> responseList = null;
		// List<CustomerEntity> entityList= template.find(queryString, values)
		session = sessionFactory.openSession();
		Query query = session.createQuery("from CustomerEntity");
		List<CustomerEntity> customerEntityList = query.list();
		return responseList = customerEntityList.stream()
				.filter(entity -> org.apache.commons.lang3.StringUtils.isNotBlank(entity.getCustName())).map(entity -> {
					CustomerResponse response = new CustomerResponse();
					response.setCustName(entity.getCustName());
					response.setCustId(entity.getCustId());
					response.setCustNumber(entity.getCustNumber()); 
					//responseList.add(response);
					return response;
				}).collect(Collectors.toList());
	}

	public boolean delete(int id) {
		Boolean isRowsDeleted = false;
		try {
			template.delete("CustomerEntity", id);
			isRowsDeleted = true;
		} catch (Exception e) {
			// loggers
			// throw customExceptions
			throw new RuntimeException("row is not found");
		}
		return isRowsDeleted;

	}
}
