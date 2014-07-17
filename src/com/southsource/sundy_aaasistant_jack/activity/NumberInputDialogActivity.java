package com.southsource.sundy_aaasistant_jack.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.southsource.sundy_aaasistant_jack.R;
import com.southsource.sundy_aaasistant_jack.activity.base.ABaseDialogActivity;

public class NumberInputDialogActivity extends ABaseDialogActivity implements android.view.View.OnClickListener{
	
	TextView mNumberShow;
	Button mButton1, mButton2, mButton3, mButton4, mButton5, mButton6,
		mButton7, mButton8, mButton9, mButton0, mButtonPoint,mButtonOk;
	ImageButton  mButtonBack;
	String mNumberString;
	boolean isPoint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		inflateBody(R.layout.number_input_pad);
		setDialogTitle("Input The Number!");
		initUi();
		initVariables();
		bindHandlers();
	}
	
	private void initUi(){
		mNumberShow= (TextView) findViewById(R.id.number_input_pad_show);
		mButton0 = (Button)findViewById(R.id.number_input_pad_0);
		mButton1 = (Button)findViewById(R.id.number_input_pad_1);
		mButton2 = (Button)findViewById(R.id.number_input_pad_2);
		mButton3 = (Button)findViewById(R.id.number_input_pad_3);
		mButton4 = (Button)findViewById(R.id.number_input_pad_4);
		mButton5 = (Button)findViewById(R.id.number_input_pad_5);
		mButton6 = (Button)findViewById(R.id.number_input_pad_6);
		mButton7 = (Button)findViewById(R.id.number_input_pad_7);
		mButton8 = (Button)findViewById(R.id.number_input_pad_8);
		mButton9 = (Button)findViewById(R.id.number_input_pad_9);
		mButtonPoint = (Button)findViewById(R.id.number_input_pad_point);
		mButtonBack= (ImageButton)findViewById(R.id.number_input_pad_back);
		mButtonOk= (Button)findViewById(R.id.number_input_pad_ok);
	}
	
	private void initVariables() {
		mNumberString = "0";
	}
	
	private void bindHandlers(){
		mButton0.setOnClickListener(this);
		mButton1.setOnClickListener(this);
		mButton2.setOnClickListener(this);
		mButton3.setOnClickListener(this);
		mButton4.setOnClickListener(this);
		mButton5.setOnClickListener(this);
		mButton6.setOnClickListener(this);
		mButton7.setOnClickListener(this);
		mButton8.setOnClickListener(this);
		mButton9.setOnClickListener(this);
		mButtonPoint.setOnClickListener(this);
		mButtonBack.setOnClickListener(this);
		mButtonOk.setOnClickListener(this);
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.number_input_pad_0:
			if (!mNumberString.equals("0")) {
				mNumberString+="0";
			}
			break;
		case R.id.number_input_pad_1:
			if (!mNumberString.equals("0")) {
				mNumberString+="1";
			}else {
				mNumberString ="1";
			}
			break;
		case R.id.number_input_pad_2:
			if (!mNumberString.equals("0")) {
				mNumberString+="2";
			}else {
				mNumberString = "2";
			}
			break;
		case R.id.number_input_pad_3:
			if (!mNumberString.equals("0")) {
				mNumberString+="3";
			}else {
				mNumberString = "3";
			}
			break;
		case R.id.number_input_pad_4:
			if (!mNumberString.equals("0")) {
				mNumberString+="4";
			}else {
				mNumberString = "4";
			}
			break;
		case R.id.number_input_pad_5:
			if (!mNumberString.equals("0")) {
				mNumberString+="5";
			}else {
				mNumberString = "5";
			}
			break;
		case R.id.number_input_pad_6:
			if (!mNumberString.equals("0")) {
				mNumberString+="6";
			}else {
				mNumberString = "6";
			}
			break;
		case R.id.number_input_pad_7:
			if (!mNumberString.equals("0")) {
				mNumberString+="7";
			}else {
				mNumberString = "7";
			}
			break;
		case R.id.number_input_pad_8:
			if (!mNumberString.equals("0")) {
				mNumberString+="8";
			}else {
				mNumberString = "8";
			}
			break;
		case R.id.number_input_pad_9:
			if (!mNumberString.equals("0")) {
				mNumberString+="9";
			}else {
				mNumberString = "9";
			}
			break;
		case R.id.number_input_pad_point:
			if (!mNumberString.contains(".")) {
				mNumberString+=".";
			}
			break;
		case R.id.number_input_pad_back:
			if (!mNumberString.equals("0")) {
				mNumberString= mNumberString.substring(0, mNumberString.length()-1);
			}
			break;
		case R.id.number_input_pad_ok:
			Intent _Intent =new Intent();
			double _Value = Double.valueOf(mNumberString);
			_Intent.putExtra("mNumber", _Value);
			setResult(1344, _Intent);
			Log.i("jack", "ok!");
			finish();
			break;
		default:
			break;
		}
		mNumberShow.setText(mNumberString);
	}

	
}
