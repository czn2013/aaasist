package com.southsource.sundy_aaasistant_jack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.southsource.sundy_aaasistant_jack.R;
import com.southsource.sundy_aaasistant_jack.activity.base.ABaseDialogActivity;
import com.southsource.sundy_aaasistant_jack.business.AccountBookBLL;
import com.southsource.sundy_aaasistant_jack.model.AccountBook;
import com.southsource.sundy_aaasistant_jack.util.RegexTools;

public class AccountBookDialogActivity extends ABaseDialogActivity {
	private static final String TAG = "jack";

	private EditText mAccountBookEditText;
	private AccountBookBLL mAccountBookBLL = new AccountBookBLL(this);
	private CheckBox mCheckBox;
	private Button mOkButton;
	private Button mCancelButton;
	private AccountBook mAccountBook = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i(TAG,"AccouontBookDialogActivity.onCreate()");
		inflateBody(R.layout.dialog_body_account_book);
		mAccountBookEditText = (EditText)findViewById(R.id.dialog_body_et_accountbook_name);
		mCheckBox = (CheckBox) findViewById(R.id.dialog_body_cb_default);
		mOkButton = (Button) findViewById(R.id.dialog_body_btn_ok);
		mCancelButton = (Button) findViewById(R.id.dialog_body_btn_cancel);
		
		Intent _Intent = getIntent();
		int _AccountBookId = _Intent.getIntExtra("accountBookId", -1);
		Log.i("jack", "accountBookId: "+_AccountBookId);
		if (_AccountBookId == -1) {
			setDialogTitle("Add a New AccountBook");
			mOkButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String _AccountBookName = mAccountBookEditText.getText().toString().trim();
					if (_AccountBookName.equals("")) {
						Log.i(TAG, "2"+ _AccountBookName);
						toastMsg("The AccountBook Name field can not be empty!");
					}else {
						if (!RegexTools.isLegalAccountBookName(_AccountBookName)) {
							Log.i(TAG, "3"+ _AccountBookName);
							toastMsg("Please take care of the format of AccountBook Name, it can contain one or more underline( \"_\") or Chinese characters or English characters or numbers, but it can not start with numbers");
						}else {
							if (mAccountBookBLL.existAccountBookName(_AccountBookName)) {
								toastMsg("The AccountBook Name is duplicated, pleas choose another one! ");
								Log.i(TAG, "4"+ _AccountBookName);
							}else{
								AccountBook _AccountBook = new AccountBook();
								_AccountBook.setName(_AccountBookName);
								_AccountBook.setState(0);
								mAccountBookBLL.createAccountBook(_AccountBook);
								if (mCheckBox.isChecked()) {
									mAccountBookBLL.setDefaultAccountBook(_AccountBook);
								}
								toastMsg("sucessfully created!");
								AccountBookDialogActivity.this.setResult(1234);
								AccountBookDialogActivity.this.finish();
							}
						}
					}
				}
			});
			
			
		}else {
			mAccountBook = mAccountBookBLL.getAccountBookById(_AccountBookId);
			//Log.i("jack", mAccountBook.toString());
			setDialogTitle("Edit The AccountBook Infomation");
			
			mAccountBookEditText.setText(mAccountBook.getName());
			if (mAccountBook.getIsDefault() ==0) {
				mCheckBox.setChecked(true);
			}else {
				mCheckBox.setChecked(false);
			}
			
			mOkButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String _NewName = mAccountBookEditText.getText().toString().trim();
					if (_NewName.equals("")) {
						Log.i(TAG, "2"+ _NewName);
						toastMsg("The AccountBook Name field can not be empty!");
					}else {
						if (!RegexTools.isLegalAccountBookName(_NewName)) {
							Log.i(TAG, "3"+ _NewName);
							toastMsg("Please take care of the format of AccountBook Name, it can contain one or more underline( \"_\") or Chinese characters or English characters or numbers, but it can not start with numbers");
						}else {
							if (mAccountBook.getName().equals(_NewName) && mAccountBook.getIsDefault()==1 && mCheckBox.isChecked()) {
								mAccountBookBLL.setDefaultAccountBook(mAccountBook);
								AccountBookDialogActivity.this.setResult(1234);
								AccountBookDialogActivity.this.finish();
							}else if (!mAccountBook.getName().equals(_NewName) && mAccountBookBLL.existAccountBookName(_NewName)) {
								toastMsg("Please Choose another Name!");
							}else if (!mAccountBook.getName().equals(_NewName) && !mAccountBookBLL.existAccountBookName(_NewName)) {
								mAccountBook.setName(_NewName);
								mAccountBookBLL.updateAccountBook(mAccountBook);
								if (mCheckBox.isChecked()) {
									mAccountBookBLL.setDefaultAccountBook(mAccountBook);
								}
								AccountBookDialogActivity.this.setResult(1234);
								AccountBookDialogActivity.this.finish();
							}
						}
					}
				}
			});
		}
		
		mCancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AccountBookDialogActivity.this.finish();
			}
		});
		
	}

	
	
}
