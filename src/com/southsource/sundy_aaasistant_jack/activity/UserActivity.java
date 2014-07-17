package com.southsource.sundy_aaasistant_jack.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.southsource.sundy_aaasistant_jack.R;
import com.southsource.sundy_aaasistant_jack.activity.base.AFrameActivity;
import com.southsource.sundy_aaasistant_jack.adapter.UserAdapter;
import com.southsource.sundy_aaasistant_jack.business.UserBLL;
import com.southsource.sundy_aaasistant_jack.model.User;

@SuppressLint("NewApi")
public class UserActivity extends AFrameActivity {
	
	ListView mUserListView;
	BaseAdapter mMenuAdapter;
	BaseAdapter mUserAdapter;
	String[] mMenuList;
	private UserBLL mUserBLL= new UserBLL(this);
	
	private User mSelectedUser=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setHeaderTitle(getString(R.string.grid_item_user));

		
		//implement the body:
		bindBody(R.layout.user_body);
		mUserListView = (ListView) findViewById(R.id.lv_user);
		mUserAdapter = new UserAdapter(UserActivity.this, mUserBLL)	;
		mUserListView.setAdapter(mUserAdapter);
		registerForContextMenu(mUserListView);
		
		// implement the menu:
		mMenuList = getResources().getStringArray(R.array.SlideMenuUser);
		mMenuAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mMenuList);
		setMenuAdapter(mMenuAdapter);
		setOnMenuItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				// Add a new user
				case 0:
					UserActivity.this.openActivityForResult(UserDialogActivity.class, 123);
					break;
				case 1:
					
					break;
				default:
					break;
					
				}
				toggleMenu();
				
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("jack", "onActivityResult"+requestCode +resultCode );
		mUserAdapter.notifyDataSetChanged();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		Log.i("jack", ""+v.toString()+menuInfo.toString());
		
		int position = ((AdapterContextMenuInfo)menuInfo).position;
		
		mSelectedUser = (User) mUserAdapter.getItem(position);
		menu.setHeaderTitle(mSelectedUser.getName());
		menu.add(0, 1, 0, "Edit");
		menu.add(0, 2, 0, "Delete");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 1:
			Intent _Intent = new Intent(this,UserDialogActivity.class);
			_Intent.putExtra("userId", mSelectedUser.getId());
			startActivityForResult(_Intent, 1111);
			break;

		case 2:
			mUserBLL.deleteUser(mSelectedUser);
			mUserAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}


	
	
	
	

	
}
