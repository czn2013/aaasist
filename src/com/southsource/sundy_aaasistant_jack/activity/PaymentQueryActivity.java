package com.southsource.sundy_aaasistant_jack.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.southsource.sundy_aaasistant_jack.R;
import com.southsource.sundy_aaasistant_jack.activity.base.AFrameActivity;
import com.southsource.sundy_aaasistant_jack.adapter.PaymentAdapter;
import com.southsource.sundy_aaasistant_jack.business.PaymentBLL;

public class PaymentQueryActivity extends AFrameActivity {

	ListView mPaymentListView;
	PaymentAdapter mPaymentAdapter;
	
	
	
	
	String[] mMenuList ;
	ArrayAdapter<String> mMenuAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHeaderTitle("Query Payments");
		mMenuList = getResources().getStringArray(R.array.SlideMenuPaymentQuery);
		mMenuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mMenuList);
		setOnMenuItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					Toast.makeText(PaymentQueryActivity.this, "switch default account book", Toast.LENGTH_LONG).show();
					break;
				default:
					break;
				}
				toggleMenu();
				
			}});
		//bindBody ListView
		bindBody(R.layout.payment_query);
		
		initUi();
		initVariable();
		bindData();
		bindHandler();
	}
	
		private void initUi(){
			mPaymentListView = (ListView)findViewById(R.id.payment_query_list);
		}
		
		private void initVariable(){
			
		}
		
		private void bindData(){
			mPaymentAdapter = new PaymentAdapter(this);
			mPaymentListView.setAdapter(mPaymentAdapter); 
		}
		
		private void bindHandler(){
			
		}
}
