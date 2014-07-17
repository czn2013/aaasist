package com.southsource.sundy_aaasistant_jack.adapter.base;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;

import android.widget.BaseAdapter;

public abstract class ABaseAdapter extends BaseAdapter {

	private List<?> mList;
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	
	public ABaseAdapter(Context pContext)
	{
		mContext = pContext;
		mLayoutInflater = LayoutInflater.from(mContext);
	}
	
	public LayoutInflater getLayoutInflater()
	{
		return mLayoutInflater;
	}
	
	public Context getContext()
	{
		return mContext;
	}
	
	public List<?> getList()
	{
		return mList;
	}
	
	public void setList(List<?> pList) {
		mList = pList;
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int pPosition) {
		return mList.get(pPosition);
	}

	@Override
	public long getItemId(int pPosition) {
		return pPosition;
	}

}
