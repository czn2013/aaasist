package com.southsource.sundy_aaasistant_jack.adapter;

import java.util.ArrayList;
import java.util.List;

import com.southsource.sundy_aaasistant_jack.R;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DashAdapter extends BaseAdapter {

	class DashItem{
		public int mImage;
		public int mText;
	}
	private int[] mImageRes = new int[]{R.drawable.grid_payout,R.drawable.grid_bill,
			R.drawable.grid_report,R.drawable.grid_account_book,
			R.drawable.grid_category,R.drawable.grid_user};
	private int[] mTextRes = new int[]{R.string.grid_item_payout,R.string.grid_item_bill,
			R.string.grid_item_report,R.string.grid_item_account_book,
			R.string.grid_item_category,R.string.grid_item_user};
	
	private List<DashItem> mList;
	private Context mContext ;
	
	public DashAdapter(Context pContext){
		mContext = pContext;
		mList = new ArrayList<DashItem>();
		for (int i = 0; i < 6; i++) {
			DashItem _DashItem = new DashItem();
			_DashItem.mImage= mImageRes[i];
			_DashItem.mText = mTextRes[i];
			mList.add(_DashItem);
		}
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Log.i("Dash Adapter", "getView() been called" );
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.dash_grid_item, null);
		}
		ImageView _Image = (ImageView)convertView.findViewById(R.id.grid_item_image);
		TextView _Text =(TextView)convertView.findViewById(R.id.grid_item_text);
		_Image.setImageResource(mList.get(position).mImage);
		_Text.setText(mList.get(position).mText);
		
		return convertView;
	}

}
