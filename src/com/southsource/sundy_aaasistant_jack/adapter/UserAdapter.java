package com.southsource.sundy_aaasistant_jack.adapter;

import com.southsource.sundy_aaasistant_jack.adapter.base.ABaseAdapter;
import com.southsource.sundy_aaasistant_jack.business.UserBLL;
import com.southsource.sundy_aaasistant_jack.model.User;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserAdapter extends ABaseAdapter{

	private UserBLL mUserBLL;
	
	public UserAdapter(Context pContext, UserBLL pUserBLL) {
		super(pContext);
		mUserBLL = pUserBLL;
		setList(mUserBLL.getAllUsers());
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null ) {
			convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
		}
		
		if (convertView.getTag() == null) {
			ViewHolder _ViewHolder = new ViewHolder();
			
			_ViewHolder.text = (TextView) convertView.findViewById(android.R.id.text1);
			_ViewHolder.text.setText(((User)getItem(position)).getName());
			convertView.setTag(_ViewHolder);
		}else{
			ViewHolder _ViewHolder = (ViewHolder)convertView.getTag();
			_ViewHolder.text.setText(((User)getItem(position)).getName());
		}
		return convertView;
	}
	
	
	
	@Override
	public void notifyDataSetChanged() {
		// update the DataSet
		setList(mUserBLL.getAllUsers());
		super.notifyDataSetChanged();
	}



	class ViewHolder {
		public TextView text;
	}

}
