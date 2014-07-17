package com.southsource.sundy_aaasistant_jack.sqlite;


import java.util.ArrayList;
import java.util.Date;

import com.southsource.sundy_aaasistant_jack.model.User;
import com.southsource.sundy_aaasistant_jack.sqlite.base.BaseSqlite;
import com.southsource.sundy_aaasistant_jack.util.DateTools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;



public class UserSqlite extends BaseSqlite {

	public UserSqlite(Context context) {
		super(context);
	}
	
	public static final String TABLE_NAME= "user";

	public static final String[] TABLECOLUMNS = {
			User.COL_ID,
			User.COL_NAME,
			User.COL_CREATE_DATE,
			User.COL_STATE};

	public static final String CREATE_SQL = 
			"CREATE TABLE " + TABLE_NAME + " (" +
			User.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			User.COL_NAME + " TEXT not null, " +
			User.COL_CREATE_DATE + " datetime not null ," +
			User.COL_STATE + " INTEGER " +
			");";	
	
	public static final String INIT = "INSERT INTO TABLE " + TABLE_NAME + "(" +
			User.COL_ID +"," +User.COL_NAME +"," + User.COL_CREATE_DATE +"," + User.COL_STATE +
			") VALUES(1,zhangsan,now(),1)";
	
	
	public void createUser(User pUser){
		ContentValues _ContentValues = getContentValues(pUser);
		this.create(_ContentValues);
	}

	public void deleteUser(int pUserId){
		String whereSql = User.COL_ID + " = ?";
		String[] whereParams = new String[]{String.valueOf(pUserId)};
		try {
			if (this.exists(whereSql, whereParams)) {
				this.delete(whereSql, whereParams);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateUser (User user) {
		// prepare blog data
		ContentValues values = getContentValues(user); 
		
		String whereSql = User.COL_ID + "=?";
		String[] whereParams = new String[]{String.valueOf(user.getId())};
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
	
	public ArrayList<User> getAllUsers(){
		ArrayList<User> userList = getUser(null, null);
		return userList;
	}
	
	public ArrayList<User> getUser(String where,String[] whereArgs){
		Cursor cursor= null;
		 ArrayList<User> _UserList= new ArrayList<User>();
		try {
			cursor = getDbInstance().query(TABLE_NAME, TABLECOLUMNS,  where,  whereArgs, null, null, null);
			while(cursor.moveToNext()){
				User _User =new User();
				_User.setId(cursor.getInt(cursor.getColumnIndex(User.COL_ID)));
				_User.setName(cursor.getString(cursor.getColumnIndex(User.COL_NAME)));
				String _DateString = cursor.getString(cursor.getColumnIndex(User.COL_CREATE_DATE));
				_User.setCreateDate(DateTools.getDate(_DateString, "yyyy-MM-dd HH:mm:ss"));
				Log.i("jack",""+_DateString );
				_User.setState(cursor.getInt(cursor.getColumnIndex(User.COL_STATE)));
				_UserList.add(_User);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
			
		}
			return _UserList;
		
	}

	public static ContentValues getContentValues(User pUser ){
		ContentValues values = new ContentValues();
		if (pUser.getId() != 0) {
			values.put(User.COL_ID, pUser.getId());
		}
		values.put(User.COL_NAME, pUser.getName());
		values.put(User.COL_CREATE_DATE, DateTools.getFormatDateTime(pUser.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
		values.put(User.COL_STATE, pUser.getState());
		return values;
	}

	@Override
	protected String tableName() {
		// TODO Auto-generated method stub
		return TABLE_NAME;
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
		return "DROP "  +" IF EIXTS "+ TABLE_NAME + " ;";
	}
	
	
	
}