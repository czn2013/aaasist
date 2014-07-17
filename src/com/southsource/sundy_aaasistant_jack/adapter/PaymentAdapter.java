package com.southsource.sundy_aaasistant_jack.adapter;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.southsource.sundy_aaasistant_jack.R;
import com.southsource.sundy_aaasistant_jack.adapter.AccountBookAdapter.ViewHolder;
import com.southsource.sundy_aaasistant_jack.adapter.base.ABaseAdapter;
import com.southsource.sundy_aaasistant_jack.business.PaymentBLL;
import com.southsource.sundy_aaasistant_jack.business.UserBLL;
import com.southsource.sundy_aaasistant_jack.model.Payment;

public class PaymentAdapter extends ABaseAdapter {

	UserBLL mUserBLL;
	PaymentBLL mPaymentBLL ; 
	Date mLastDate;
	
	public PaymentAdapter(Context pContext) {
		super(pContext);
		mPaymentBLL = new PaymentBLL(pContext);
		setList(mPaymentBLL.getAllPayments());
		mUserBLL = new UserBLL(pContext);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = getLayoutInflater().inflate(R.layout.payment_query_list_item, null);
			viewHolder  = new ViewHolder();
			viewHolder.date = (TextView) convertView.findViewById(R.id.payment_query_date);
			viewHolder.category = (TextView) convertView.findViewById(R.id.payment_query_category);
			viewHolder.user = (TextView) convertView.findViewById(R.id.payment_query_users);
			viewHolder.money = (TextView) convertView.findViewById(R.id.payment_query_money);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		Payment payment = (Payment)getItem(position);
		if (mLastDate == null || !mLastDate.equals(payment.getPaymentDate())) {
			mLastDate = payment.getPaymentDate();
			//TODO need to change
			viewHolder.date.setVisibility(View.VISIBLE);
			viewHolder.date.setText(payment.getPaymentDate().toGMTString());
		} else {
			viewHolder.date.setVisibility(View.GONE);
		}
		
		viewHolder.category.setText(payment.getCategoryName());
		
		ArrayList<Integer> userIds = payment.getUserIds();
		for (int i = 0; i < userIds.size(); i++) {
			viewHolder.user.append(mUserBLL.getUserById(userIds.get(i).intValue()).getName() + " ");
		}
		
		viewHolder.money.setText(" " + payment.getCount());
		
		return convertView;
	}
	
	class ViewHolder {
		TextView date;
		TextView category;
		TextView user;
		TextView money;
	}

}
