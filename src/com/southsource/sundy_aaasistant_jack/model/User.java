package com.southsource.sundy_aaasistant_jack.model;

import java.util.Date;



public class User {

	public static final String COL_ID = "userid";
	public static final String COL_NAME = "name";
	public static final String COL_CREATE_DATE = "createdate";
	public static final String COL_STATE = "state";
	
	
	private int userid;
	private String username;
	
	private Date createdate = new Date();
	//0 : show   ;   1:  hide
	private int state;
	
	
	
	public User() {
		super();
	}

	public User(int userid, String username, Date createdate, int state) {
		super();
		this.userid = userid;
		this.username = username;
		if (createdate != null) {
			this.createdate = createdate;
		}
		
		this.state = state;
	}
	
	public int getId() {
		return userid;
	}
	public void setId(int pId) {
		this.userid = pId;
	}
	public String getName() {
		return username;
	}
	public void setName(String pName) {
		this.username = pName;
	}
	public int getState() {
		return state;
	}
	public void setState(int pState) {
		this.state = pState;
	}
	public Date getCreateDate() {
		return createdate;
	}
	public void setCreateDate(Date pCreateDate) {
		this.createdate = pCreateDate;
	}
	
	public String toString(){
		return username ;
		
	}
}
