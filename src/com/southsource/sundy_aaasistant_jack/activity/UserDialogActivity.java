package com.southsource.sundy_aaasistant_jack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.southsource.sundy_aaasistant_jack.R;
import com.southsource.sundy_aaasistant_jack.activity.base.ABaseDialogActivity;
import com.southsource.sundy_aaasistant_jack.business.UserBLL;
import com.southsource.sundy_aaasistant_jack.model.User;
import com.southsource.sundy_aaasistant_jack.util.RegexTools;

public class UserDialogActivity extends ABaseDialogActivity {
	private static final String TAG = "UserDialogActivity";

	private EditText mUserNameEditText;
	private UserBLL mUserBLL = new UserBLL(this);
	private Button mOkButton;
	private Button mCancelButton;
	private User mUser = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		inflateBody(R.layout.dialog_body_user);
		mUserNameEditText = (EditText)findViewById(R.id.dialog_body_et_username);
		mOkButton = (Button) findViewById(R.id.dialog_body_btn_ok);
		mCancelButton = (Button) findViewById(R.id.dialog_body_btn_cancel);
		
		Intent _Intent = getIntent();
		int _UserId = _Intent.getIntExtra("userId", -1);
		Log.i("jack", "UserId: "+_UserId);
		if (_UserId == -1) {
			setDialogTitle("Add a New User");
			mOkButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String _UserName = mUserNameEditText.getText().toString().trim();
					if (_UserName.equals("")) {
						Log.i(TAG, "2"+ _UserName);
						toastMsg("The User Name field can not be empty!");
					}else {
						if (!RegexTools.isLegalUserName(_UserName)) {
							Log.i(TAG, "3"+ _UserName);
							toastMsg("Please take care of the format of User Name, it can contain one or more underline( \"_\") or Chinese characters or English characters or numbers, but it can not start with numbers");
						}else {
							
							if (mUserBLL.existUserName(_UserName)) {
								toastMsg("The User Name is duplicated, pleas choose another one! ");
								Log.i(TAG, "4"+ _UserName);
							}else{
								User _User = new User();
								_User.setName(_UserName);
								_User.setState(0);
								mUserBLL.createUser(_User);
								toastMsg("sucessfully created!");
								UserDialogActivity.this.setResult(1234);
								UserDialogActivity.this.finish();
							}
						}
					}
				}
			});
			
			
		}else {
			mUser = mUserBLL.getUserById(_UserId);
			Log.i("jack", mUser.toString());
			setDialogTitle("Edit The User Infomation");
			
			mUserNameEditText.setText(mUser.getName());
			
			mOkButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String _NewName = mUserNameEditText.getText().toString().trim();
					if (_NewName.equals("")) {
						Log.i(TAG, "2"+ _NewName);
						toastMsg("The User Name field can not be empty!");
					}else {
						if (!RegexTools.isLegalUserName(_NewName)) {
							Log.i(TAG, "3"+ _NewName);
							toastMsg("Please take care of the format of User Name, it can contain one or more underline( \"_\") or Chinese characters or English characters or numbers, but it can not start with numbers");
						}else {
							if (!mUser.getName().equals(_NewName) && mUserBLL.existUserName(_NewName)) {
								toastMsg("Please Choose another Name!");
							}else if (!mUser.getName().equals(_NewName) && !mUserBLL.existUserName(_NewName)) {
								mUser.setName(_NewName);
								mUserBLL.updateUser(mUser);
							}else {
								UserDialogActivity.this.setResult(1234);
								UserDialogActivity.this.finish();
							}
						}
					}
				}
			});
		}
		
		mCancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UserDialogActivity.this.finish();
			}
		});
		
	}

	
	
}
