package com.southsource.sundy_aaasistant_jack.sqlite;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.southsource.sundy_aaasistant_jack.model.AccountBook;
import com.southsource.sundy_aaasistant_jack.model.Category;
import com.southsource.sundy_aaasistant_jack.model.Payment;
import com.southsource.sundy_aaasistant_jack.sqlite.base.BaseSqlite;
import com.southsource.sundy_aaasistant_jack.util.DateTools;




public class PaymentSqlite extends BaseSqlite {

	public PaymentSqlite(Context context) {
		super(context);
	}
	
	public static final String TABLENAME= "Payment";

	public static final String[] TABLECOLUMNS = {
			Payment.COL_ID,
			Payment.COL_ACCOUNTBOOK_ID,
			Payment.COL_ACCOUNTBOOK_NAME,
			Payment.COL_CATEGORY_ID,
			Payment.COL_CATEGORY_NAME,
			Payment.COL_COUNT,
			Payment.COL_PAYMENT_DATE,
			Payment.COL_TYPE,
			Payment.COL_USER_IDS,
			Payment.COL_COMMENTS,
			Payment.COL_CREATE_DATE,
			Payment.COL_STATE};

	public static final String CREATE_SQL = 
			"CREATE TABLE " + TABLENAME + " (" +
			Payment.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			Payment.COL_ACCOUNTBOOK_ID + " INTEGER not null references " + 
			       AccountBookSqlite.TABLE_NAME +"(" +AccountBook.COL_ID +"),"+ 
			Payment.COL_CATEGORY_ID + " INTEGER not null references " + 
			       CategorySqlite.TABLE_NAME +"(" +Category.COL_ID +"),"+
			Payment.COL_COUNT + " FLOAT not null, " +
			Payment.COL_PAYMENT_DATE+ " datetime not null, " +
			Payment.COL_TYPE + " integer not null, "+ 
			Payment.COL_USER_IDS + " text not null, " + 
			Payment.COL_COMMENTS + " text," +
			Payment.COL_CREATE_DATE + " datetime not null ," +
			Payment.COL_STATE + " INTEGER " +
			");";	
	
	public static final String VIEW_NAME ="PAYMENT_VIEW";
	
	public static final String CREATE_VIEW = 
			"CREATE VIEW " + VIEW_NAME +" AS SELECT a.* ,b." +AccountBook.COL_NAME + " ,c." +Category.COL_NAME +
					" from " + TABLENAME + " a left join " + AccountBookSqlite.TABLE_NAME + " b on a." + Payment.COL_ACCOUNTBOOK_ID + "= b." + AccountBook.COL_ID +
					" left join " +CategorySqlite.TABLE_NAME +" c on a."+Payment.COL_CATEGORY_ID +"=c." +Category.COL_ID ;
	
	
	public void createPayment(Payment pPayment){
		ContentValues _ContentValues = getContentValues(pPayment);
		this.create(_ContentValues);
	}

	public void deletePayment(int pPaymentId){
		String whereSql = Payment.COL_ID + " = ?";
		String[] whereParams = new String[]{String.valueOf(pPaymentId)};
		try {
			if (this.exists(whereSql, whereParams)) {
				this.delete(whereSql, whereParams);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean updatePayment (Payment Payment) {
		// prepare blog data
		ContentValues values = getContentValues(Payment); 
		
		String whereSql = Payment.COL_ID + "=?";
		String[] whereParams = new String[]{String.valueOf(Payment.getId())};
		// create or update
		try {
			if (this.exists(whereSql, whereParams)) {
				this.update(values, whereSql, whereParams);
			} else {
				this.create(values);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public ArrayList<Payment> getAllPayments(){
		ArrayList<Payment> PaymentList = getPayment(null, null);
		return PaymentList;
	}
	
	public ArrayList<Payment> getPayment(String where,String[] whereArgs){
		Cursor cursor= null;
		 ArrayList<Payment> _PaymentList= new ArrayList<Payment>();
		try {
			cursor = getDbInstance().query(VIEW_NAME, TABLECOLUMNS,  where,  whereArgs, null, null, Payment.COL_PAYMENT_DATE);
			while(cursor.moveToNext()){
				Payment _Payment =new Payment();
				_Payment.setId(cursor.getInt(cursor.getColumnIndex(Payment.COL_ID)));

				

				_Payment.setAccountbookId(cursor.getInt(cursor.getColumnIndex(Payment.COL_ACCOUNTBOOK_ID)));
				_Payment.setAccountbookName(cursor.getString(cursor.getColumnIndex(Payment.COL_ACCOUNTBOOK_NAME)));
				_Payment.setCategoryId(cursor.getInt(cursor.getColumnIndex(Payment.COL_CATEGORY_ID)));
				_Payment.setCategoryName(cursor.getString(cursor.getColumnIndex(Payment.COL_CATEGORY_NAME)));

				_Payment.setCount(cursor.getDouble(cursor.getColumnIndex(Payment.COL_COUNT)));
				String _PAYDateString = cursor.getString(cursor.getColumnIndex(Payment.COL_CREATE_DATE));
				_Payment.setPaymentDate(DateTools.getDate(_PAYDateString, "yyyy-MM-dd HH:mm:ss"));
				
				_Payment.setType(cursor.getInt(cursor.getColumnIndex(Payment.COL_TYPE)));
				String _UserIdString = cursor.getString(cursor.getColumnIndex(Payment.COL_USER_IDS));
				String[] _UserIds = _UserIdString.split(",");
				ArrayList<Integer> _UserIdList= new ArrayList<Integer>();
				for (String _UserId : _UserIds) {
					_UserIdList.add(Integer.valueOf(_UserId));
				}
				_Payment.setUserIds(_UserIdList);
				_Payment.setComments(cursor.getString(cursor.getColumnIndex(Payment.COL_COMMENTS)));
				
				String _DateString = cursor.getString(cursor.getColumnIndex(Payment.COL_CREATE_DATE));
				_Payment.setCreateDate(DateTools.getDate(_DateString, "yyyy-MM-dd HH:mm:ss"));
				_Payment.setState(cursor.getInt(cursor.getColumnIndex(Payment.COL_STATE)));
				_PaymentList.add(_Payment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
			
		}
			return _PaymentList;
		
	}

	
	private ContentValues getContentValues(Payment pPayment ){
		ContentValues values = new ContentValues();
		if (pPayment.getId() != 0) {
			values.put(Payment.COL_ID, pPayment.getId());
		}
		values.put(Payment.COL_ACCOUNTBOOK_ID, pPayment.getAccountbookId())	;
		values.put(Payment.COL_CATEGORY_ID, pPayment.getCategoryId());
		values.put(Payment.COL_COMMENTS, pPayment.getComments());
		values.put(Payment.COL_COUNT, pPayment.getCount());
		values.put(Payment.COL_PAYMENT_DATE, DateTools.getFormatDateTime(pPayment.getPaymentDate(),"yyyy-MM-dd HH:mm:ss"));
		values.put(Payment.COL_TYPE, pPayment.getType());
		StringBuilder _StringBuilder =new StringBuilder();
		for (int _UserId : pPayment.getUserIds()) {
			_StringBuilder.append(_UserId+",");
		}
		
		values.put(Payment.COL_USER_IDS, _StringBuilder.substring(0, _StringBuilder.length()-1));

		values.put(Payment.COL_CREATE_DATE, DateTools.getFormatDateTime(pPayment.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
		values.put(Payment.COL_STATE, pPayment.getState());
		return values;
	}

	@Override
	protected String tableName() {
		// TODO Auto-generated method stub
		return TABLENAME;
	}

	@Override
	protected String[] tableColumns() {
		// TODO Auto-generated method stub
		return TABLECOLUMNS;
	}

	@Override
	protected String createSql() {
		// TODO Auto-generated method stub
		return CREATE_SQL;
	}

	@Override
	protected String upgradeSql() {
		// TODO Auto-generated method stub
		return "DROP "  +" IF EIXTS "+ TABLENAME + " ;";
	}
}
