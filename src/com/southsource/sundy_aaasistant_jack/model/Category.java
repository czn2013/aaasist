package com.southsource.sundy_aaasistant_jack.model;

import java.util.Date;

public class Category {

	public static final String COL_ID = "categoryid";
	public static final String COL_NAME = "categoryname";
	public static final String COL_PARENT = "parent";
	public static final String COL_CREATE_DATE = "createdate";
	public static final String COL_STATE = "state";

	private int categoryid;
	private String categoryname;
	private int parent = 0;
	private Date createdate = new Date();;
	// is visible 0 , is hidden 1;
	private int state;
	
	
	

	public Category() {
		super();
	}

	public Category(int categoryid, String categoryname, int parent,
			Date createdate, int state) {
		super();
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.parent = parent;
		if (createdate != null) {
			this.createdate = createdate;
		}
		this.state = state;
	}
	public int getId() {
		return categoryid;
	}

	public void setId(int pId) {
		this.categoryid = pId;
	}

	public String getName() {
		return categoryname;
	}

	public void setName(String pAccountBookName) {
		this.categoryname = pAccountBookName;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int pParent) {
		this.parent = pParent;
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
		return categoryname; 
	}
}
