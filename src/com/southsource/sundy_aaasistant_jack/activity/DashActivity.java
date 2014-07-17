package com.southsource.sundy_aaasistant_jack.activity;

import com.southsource.sundy_aaasistant_jack.R;
import com.southsource.sundy_aaasistant_jack.activity.base.AFrameActivity;
import com.southsource.sundy_aaasistant_jack.adapter.DashAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

public class DashActivity extends AFrameActivity {

	GridView mDashBodyGridView;
	ListAdapter mMenuAdapter;


	String[] mMenuList;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("Jack", "DashActivity.onCreate()  ..."); 
		bindBody(R.layout.dash_body);
		mDashBodyGridView =(GridView)findViewById(R.id.dash_grid);
		mDashBodyGridView.setBackgroundColor(256);
		mDashBodyGridView.setAdapter(new DashAdapter(this));
		
		mDashBodyGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					openActivity(PaymentEditActivity.class);
					break;

				case 1:
					openActivity(PaymentQueryActivity.class);
					break;
					
				case 2:
					openActivity(PaymentStatisticsActivity.class);
					break;
					
				case 3:
					 openActivity(AccountBookActivity.class);
					break;
					
				case 4:
					openActivity(CategoryActivity.class);
					break;
					
				case 5:
				    openActivity(UserActivity.class);
					break;
				default:
					break;
				}
			}
		});
		
		//do what need to be done first
		setIsDashBoard();
		setHeaderTitle(getString(R.string.welcome_message));
		mMenuList = getResources().getStringArray(R.array.SlideMenuDash);
		mMenuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mMenuList);
		setMenuAdapter(mMenuAdapter);
		setOnMenuItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.i("Jack", "DashActivity.setOnMenuItemClickListener() ..."+ view.getId()+",position:" + position);
				switch (position) {
				//choose the data backup option.
				case 0:

					//Toast.makeText(DashActivity.this, "��ݱ���", Toast.LENGTH_LONG).show();
					toggleMenu();
					break;
				default:
					break;
				}
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}



}
