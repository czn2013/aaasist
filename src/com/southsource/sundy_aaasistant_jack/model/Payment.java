package com.southsource.sundy_aaasistant_jack.model;


import java.util.Date;

import java.util.ArrayList;



public class Payment {

	public static final String COL_ID = "paymentid";

	public static final String COL_PAYMENT_DATE ="paymentdate";
	public static final String COL_USER_IDS ="userids";
	
	public static final String COL_ACCOUNTBOOK_ID = "accountbookid";
	public static final String COL_ACCOUNTBOOK_NAME = "accountbookname";
	
	public static final String COL_CATEGORY_ID = "categoryid";
	public static final String COL_CATEGORY_NAME = "categoryname";
	public static final String COL_COUNT = "count";
	public static final String COL_TYPE ="type";
	
	public static final String COL_COMMENTS = "comments";
	
	public static final String COL_CREATE_DATE = "createdate";
	public static final String COL_STATE = "state";
	
	private int paymentid;

	private Date paymentdate;
	private int accountbook_id;
	private String accountbook_name;
	private int category_id;
	private String category_name;
	
	private double count;
	// 0:lend 1: share 2：personal payment
	private int type;
	private ArrayList<Integer> userids;
	private String comments;
	
	private Date createdate = new Date();
	//0 : show   ;   1:  hide
	private int state;
	
	
	
	public int getId() {
		return paymentid;
	}
	public void setId(int pId) {
		this.paymentid = pId;
	}

	
	public Date getPaymentDate() {
		return paymentdate;
	}
	public void setPaymentDate(Date pPaymentdate) {
		this.paymentdate = pPaymentdate;
	}
	public int getAccountbookId() {
		return accountbook_id;
	}
	public void setAccountbookId(int pAccountbookId) {
		this.accountbook_id = pAccountbookId;
	}
	public int getCategoryId() {
		return category_id;
	}
	public void setCategoryId(int category_id) {
		this.category_id = category_id;
	}
	public double getCount() {
		return count;
	}
	public void setCount(Double pCount) {
		this.count = pCount;
	}
	public int getType() {
		return type;
	}
	public void setType(int pType) {
		this.type = pType;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String pComments) {
		this.comments = pComments;
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
	
	public String getAccountbookName() {
		return accountbook_name;
	}
	public void setAccountbookName(String pAccountbookName) {
		this.accountbook_name = pAccountbookName;
	}
	public String getCategoryName() {
		return category_name;
	}
	public void setCategoryName(String pCategoryName) {
		this.category_name = pCategoryName;
	}
	public String toString(){
		return "id: "+paymentid+ ", date: " +createdate+ ", state: " + state  ;
		
	}
	public void setUserIds(ArrayList<Integer> pUserIds) {
		userids =pUserIds;
	}
	
	public ArrayList<Integer> getUserIds(){
		return userids;
	}

}
