package com.southsource.sundy_aaasistant_jack.business;

import java.util.ArrayList;

import com.southsource.sundy_aaasistant_jack.model.Payment;
import com.southsource.sundy_aaasistant_jack.sqlite.PaymentSqlite;

import android.content.Context;


public class PaymentBLL {
	private PaymentSqlite mPaymentSqlite;
	private Context mContext;
	public PaymentBLL(Context pContext){
		mContext = pContext;
		mPaymentSqlite = new PaymentSqlite(mContext);
	}
	
	public Payment getPaymentById(int pId){
		return mPaymentSqlite.getPayment(Payment.COL_ID + " =?",new String[]{String.valueOf(pId)}).get(0);
	}
	
	public ArrayList<Payment> getAllPayments(){
		return mPaymentSqlite.getAllPayments();
	}
	
	public boolean updatePayment(Payment Payment){
		return mPaymentSqlite.updatePayment(Payment);
	}
	
	
	public void createPayment(Payment pPayment){
		 mPaymentSqlite.createPayment(pPayment);
	}
	
	public void deletePayment(Payment pPayment){
		mPaymentSqlite.deletePayment(pPayment.getId());
	}
	
	
	
}
