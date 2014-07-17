package com.southsource.sundy_aaasistant_jack.util;

public class RegexTools {

	private static final String mMoneyRegEx = "[0-9]+ [.]? [0-9]{0,2}";
	private static final String mUserNameRegEx = "[a-zA-Z_\u4e00-\u9fa5]+[0-9a-zA-Z_\u4e00-\u9fa5]*";
	
	public static boolean isMoney(String pMoney){
		if(pMoney.matches(mMoneyRegEx)){
			return true;	
		}else {
			return false;
		}
	}
	
	public static boolean isLegalUserName(String pUserName){
		if(pUserName.matches(mUserNameRegEx)){
			return true;
		}else {
			return false;
		}
	}

	public static boolean isLegalAccountBookName(String pAccountBookName) {
		// TODO Auto-generated method stub
		if(pAccountBookName.matches(mUserNameRegEx)){
			return true;
		}else {
			return false;
		}
	}

	public static boolean isLegalCategoryName(String pCategoryName) {
		// TODO Auto-generated method stub
		if(pCategoryName.matches(mUserNameRegEx)){
			return true;
		}else {
			return false;
		}
	}
}
