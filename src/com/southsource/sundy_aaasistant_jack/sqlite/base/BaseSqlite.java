package com.southsource.sundy_aaasistant_jack.sqlite.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class BaseSqlite {

	private static DbHelper dbh = null;
	private static SQLiteDatabase db = null;
	private Cursor cursor = null;
	private Context mContext = null;
	
	public BaseSqlite(Context pContext) {
		mContext = pContext;
		dbh = DbHelper.getInstance(pContext);
	}
	
	protected Context getContext(){
		return mContext;
	}
	
	
	public static SQLiteDatabase getDbInstance() {
		if (db == null ) {
			
			db = dbh.getWritableDatabase();
		}
		return db;
	}
	
	public static void closeDatabase() {
		if (db.isOpen()) {
			db.close();
		}
	}
	
	protected void beginTransaction(){
		db.beginTransaction();
	}
	
	protected void endTransaction() {
		db.endTransaction();
	}
	
	protected void setTransactionSuccessful(){
		db.setTransactionSuccessful();
	}	

	public void create (ContentValues values) {
		try {
			db = getDbInstance();
			db.insert(tableName(), null, values);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	
	public void update (ContentValues values, String where, String[] params) {
		try {
			db = getDbInstance();
			db.update(tableName(), values, where, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	
	public void delete (String where, String[] params) {
		try {
			db = getDbInstance();
			db.delete(tableName(), where, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	
//	public ArrayList<ArrayList<String>> query (String where, String[] params) {
//		ArrayList<ArrayList<String>> rList = new ArrayList<ArrayList<String>>();
//		try {
//			db = dbh.getReadableDatabase();
//			cursor = db.query(tableName(), tableColumns(), where, params, null, null, null);
//			while (cursor.moveToNext()) {
//				int columnCount = cursor.getColumnCount();
//				ArrayList<String> rRow = new ArrayList<String>();
//				for (int i = 0; i<columnCount ; i++) {
//					rRow.add(i, cursor.getString(i));
//					Log.i("Jack",rRow.get(i));
//				}
//				rList.add(rRow);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			cursor.close();
//			db.close();
//		}
//		return rList;
//	}
	
	public int count (String where, String[] params) {
		try {
			db = getDbInstance();
			cursor = db.query(tableName(), tableColumns(), where, params, null, null, null);
			return cursor.getCount();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return 0;
	}
	
	public boolean exists (String where, String[] params) {
		boolean result = false;
		try {
			int count = this.count(where, params);
			if (count > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			cursor.close();
		}
		return result;
	}
	
	abstract protected String tableName ();
	abstract protected String[] tableColumns ();
	abstract protected String createSql ();
	abstract protected String upgradeSql ();
	
}