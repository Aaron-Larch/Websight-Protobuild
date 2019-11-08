package com.webbuild.javabrains.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //declare data layer
public class TableObjects {
	
	
	@Id  //Declare primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String ORDERID;
	
	@Column(name="customerId") //declare column header
	private String CUSTOMERID;
	
	@Column(name="employeeId") //declare column header
	private String EMPLOYEEID;
	
	@Column(name="shipVia") //declare column header
	private String SHIPVIA;
	
	@Column(name="freight") //declare column header
	private String FREIGHT;
	
	@Column(name="shipName") //declare column header
	private String SHIPNAME;
	
	@Column(name="shipCountry") //declare column header
	private String SHIPCOUNTRY;

	
	public TableObjects() {
		
	}
	
	//collect group of object to form a table for better organization
	public TableObjects(String odrId, String custId, String empId, String shiped, String frate, String shName, String shCountery) {
		super();
		this.ORDERID=odrId;
		this.CUSTOMERID=custId;
		this.EMPLOYEEID=empId;
		this.SHIPVIA=shiped;
		this.FREIGHT=frate;
		this.SHIPNAME=shName;
		this.SHIPCOUNTRY=shCountery;
	}
	
	public String getORDERID() {
		return ORDERID; //Retrieve a value
	}

	public void setORDERID(String oRDERID) {
		this.ORDERID = oRDERID; //Store a value
	}
	
	public String getCUSTOMERID() {
		return CUSTOMERID; //Retrieve a value
	}

	public void setCUSTOMERID(String cUSTOMERID) {
		this.CUSTOMERID = cUSTOMERID; //Store a value
	}

	public String getEMPLOYEEID() {
		return EMPLOYEEID; //Retrieve a value
	}

	public void setEMPLOYEEID(String eMPLOYEEID) {
		this.EMPLOYEEID = eMPLOYEEID; //Store a value
	}

	public String getSHIPVIA() {
		return SHIPVIA; //Retrieve a value
	}

	public void setSHIPVIA(String sHIPVIA) {
		this.SHIPVIA = sHIPVIA; //Store a value
	}

	public String getFREIGHT() {
		return FREIGHT; //Retrieve a value
	}

	public void setFREIGHT(String fREIGHT) {
		this.FREIGHT = fREIGHT; //Store a value
	}

	public String getSHIPNAME() {
		return SHIPNAME; //Retrieve a value
	}

	public void setSHIPNAME(String sHIPNAME) {
		this.SHIPNAME = sHIPNAME; //Store a value
	}

	public String getSHIPCOUNTRY() {
		return SHIPCOUNTRY; //Retrieve a value
	}

	public void setSHIPCOUNTRY(String sHIPCOUNTERY) {
		this.SHIPCOUNTRY = sHIPCOUNTERY; //Store a value
	}

}
