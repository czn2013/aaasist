package com.southsource.sundy_aaasistant_jack.sqlite;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.southsource.sundy_aaasistant_jack.model.Category;
import com.southsource.sundy_aaasistant_jack.sqlite.base.BaseSqlite;
import com.southsource.sundy_aaasistant_jack.util.DateTools;

public class CategorySqlite extends BaseSqlite {

	public CategorySqlite(Context pContext) {
		super(pContext);
		// TODO Auto-generated constructor stub
	}
	
	public static final String TABLE_NAME= "category";

	public static final String[] TABLECOLUMNS = {
			Category.COL_ID,
			Category.COL_NAME,
			Category.COL_PARENT,
			Category.COL_CREATE_DATE,
			Category.COL_STATE};

	public static final String CREATE_SQL = 
			"CREATE TABLE " + TABLE_NAME + " (" +
			Category.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			Category.COL_NAME + " TEXT NOT NULL, " +
			Category.COL_PARENT + " INTEGER NOT NULL, " +
			Category.COL_CREATE_DATE + " DATETIME NOT NULL ," +
			Category.COL_STATE + " INTEGER " +
			");";	
	
	
	public void createCategory(Category pCategory){
		ContentValues _ContentValues = getContentValues(pCategory);
		this.create(_ContentValues);
	}

	public void deleteCategory(int pCategoryId){
		String whereSql = Category.COL_ID + " = ?";
		String[] whereParams = new String[]{String.valueOf(pCategoryId)};
		try {
			if (this.exists(whereSql, whereParams)) {
				this.delete(whereSql, whereParams);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateCategory (Category pCategory) {
		// prepare blog data
		ContentValues values = getContentValues(pCategory); 
		
		String whereSql = pCategory.COL_ID + "=?";
		String[] whereParams = new String[]{String.valueOf(pCategory.getId())};
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
	
	public ArrayList<Category> getAllCategories(){
		ArrayList<Category> CategoryList = getCategory(null, null);
		return CategoryList;
	}
	
	
	public ArrayList<Category> getCategory(String where,String[] whereArgs){
		Cursor cursor= null;
		 ArrayList<Category> _CategoryList= new ArrayList<Category>();
		try {
			cursor = getDbInstance().query(TABLE_NAME, TABLECOLUMNS,  where,  whereArgs, null, null, null);
			while(cursor.moveToNext()){
				Category _Category =new Category();
				_Category.setId(cursor.getInt(cursor.getColumnIndex(Category.COL_ID)));
				_Category.setName(cursor.getString(cursor.getColumnIndex(Category.COL_NAME)));
				_Category.setParent(cursor.getInt(cursor.getColumnIndex(Category.COL_PARENT)));
				String _DateString = cursor.getString(cursor.getColumnIndex(Category.COL_CREATE_DATE));
				_Category.setCreateDate(DateTools.getDate(_DateString, "yyyy-MM-dd HH:mm:ss"));
				Log.i("jack",""+_DateString );
				_Category.setState(cursor.getInt(cursor.getColumnIndex(Category.COL_STATE)));
				_CategoryList.add(_Category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
			
		}
			return _CategoryList;
		
	}

	public static ContentValues getContentValues(Category pCategory ){
		ContentValues values = new ContentValues();
		if (pCategory.getId() != 0) {
			values.put(Category.COL_ID, pCategory.getId());
		}
		values.put(Category.COL_NAME, pCategory.getName());
		values.put(Category.COL_PARENT, pCategory.getParent());
		values.put(Category.COL_CREATE_DATE, DateTools.getFormatDateTime(pCategory.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
		values.put(Category.COL_STATE, pCategory.getState());
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
		return null;
	}
	
	@Override
	protected String upgradeSql() {
		// TODO Auto-generated method stub
		return null;
	}



}
