package com.southsource.sundy_aaasistant_jack.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.southsource.sundy_aaasistant_jack.R;
import com.southsource.sundy_aaasistant_jack.adapter.base.ABaseAdapter;
import com.southsource.sundy_aaasistant_jack.business.AccountBookBLL;
import com.southsource.sundy_aaasistant_jack.model.AccountBook;

public class AccountBookAdapter extends ABaseAdapter {

	AccountBookBLL mAccountBookBLL = null;
	public AccountBookAdapter(Context pContext, AccountBookBLL pAccountBookBLL) {
		super(pContext);
		mAccountBookBLL = pAccountBookBLL;
		setList(mAccountBookBLL.getAllAccountBooks());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		AccountBook currenAccountBook = (AccountBook)getItem(position);
		if (convertView == null ) {
			convertView = getLayoutInflater().inflate(R.layout.account_book_item, null);
		}
		
		if (convertView.getTag() == null) {
			ViewHolder _ViewHolder = new ViewHolder();
			_ViewHolder.icon = (ImageView) convertView.findViewById(R.id.account_book_icon);
			_ViewHolder.text = (TextView) convertView.findViewById(R.id.account_book_text);			
			convertView.setTag(_ViewHolder);
		}

		ViewHolder _ViewHolder = (ViewHolder)convertView.getTag();
			
		if (currenAccountBook.getIsDefault()==0 ) {
			_ViewHolder.icon.setImageResource(R.drawable.account_book_default); 
		}else {
			_ViewHolder.icon.setImageResource(R.drawable.account_book_small_icon);
		}
			_ViewHolder.text.setText(currenAccountBook.getName());

		return convertView;
		
	}

	@Override
	public void notifyDataSetChanged() {
		// update the DataSet
		setList(mAccountBookBLL.getAllAccountBooks());
		super.notifyDataSetChanged();
	}
	
	class ViewHolder {
		public ImageView icon;
		public TextView text;
	}
}
