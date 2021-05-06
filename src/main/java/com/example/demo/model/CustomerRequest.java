package com.example.demo.model;

import lombok.Data;

//@Data
public class CustomerRequest {
	private int custId;
	private String custName;
	private String custNumber;

	public CustomerRequest(int custId, String custName, String custNumber) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custNumber = custNumber;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	@Override
	public String toString() {
		return "CustomerRequest [custId=" + custId + ", custName=" + custName + ", custNumber=" + custNumber + "]";
	}

}
