package com.southsource.sundy_aaasistant_jack.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.content.Context;

import com.southsource.sundy_aaasistant_jack.model.Category;
import com.southsource.sundy_aaasistant_jack.sqlite.CategorySqlite;

public class CategoryBLL {
	
	private Context mContext;
	private CategorySqlite mCategorySqlite;
	
	public CategoryBLL(Context pContext){
		mContext = pContext;
		mCategorySqlite = new CategorySqlite(mContext);
	}
	
	
	
	public Category getCategoryById(int pId){
		ArrayList<Category> _CategorysList = mCategorySqlite.getCategory(Category.COL_ID + " =?",new String[]{""+pId});
		if (_CategorysList.size()>0) {
			return _CategorysList.get(0);
		}else {
			return null;
		}
		
	}
	
	public ArrayList<Category> getAllCategories(){
		return mCategorySqlite.getAllCategories();
	}
	
	public ArrayList<Category> getAllChildCategories(){
		ArrayList<Category> categories = getAllCategories();
		for(int i= 0 ; i<categories.size(); i++){
			if (getCategoryChild(categories.get(i)).size()>0) {
				categories.remove(i);
			}
		}
		return categories ;
	}
	
	public ArrayList<Category> getParentCategory() {
		// TODO Auto-generated method stub
		return mCategorySqlite.getCategory(Category.COL_PARENT + "=?", new String[]{"0"});
	}
	
	public List<Category> getCategoryGoup() {
		// TODO Auto-generated method stub
		List<Category> _GroupList = new ArrayList<Category>();
		_GroupList = mCategorySqlite.getCategory(Category.COL_PARENT+ "=?", new String[]{"0"});
		return _GroupList;
	}

	public List<Category> getCategoryChild(Category pCategory ) {
		List<Category> _ChildCategories = 
				mCategorySqlite.getCategory(Category.COL_PARENT + "=?", new String[]{String.valueOf(pCategory.getId())});
		return _ChildCategories;
	}
	
	
	public boolean updateCategory(Category Category){
		return mCategorySqlite.updateCategory(Category);
	}
	
	public boolean existCategoryName(String pCategoryName){
		if (mCategorySqlite.exists(Category.COL_NAME + "=?",new String[]{pCategoryName})) {
			return true;
		}else {
			return false;
		}
	}
	public void createCategory(Category pCategory){
		 mCategorySqlite.createCategory(pCategory);
	}
	
	public void deleteCategory(Category pCategory){
		mCategorySqlite.deleteCategory(pCategory.getId());
	}






	

}
