package com.southsource.sundy_aaasistant_jack.activity.base;


import com.southsource.sundy_aaasistant_jack.R;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class AFrameActivity extends ABaseActivity {

	//main RelativeLayout
	RelativeLayout mMainLayout;
	//main body
	FrameLayout mMainBody;
	//header 
	RelativeLayout mHeader;
	TextView mHeaderTitle;
	ImageButton mHeaderBack;
	ListView mAdapter;
	
	//footer
	LinearLayout mFooter;
	ImageButton mMenuButton;
	ListView mMenuListView;
	boolean mIsMenuOpen = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		initUi();
		bindData();
		initHandler();
	}
	
	
	private void initUi() {
		// TODO Auto-generated method stub
		mMainLayout= (RelativeLayout) findViewById(R.id.mainlayout);
		
		mMainBody= (FrameLayout)findViewById(R.id.main_body);
		
		mHeader = (RelativeLayout) findViewById(R.id.header);
		mHeaderTitle = (TextView) findViewById(R.id.tv_header_title);
		mHeaderBack = (ImageButton) findViewById(R.id.ib_header_back);
		
		mFooter =(LinearLayout) findViewById(R.id.footer);
		mMenuButton = (ImageButton) findViewById(R.id.ib_footer);
		mMenuListView = (ListView) findViewById( R.id.lv_footer_menu);
	}

	private void bindData() {
		// TODO Auto-generated method stub
		
	}
	
	
	private void initHandler() {
		// TODO Auto-generated method stub
		mHeaderBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AFrameActivity.this.finish();
			}
		});
		
		mFooter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				toggleMenu();
			}
			
		    });
		mMenuButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				toggleMenu();
			}
		});
	}
	
	

	protected void toggleMenu(){
		if (mIsMenuOpen) {
			RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,130);
			_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			mFooter.setLayoutParams(_LayoutParams);
			mIsMenuOpen = false;
		}else {
			RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
			_LayoutParams.addRule(RelativeLayout.BELOW, R.id.header);
			mFooter.setLayoutParams(_LayoutParams);
			mIsMenuOpen =true;
		}
	}
	
	protected void setIsDashBoard(){
		mHeaderBack.setVisibility(View.GONE);
	}
	
///////work that need to be done when override the activity ////////
	
	protected void setHeaderTitle(CharSequence header_title) {
		mHeaderTitle.setText(header_title);
	}
	
	protected void setMenuAdapter(ListAdapter pAdapter){
		mMenuListView.setAdapter(pAdapter);
	}
	
	protected void  setOnMenuItemClickListener(OnItemClickListener pOnItemClickListener ) {
		mMenuListView.setOnItemClickListener(pOnItemClickListener);
	}
	
	protected void bindBody(int resId){
		View body= View.inflate(this,resId , null);
		Log.i("jack", "AFrameActivity.bindBody() ...");
		mMainBody.addView(body);
	}
	
	protected void bindBody(View pView){
		
		mMainBody.addView(pView);
	}
	
	protected void hideMenuBar(){
		RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,0);
		_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		mFooter.setLayoutParams(_LayoutParams);
	}
	
///////work that need to be done when override the activity /////////////
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		//return super.onCreateOptionsMenu(menu);
		return false;
	}



	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
}
