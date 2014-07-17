package com.southsource.sundy_aaasistant_jack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.southsource.sundy_aaasistant_jack.activity.base.AFrameActivity;
import com.southsource.sundy_aaasistant_jack.adapter.AccountBookAdapter;
import com.southsource.sundy_aaasistant_jack.business.AccountBookBLL;
import com.southsource.sundy_aaasistant_jack.model.AccountBook;

public class AccountBookActivity extends AFrameActivity {

	ListView mAccountBookListView;
	BaseAdapter mMenuAdapter;
	BaseAdapter mAccountBookAdapter;
	String[] mMenuList = new String[]{"Add a New AccountBook"};
	private AccountBookBLL mAccountBookBLL= new AccountBookBLL(this);
	
	private AccountBook mSelectedAccountBook=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHeaderTitle("AccountBook Management");
		mMenuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mMenuList);
		setMenuAdapter(mMenuAdapter);
		setOnMenuItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					AccountBookActivity.this.openActivityForResult(AccountBookDialogActivity.class, 1221);
					break;
				default:
					break;
				}
				toggleMenu();
				
			}});
		
		
		//bindBody()
		ListView mAccountBookListView = new ListView(this);
		RelativeLayout.LayoutParams lpLayoutParams = 
				new  RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
		
		mAccountBookListView.setLayoutParams(lpLayoutParams);
		bindBody(mAccountBookListView);
//		String[] strings = new String[]{"A","B","C"};
//		ArrayAdapter _Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, strings);
		mAccountBookAdapter = new AccountBookAdapter(this, mAccountBookBLL);
		mAccountBookListView.setAdapter(mAccountBookAdapter);
		registerForContextMenu(mAccountBookListView);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		Log.i("jack", ""+v.toString()+menuInfo.toString());
		
		int position = ((AdapterContextMenuInfo)menuInfo).position;
		
		mSelectedAccountBook = (AccountBook) mAccountBookAdapter.getItem(position);
		menu.setHeaderTitle(mSelectedAccountBook.getName());
		menu.add(0, 1, 0, "Edit");
		menu.add(0, 2, 0, "Delete");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 1:
			Intent _Intent = new Intent(this,AccountBookDialogActivity.class);
			_Intent.putExtra("accountBookId", mSelectedAccountBook.getId());
			startActivityForResult(_Intent, 1111);
			break;
		case 2:
			mAccountBookBLL.deleteAccountBook(mSelectedAccountBook);
			mAccountBookAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("jack", "onActivityResult"+requestCode +resultCode );
		mAccountBookAdapter.notifyDataSetChanged();
	}


	
	
}
