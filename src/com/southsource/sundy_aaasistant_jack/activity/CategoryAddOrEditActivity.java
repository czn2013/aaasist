package com.southsource.sundy_aaasistant_jack.activity;

import java.util.ArrayList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.southsource.sundy_aaasistant_jack.R;
import com.southsource.sundy_aaasistant_jack.activity.base.AFrameActivity;
import com.southsource.sundy_aaasistant_jack.business.CategoryBLL;
import com.southsource.sundy_aaasistant_jack.model.Category;
import com.southsource.sundy_aaasistant_jack.util.RegexTools;

public class CategoryAddOrEditActivity extends AFrameActivity {

	private CategoryBLL mCategoryBLL;
	private ArrayAdapter<Category> mSpinnerAdapter;
	
	//Views
	TextView mCategoryNameView ;
	Spinner mSpinner;
	Button mConfirmButton , mCancelButton;
	Category mParentCategory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setHeaderTitle("Create a New Category");
		bindBody(R.layout.category_create_or_edit);
		hideMenuBar();
		
		initUi();
		initVariable();
		bindData();
		initHandler();
	}
	
	private void initUi() {
		// TODO Auto-generated method stub
		mCategoryNameView =(TextView) findViewById(R.id.category_create_edit_et_name);
		mSpinner= (Spinner)findViewById(R.id.category_create_edit_spinner_parent);
		mConfirmButton = (Button) findViewById(R.id.category_create_edit_btn_confirm);
		mCancelButton = (Button) findViewById(R.id.category_create_edit_btn_cancel);
	}
	
	private void initVariable() {
		// TODO Auto-generated method stub
		mCategoryBLL= new CategoryBLL(this);
	}

	private void bindData() {
		// TODO Auto-generated method stub
		ArrayList<Category> _ParentList = mCategoryBLL.getParentCategory(); 
		Category _Category = new Category();
		_Category.setName("--Please Choose the Parent--");
		_ParentList.add(0, _Category);
		mSpinnerAdapter = new ArrayAdapter<Category>(this,android.R.layout.simple_spinner_item,_ParentList);
		mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinner.setAdapter(mSpinnerAdapter);
	}

	private void initHandler() {
		// TODO Auto-generated method stub
		
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				mParentCategory =(Category)mSpinnerAdapter.getItem(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		mConfirmButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String _CategoryName = mCategoryNameView.getText().toString().trim();
				if (_CategoryName.equals("")) {
					toastMsg("The Category Name field can not be empty!");
				}else {
					if (!RegexTools.isLegalCategoryName(_CategoryName)) {
						toastMsg("Please take care of the format of Category Name, it can contain one or more underline( \"_\") or Chinese characters or English characters or numbers, but it can not start with numbers");
					}else {
						if (mCategoryBLL.existCategoryName(_CategoryName)) {
							toastMsg("The Category Name is duplicated, pleas choose another one! ");
						
						}else{
							Category _Category = new Category();
							_Category.setName(_CategoryName);
							if (mParentCategory.getName().equals("--Please Choose the Parent--")) {
								_Category.setParent(0);
							}else {
								_Category.setParent(mParentCategory.getId());
							}
							
							_Category.setState(0);
							mCategoryBLL.createCategory(_Category);
							toastMsg("sucessfully created!");
							CategoryAddOrEditActivity.this.setResult(1234);
							CategoryAddOrEditActivity.this.finish();
						}
					}
				}
			}
			
		});
	}
	
}
