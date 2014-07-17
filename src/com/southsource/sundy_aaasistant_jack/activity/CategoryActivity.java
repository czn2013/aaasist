package com.southsource.sundy_aaasistant_jack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.AdapterView.OnItemClickListener;

import com.southsource.sundy_aaasistant_jack.R;
import com.southsource.sundy_aaasistant_jack.activity.base.AFrameActivity;
import com.southsource.sundy_aaasistant_jack.adapter.CategoryAdapter;
import com.southsource.sundy_aaasistant_jack.business.CategoryBLL;

public class CategoryActivity extends AFrameActivity {
	


	String[] mMenuItems;
	ArrayAdapter<String> mMenuAdapter= null;
	ExpandableListView  mExpandableListView;
	CategoryAdapter mCategoryAdapter;
	CategoryBLL mCategoryBLL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHeaderTitle("Category Management") ;
		bindBody(R.layout.category_body);
		
		mMenuItems = getResources().getStringArray(R.array.SlideMenuCategory);
		mMenuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mMenuItems);
		setMenuAdapter(mMenuAdapter); 
		setOnMenuItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					openActivityForResult(CategoryAddOrEditActivity.class, 1233);
					break;

				default:
					break;
				}
				toggleMenu();
			}
			
		} );
		
		initUi();
		initVariable();
		bindData();
		initHandler();
		
	}
	
	

	private void initUi(){
		mExpandableListView= (ExpandableListView)findViewById(R.id.category_elv_category);
	}
	
	private void initVariable() {
		// TODO Auto-generated method stub
		mCategoryBLL = new CategoryBLL(this);
	}
	
	private void bindData(){
		//
		mCategoryAdapter = new CategoryAdapter(this, mCategoryBLL);
		mExpandableListView.setGroupIndicator(null);
		mExpandableListView.setAdapter(mCategoryAdapter);
	}
	
	private void initHandler(){
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		mCategoryAdapter.notifyDataSetChanged();
	}

	
}
