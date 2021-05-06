package com.example.demo.model;

public class CustomerResponse {
	private int custId;
	private String custName;
	private String custNumber;

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
		return "CustomerResponse [custId=" + custId + ", custName=" + custName + ", custNumber=" + custNumber + "]";
	}

}
