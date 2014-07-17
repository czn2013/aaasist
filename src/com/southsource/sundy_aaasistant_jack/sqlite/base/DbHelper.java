package com.southsource.sundy_aaasistant_jack.sqlite.base;



import com.southsource.sundy_aaasistant_jack.R;
import com.southsource.sundy_aaasistant_jack.model.AccountBook;
import com.southsource.sundy_aaasistant_jack.model.Category;
import com.southsource.sundy_aaasistant_jack.model.User;
import com.southsource.sundy_aaasistant_jack.sqlite.AccountBookSqlite;
import com.southsource.sundy_aaasistant_jack.sqlite.CategorySqlite;
import com.southsource.sundy_aaasistant_jack.sqlite.PaymentSqlite;
import com.southsource.sundy_aaasistant_jack.sqlite.UserSqlite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	
	private static Context mContext;
	private static final String DB_NAME = "aaasist.db";
	private static final int DB_VERSION = 1;
	private static DbHelper sDbHelper = null;
	
	public static DbHelper getInstance(Context pContext){
		mContext = pContext;
		if(sDbHelper == null){
			sDbHelper =new  DbHelper(mContext, DB_NAME, null, DB_VERSION);
		}
		return sDbHelper;
	}
	


	public DbHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);

	}
	
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(UserSqlite.CREATE_SQL);
		db.execSQL(AccountBookSqlite.CREATE_SQL);
		db.execSQL(CategorySqlite.CREATE_SQL);
		db.execSQL(PaymentSqlite.CREATE_SQL);
		db.execSQL(PaymentSqlite.CREATE_VIEW);
		
		init(db);
		
	}

	private void init(SQLiteDatabase db) {
		
		//init user 
		db.insert(UserSqlite.TABLE_NAME, null, UserSqlite.getContentValues(new User(1,"jack",null,1)));
		db.insert(UserSqlite.TABLE_NAME, null, UserSqlite.getContentValues(new User(2,"Rose",null,1)));
		
		//init category
		String[]  categories = mContext.getResources().getStringArray(R.array.InitDefaultCategoryName);
		for(int i = 0; i<categories.length ; i++)
		{
			db.insert(CategorySqlite.TABLE_NAME, null, CategorySqlite.getContentValues(new Category(i,categories[i],0,null,1)));
		}
		
		//init AccountBook
		db.insert(AccountBookSqlite.TABLE_NAME, null, AccountBookSqlite.getContentValues(new AccountBook(1,"Default",0,null,1)));
		
		
	}



	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL(upgradeSql());
		onCreate(db);
	}



	private String upgradeSql() {
		// TODO Auto-generated method stub
		return null;
	}




}
