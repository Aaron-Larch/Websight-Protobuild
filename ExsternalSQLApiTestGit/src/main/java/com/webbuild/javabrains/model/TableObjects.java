package com.webbuild.javabrains.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TableObjects {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String ORDERID;
	
	@Column(name="customerId")
	private String CUSTOMERID;
	
	@Column(name="employeeId")
	private String EMPLOYEEID;
	
	@Column(name="shipVia")
	private String SHIPVIA;
	
	@Column(name="freight")
	private String FREIGHT;
	
	@Column(name="shipName")
	private String SHIPNAME;
	
	@Column(name="shipCountry")
	private String SHIPCOUNTRY;

	
	public TableObjects() {
		
	}
	
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
		return ORDERID;
	}

	public void setORDERID(String oRDERID) {
		this.ORDERID = oRDERID;
	}
	
	public String getCUSTOMERID() {
		return CUSTOMERID;
	}

	public void setCUSTOMERID(String cUSTOMERID) {
		this.CUSTOMERID = cUSTOMERID;
	}

	public String getEMPLOYEEID() {
		return EMPLOYEEID;
	}

	public void setEMPLOYEEID(String eMPLOYEEID) {
		this.EMPLOYEEID = eMPLOYEEID;
	}

	public String getSHIPVIA() {
		return SHIPVIA;
	}

	public void setSHIPVIA(String sHIPVIA) {
		this.SHIPVIA = sHIPVIA;
	}

	public String getFREIGHT() {
		return FREIGHT;
	}

	public void setFREIGHT(String fREIGHT) {
		this.FREIGHT = fREIGHT;
	}

	public String getSHIPNAME() {
		return SHIPNAME;
	}

	public void setSHIPNAME(String sHIPNAME) {
		this.SHIPNAME = sHIPNAME;
	}

	public String getSHIPCOUNTRY() {
		return SHIPCOUNTRY;
	}

	public void setSHIPCOUNTRY(String sHIPCOUNTERY) {
		this.SHIPCOUNTRY = sHIPCOUNTERY;
	}

}
