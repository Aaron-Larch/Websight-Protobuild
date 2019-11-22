package com.webbuild.springbootrestapitest.model;


public class TableObjects {
	
	
	private String ORDERID;
	private String CUSTOMERID;
	private String EMPLOYEEID;
	private String SHIPVIA;
	private String FREIGHT;
	private String SHIPNAME;
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
