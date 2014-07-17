package com.southsource.sundy_aaasistant_jack.model;

import java.util.Date;

public class AccountBook {

	public static final String COL_ID = "accountbookid";
	public static final String COL_NAME = "accountbookname";
	public static final String COL_IS_DEFAULT = "isdefault";
	public static final String COL_CREATE_DATE = "createdate";
	public static final String COL_STATE = "state";

	private int accountbookid;
	private String accountbookname;
	// isdefault 0, not default 1;
	private int isdefault = 1;
	private Date createdate = new Date();;
	// is visible 0 , is hidden 1;
	private int state;
	
	

	public AccountBook() {
		super();
	}

	public AccountBook(int accountbookid, String accountbookname,
			int isdefault, Date createdate, int state) {
		super();
		this.accountbookid = accountbookid;
		this.accountbookname = accountbookname;
		this.isdefault = isdefault;
		if (createdate != null) {
			this.createdate = createdate;
		}
		
		this.state = state;
	}

	public int getId() {
		return accountbookid;
	}

	public void setId(int pId) {
		this.accountbookid = pId;
	}

	public String getName() {
		return accountbookname;
	}

	public void setName(String pAccountBookName) {
		this.accountbookname = pAccountBookName;
	}

	public int getIsDefault() {
		return isdefault;
	}

	public void setIsDefault(int pIsDefault) {
		this.isdefault = pIsDefault;
	}

	public Date getCreateDate() {
		return createdate;
	}

	public void setCreateDate(Date pCreateDate) {
		this.createdate = pCreateDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int pState) {
		this.state = pState;
	}

	public String toString(){
		return accountbookname; 
	}
}
