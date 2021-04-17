package com.xyzInsurance.brokerOnboardingSvc.entities;

public class BrokerRecord {
	
	private long id;
	private String firstName;
	private String lastName;
	
	public BrokerRecord(long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public BrokerRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "BrokerRecord [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	

}
