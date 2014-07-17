package com.southsource.sundy_aaasistant_jack.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.R.integer;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.southsource.sundy_aaasistant_jack.R;
import com.southsource.sundy_aaasistant_jack.activity.base.AFrameActivity;
import com.southsource.sundy_aaasistant_jack.adapter.CategoryAdapter;
import com.southsource.sundy_aaasistant_jack.business.AccountBookBLL;
import com.southsource.sundy_aaasistant_jack.business.CategoryBLL;
import com.southsource.sundy_aaasistant_jack.business.PaymentBLL;
import com.southsource.sundy_aaasistant_jack.business.UserBLL;
import com.southsource.sundy_aaasistant_jack.model.AccountBook;
import com.southsource.sundy_aaasistant_jack.model.Category;
import com.southsource.sundy_aaasistant_jack.model.Payment;
import com.southsource.sundy_aaasistant_jack.model.User;
import com.southsource.sundy_aaasistant_jack.util.DateTools;

public class PaymentEditActivity extends AFrameActivity {

	// Ui
	Button mAcccountBookButton, mCategoryButton, mAmountButton,
			mDatetimeButton, mTypeButton, mConsumersButton, mOkButton,
			mCancelButton;
	EditText mAccountBookEditText, mAmountEditText, mTypeEditText,
			mDatetimeEditText, mConsumersEditText;

	MultiAutoCompleteTextView mMultiAutoCompleteTextView;

	AccountBookBLL mAccountBookBLL;
	PaymentBLL mPaymentBLL;
	CategoryBLL mCategoryBLL;
	UserBLL mUserBLL;

	ArrayAdapter<AccountBook> mAccountBookAdapter;
	AlertDialog mCategoryDialog;
	CategoryAdapter mCategoryAdapter;
	ArrayAdapter<User> mUserAdapter;

	String[] mTypes;
	ArrayList<User> mUsers;

	// the choice
	int mAccountBookId, mCategoryId, mTypeId;
	double mPadNum;
	Date mDate = new Date();
	ArrayList<Integer> mUserIds;
	int mType = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		hideMenuBar();
		bindBody(R.layout.payment_add_or_edit);

		initUi();
		initVariable();
		bindData();
		bindHandler();

		Intent _Intent = getIntent();
		int _PaymentId = _Intent.getIntExtra(Payment.COL_ID, -1);
		if (_PaymentId == -1) {
			setHeaderTitle("Payment Create");
			bindDefaultData();
		} else {
			setHeaderTitle("Payment Update");
			bindPaymentData(_PaymentId);
		}
	}

	private void initUi() {

		mAcccountBookButton = (Button) findViewById(R.id.payment_edit_btn_accountbook);
		mCategoryButton = (Button) findViewById(R.id.payment_edit_btn_category);
		mAmountButton = (Button) findViewById(R.id.payment_edit_btn_amount);
		mDatetimeButton = (Button) findViewById(R.id.payment_edit_btn_datetime);
		mTypeButton = (Button) findViewById(R.id.payment_edit_btn_type);
		mConsumersButton = (Button) findViewById(R.id.payment_edit_btn_consumers);
		mOkButton = (Button) findViewById(R.id.payment_edit_btn_ok);
		mCancelButton = (Button) findViewById(R.id.payment_edit_btn_cancel);

		mAccountBookEditText = (EditText) findViewById(R.id.payment_edit_et_accountbook);
		mAmountEditText = (EditText) findViewById(R.id.payment_edit_et_amount);
		mTypeEditText = (EditText) findViewById(R.id.payment_edit_et_type);
		mConsumersEditText = (EditText) findViewById(R.id.payment_edit_et_consumers);
		mDatetimeEditText = (EditText) findViewById(R.id.payment_edit_et_datetime);
		mMultiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.payment_edit_mact_category);
	}

	private void initVariable() {
		mPaymentBLL = new PaymentBLL(this);
		mCategoryBLL = new CategoryBLL(this);
		mAccountBookBLL = new AccountBookBLL(this);
		mUserBLL = new UserBLL(this);
		mPadNum = 0.0;
		mTypes = getResources().getStringArray(R.array.type_array);
		mUserIds = new ArrayList<Integer>();
	}

	private void bindData() {
		ArrayList<Category> _Categories = mCategoryBLL.getAllCategories();
		ArrayAdapter<Category> _Adapter = new ArrayAdapter<Category>(this,
				android.R.layout.simple_list_item_1, _Categories);
		mMultiAutoCompleteTextView.setAdapter(_Adapter);
		mMultiAutoCompleteTextView
				.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}

	private void bindHandler() {
		// choose the account book
		mAcccountBookButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder _Builder = new AlertDialog.Builder(
						PaymentEditActivity.this);
				ArrayList<AccountBook> _AccountBooks = mAccountBookBLL
						.getAllAccountBooks();
				mAccountBookAdapter = new ArrayAdapter<AccountBook>(
						PaymentEditActivity.this,
						android.R.layout.simple_list_item_1, _AccountBooks);
				DialogInterface.OnClickListener _Listener = new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						AccountBook mAccountBook = (AccountBook) mAccountBookAdapter
								.getItem(which);
						mAccountBookId = mAccountBook.getId();
						mAccountBookEditText.setText(mAccountBook.getName());
					}

				};
				_Builder.setAdapter(mAccountBookAdapter, _Listener);
				_Builder.create().show();

			}
		});

		//
		mCategoryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder _Builder = new AlertDialog.Builder(
						PaymentEditActivity.this);
				View categoryView = getLayoutInflater().inflate(
						R.layout.category_body, null);
				ExpandableListView expandableListView = (ExpandableListView) categoryView
						.findViewById(R.id.category_elv_category);
				mCategoryAdapter = new CategoryAdapter(
						PaymentEditActivity.this, mCategoryBLL);
				expandableListView.setAdapter(mCategoryAdapter);
				expandableListView.setGroupIndicator(null);
				expandableListView
						.setOnChildClickListener(new OnChildClickListener() {

							@Override
							public boolean onChildClick(
									ExpandableListView parent, View v,
									int groupPosition, int childPosition,
									long id) {
								Category childCategory = (Category) mCategoryAdapter
										.getChild(groupPosition, childPosition);
								mCategoryId = childCategory.getId();
								mMultiAutoCompleteTextView
										.setText(childCategory.getName());
								mCategoryDialog.dismiss();
								return false;

							}
						});

				expandableListView
						.setOnGroupClickListener(new OnGroupClickListener() {

							@Override
							public boolean onGroupClick(
									ExpandableListView parent, View v,
									int groupPosition, long id) {
								Category groupCategory = (Category) mCategoryAdapter
										.getGroup(groupPosition);
								if (mCategoryBLL
										.getCategoryChild(groupCategory).size() > 0) {
									if (parent.isGroupExpanded(groupPosition)) {
										parent.collapseGroup(groupPosition);
									} else {
										parent.expandGroup(groupPosition);
									}
								} else {
									mCategoryId = groupCategory.getId();
									mMultiAutoCompleteTextView
											.setText(groupCategory.getName());
									mCategoryDialog.dismiss();
								}
								return false;
							}
						});
				_Builder.setView(categoryView);
				mCategoryDialog = _Builder.create();
				mCategoryDialog.show();
			}
		});
		// prompt a dialog keyboard
		mAmountButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent _Intent = new Intent(PaymentEditActivity.this,
						NumberInputDialogActivity.class);
				startActivityForResult(_Intent, 1344);
			}
		});
		// prompt a datepicker
		mDatetimeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Date _CurrentDate = new Date();
				Calendar _Calendar = new GregorianCalendar();
				_Calendar.setTime(_CurrentDate);
				int _Year = _Calendar.get(Calendar.YEAR);
				int _MonthOfYear = _Calendar.get(Calendar.MONTH);
				int _DayOfMonth = _Calendar.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog.OnDateSetListener _OnDateSetListener = new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						Calendar _Calendar = new GregorianCalendar();
						_Calendar.set(Calendar.YEAR, year);
						_Calendar.set(Calendar.MONTH, monthOfYear);
						_Calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
						mDate = _Calendar.getTime();
						mDatetimeEditText.setText(DateTools
								.getFormatDate(mDate));
					}

				};
				DatePickerDialog _DatePickerDialog = new DatePickerDialog(
						PaymentEditActivity.this, _OnDateSetListener, _Year,
						_MonthOfYear, _DayOfMonth);
				_DatePickerDialog.show();
			}
		});

		// prompt a dialog pad to choose the type
		mTypeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder _Builder = new AlertDialog.Builder(
						PaymentEditActivity.this);

				ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(
						PaymentEditActivity.this,
						android.R.layout.simple_list_item_1, mTypes);
				DialogInterface.OnClickListener _Listener = new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						mTypeEditText.setText(mTypes[which]);
						mType = which;
					}

				};
				_Builder.setAdapter(_Adapter, _Listener);
				_Builder.create().show();
			}
		});
		// choose the consumer, the first person is the payer.
		mConsumersButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder _Builder = new AlertDialog.Builder(
						PaymentEditActivity.this);
				mUsers = mUserBLL.getAllUsers();
				mUserAdapter = new ArrayAdapter<User>(PaymentEditActivity.this,
						android.R.layout.simple_list_item_1, mUsers);

				DialogInterface.OnClickListener _Listener = new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						User _User = (User) mUserAdapter.getItem(which);
						mUserIds.add(_User.getId());
						mConsumersEditText.append("  " + _User.getName());
					}
				};
				_Builder.setAdapter(mUserAdapter, _Listener);
				_Builder.create().show();
			}
		});

		mOkButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Double.valueOf(mAmountEditText.getText().toString()) == 0) {
					toastMsg("You'd better input a amount greater than 0 !");
				} else if (mMultiAutoCompleteTextView.getText().toString()
						.equals("")) {
					toastMsg("Please choose a category!");
				} else if (mTypeEditText.getText().toString().equals("")) {
					toastMsg("Please choose a Type!");
				} else if (mConsumersEditText.getText().toString().equals("")) {
					toastMsg("Please choose the consumers!");
				} else {
					Payment _Payment = new Payment();
					_Payment.setAccountbookId(mAccountBookId);
					_Payment.setCategoryId(mCategoryId);
					_Payment.setCount(Double.valueOf(mAmountEditText.getText()
							.toString()));
					_Payment.setPaymentDate(mDate);
					_Payment.setType(0);
					_Payment.setUserIds(mUserIds);
					mPaymentBLL.createPayment(_Payment);
					PaymentEditActivity.this.finish();
					toastMsg("A new Payment Item been inserted!");
				}

			}
		});

		mCancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PaymentEditActivity.this.finish();
			}
		});

	}

	private void bindDefaultData() {

		mAccountBookEditText.setText("default");
		mAmountEditText.setText("");
		mTypeEditText.setText("GoDutch");
		mConsumersEditText.setText("");
		mMultiAutoCompleteTextView.setText("");
		mDatetimeEditText.setText(DateTools.getFormatDate(new Date()));
	}

	private void bindPaymentData(int pPaymentId) {
		Payment _Payment = mPaymentBLL.getPaymentById(pPaymentId);
		mAccountBookEditText.setText(_Payment.getAccountbookName());
		mAmountEditText.setText("" + _Payment.getCount());
		mTypeEditText.setText(_Payment.getType());
		mConsumersEditText.setText("");
		for (int userid : mUserIds) {
			mConsumersEditText.append(mUserBLL.getUserById(userid).getName());
		}
		mMultiAutoCompleteTextView.setText(_Payment.getCategoryName());
		mDatetimeEditText.setText(DateTools.getFormatDate(_Payment
				.getPaymentDate()));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1344) {
			mPadNum = data.getDoubleExtra("mNumber", 0.0);
			mAmountEditText.setText(String.valueOf(mPadNum));
		}
	}
}
