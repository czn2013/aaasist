package com.southsource.sundy_aaasistant_jack.sqlite;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract.Columns;
import android.util.Log;

import com.southsource.sundy_aaasistant_jack.model.AccountBook;
import com.southsource.sundy_aaasistant_jack.model.User;
import com.southsource.sundy_aaasistant_jack.sqlite.base.BaseSqlite;
import com.southsource.sundy_aaasistant_jack.util.DateTools;

public class AccountBookSqlite extends BaseSqlite {

	Context mContext;
	public static final String TABLE_NAME = "account_book";
	public static final String[] COLUMNS = new String[]{
		AccountBook.COL_ID,
		AccountBook.COL_NAME,
		AccountBook.COL_IS_DEFAULT,
		AccountBook.COL_CREATE_DATE,
		AccountBook.COL_STATE};
	
	public static final String CREATE_SQL = 
			"CREATE TABLE " + TABLE_NAME + " (" +
			AccountBook.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			AccountBook.COL_NAME + " TEXT not null, " +
			AccountBook.COL_IS_DEFAULT + " INTEGER not null, " +
			AccountBook.COL_CREATE_DATE + " DATETIME not null ," +
			AccountBook.COL_STATE + " INTEGER " +
			");";
	
	
	@Override
	protected String tableName() {
		// TODO Auto-generated method stub
		return TABLE_NAME;
	}

	@Override
	protected String[] tableColumns() {
		// TODO Auto-generated method stub
		return COLUMNS;
	}

	@Override
	protected String createSql() {
		// TODO Auto-generated method stub
		return CREATE_SQL;
	}

	@Override
	protected String upgradeSql() {
		// TODO Auto-generated method stub
		return  "DROP TABLE IF EXISTS " + tableName();
	}
	
	public AccountBookSqlite(Context pContext) {
		super(pContext);

	}
		
	public void createAccountBook(AccountBook pAccountBook){
		ContentValues _ContentValues = getContentValues(pAccountBook);
		this.create(_ContentValues);
	}

	public void deleteAccountBook(int pAccountBookId){
		String whereSql = AccountBook.COL_ID + " = ?";
		String[] whereParams = new String[]{String.valueOf(pAccountBookId)};
		try {
			if (this.exists(whereSql, whereParams)) {
				this.delete(whereSql, whereParams);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateAccountBook (AccountBook AccountBook) {
		// prepare blog data
		ContentValues values = getContentValues(AccountBook); 
		
		String whereSql = AccountBook.COL_ID + "=?";
		String[] whereParams = new String[]{String.valueOf(AccountBook.getId())};
		// create or update
		try {
			if (this.exists(whereSql, whereParams)) {
				this.update(values, whereSql, whereParams);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public ArrayList<AccountBook> getAllAccountBooks(){
		ArrayList<AccountBook> AccountBookList = getAccountBook(null, null);
		return AccountBookList;
	}
	
	public ArrayList<AccountBook> getAccountBook(String where,String[] whereArgs){
		Cursor cursor= null;
		ArrayList<AccountBook> AccountBookList= new ArrayList<AccountBook>();
		try {
			cursor = getDbInstance().query(TABLE_NAME, COLUMNS, where, whereArgs, null, null, null);
			
			while (cursor.moveToNext()) {
				AccountBook _AccountBook = new AccountBook();
				_AccountBook.setId(cursor.getInt(cursor.getColumnIndex(AccountBook.COL_ID)));
				_AccountBook.setName(cursor.getString(cursor.getColumnIndex(AccountBook.COL_NAME)));
				String _DateString = cursor.getString(cursor.getColumnIndex(AccountBook.COL_CREATE_DATE));
				_AccountBook.setCreateDate(DateTools.getDate(_DateString, "yyyy-MM-dd HH:mm:ss"));
				Log.i("jack",""+_DateString );
				_AccountBook.setIsDefault(cursor.getInt(cursor.getColumnIndex(AccountBook.COL_IS_DEFAULT)));
				_AccountBook.setState(cursor.getInt(cursor.getColumnIndex(AccountBook.COL_STATE)));	
				AccountBookList.add(_AccountBook);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return AccountBookList;
	}
	
	public void setDefaultAccountBook(AccountBook pAccountBook){
	
		beginTransaction();
		try {
			String where = AccountBook.COL_IS_DEFAULT + "=?";
			String[] whereArgs = new String[]{String.valueOf(0)};
			ArrayList<AccountBook> _AccountBookList = getAccountBook(where, whereArgs);
			for (AccountBook accountBook : _AccountBookList) {
				accountBook.setIsDefault(1);
				updateAccountBook(accountBook);
			}
			pAccountBook.setIsDefault(0);
			updateAccountBook(pAccountBook);
			setTransactionSuccessful();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			endTransaction();
		}
	}
	
	public static  ContentValues getContentValues(AccountBook pAccountBook ){
		ContentValues values = new ContentValues();
		if (pAccountBook.getId() != 0) {
			values.put(AccountBook.COL_ID, pAccountBook.getId());
		}
		values.put(AccountBook.COL_NAME, pAccountBook.getName());
		values.put(AccountBook.COL_CREATE_DATE, DateTools.getFormatDateTime(pAccountBook.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
		values.put(AccountBook.COL_STATE, pAccountBook.getState());
		values.put(AccountBook.COL_IS_DEFAULT, pAccountBook.getIsDefault());
		return values;
	}

}
