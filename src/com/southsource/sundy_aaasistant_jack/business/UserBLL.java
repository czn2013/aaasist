package com.southsource.sundy_aaasistant_jack.business;

import java.util.ArrayList;

import com.southsource.sundy_aaasistant_jack.model.User;
import com.southsource.sundy_aaasistant_jack.sqlite.UserSqlite;

import android.content.Context;


public class UserBLL {
	private UserSqlite mUserSqlite;
	private Context mContext;
	public UserBLL(Context pContext){
		mContext = pContext;
		mUserSqlite = new UserSqlite(mContext);
	}
	
	public User getUserById(int pId){
		return mUserSqlite.getUser(User.COL_ID + " =?",new String[]{String.valueOf(pId)}).get(0);
	}
	
	public ArrayList<User> getAllUsers(){
		return mUserSqlite.getAllUsers();
	}
	
	public boolean updateUser(User user){
		return mUserSqlite.updateUser(user);
	}
	
	public boolean existUserName(String pUserName){
		if (mUserSqlite.exists(User.COL_NAME + "=?",new String[]{pUserName})) {
			return true;
		}else {
			return false;
		}
	}
	public void createUser(User pUser){
		 mUserSqlite.createUser(pUser);
	}
	
	public void deleteUser(User pUser){
		mUserSqlite.deleteUser(pUser.getId());
	}
	
}
