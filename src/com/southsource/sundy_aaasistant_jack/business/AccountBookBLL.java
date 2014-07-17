package com.southsource.sundy_aaasistant_jack.business;

import java.util.ArrayList;

import android.content.Context;
import com.southsource.sundy_aaasistant_jack.activity.base.ABaseDialogActivity;
import com.southsource.sundy_aaasistant_jack.model.AccountBook;
import com.southsource.sundy_aaasistant_jack.sqlite.AccountBookSqlite;

public class AccountBookBLL extends ABaseDialogActivity {

	private Context mContext;
	private AccountBookSqlite mAccountBookSqlite;
	
	
	public AccountBookBLL(Context pContext){
		mContext = pContext;
		mAccountBookSqlite = new AccountBookSqlite(mContext);
	}
	
	public void setDefaultAccountBook(AccountBook pAccountBook){
		mAccountBookSqlite.setDefaultAccountBook(pAccountBook);
	}
	
	public AccountBook getAccountBookById(int pId){
		ArrayList<AccountBook> _AccountBooksList = mAccountBookSqlite.getAccountBook(AccountBook.COL_ID + " =?",new String[]{""+pId});
		if (_AccountBooksList.size()>0) {
			return _AccountBooksList.get(0);
		}else {
			return null;
		}
		
	}
	
	public ArrayList<AccountBook> getAllAccountBooks(){
		return mAccountBookSqlite.getAllAccountBooks();
	}
	
	public boolean updateAccountBook(AccountBook AccountBook){
		return mAccountBookSqlite.updateAccountBook(AccountBook);
	}
	
	public boolean existAccountBookName(String pAccountBookName){
		if (mAccountBookSqlite.exists(AccountBook.COL_NAME + "=?",new String[]{pAccountBookName})) {
			return true;
		}else {
			return false;
		}
	}
	public void createAccountBook(AccountBook pAccountBook){
		 mAccountBookSqlite.createAccountBook(pAccountBook);
	}
	
	public void deleteAccountBook(AccountBook pAccountBook){
		mAccountBookSqlite.deleteAccountBook(pAccountBook.getId());
	}
	
}
