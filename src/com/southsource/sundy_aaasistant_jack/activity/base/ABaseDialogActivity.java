package com.southsource.sundy_aaasistant_jack.activity.base;

import com.southsource.sundy_aaasistant_jack.R;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class ABaseDialogActivity extends ABaseActivity {


	RelativeLayout mBodyRelativeLayout;
	LinearLayout mButtonLinearLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog);
		initUi();
		bindData();
		bindHandler();
	}
	
	private void initUi() {
		// TODO Auto-generated method stub
		mBodyRelativeLayout = (RelativeLayout) findViewById(R.id.dialog_body);

	}

	private void bindData() {
		// TODO Auto-generated method stub
		
	}

	private void bindHandler() {
		// TODO Auto-generated method stub
		
	}
	
	
	////////////////////////implement before override the ABaseDialogActivity/////////////////////////////
	public void inflateBody(int pResId){
		View _BodyView = getLayoutInflater().inflate(pResId, null);
		
		RelativeLayout.LayoutParams _LayoutParams = 
				new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		_LayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		mBodyRelativeLayout.addView(_BodyView,_LayoutParams );
	}
	
	
	public void  setDialogTitle(CharSequence pTitle){
		setTitle(pTitle);
	}
	

	
	
	
	
}
