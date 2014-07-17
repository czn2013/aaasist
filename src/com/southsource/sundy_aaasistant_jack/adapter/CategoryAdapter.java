package com.southsource.sundy_aaasistant_jack.adapter;

import java.util.List;
import com.southsource.sundy_aaasistant_jack.R;
import com.southsource.sundy_aaasistant_jack.business.CategoryBLL;
import com.southsource.sundy_aaasistant_jack.model.Category;



import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends BaseExpandableListAdapter {
	
	/* (non-Javadoc)
	 * @see android.widget.BaseExpandableListAdapter#notifyDataSetChanged()
	 */
	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}

	private Context mContext;
	private CategoryBLL mCategoryBLL;
	
	private List<Category> mGroup;

	
	public CategoryAdapter(Context pContext, CategoryBLL pCategoryBLL) {
		// TODO Auto-generated constructor stub
		mContext = pContext;
		mCategoryBLL = pCategoryBLL;
		mGroup = mCategoryBLL.getCategoryGoup();
	}
	
	
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return mGroup.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		Category _Category = (Category)mGroup.get(groupPosition);
		int _Count = mCategoryBLL.getCategoryChild(_Category).size();
		return _Count;
		
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return mGroup.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		Category _Category = (Category)mGroup.get(groupPosition);
		return mCategoryBLL.getCategoryChild(_Category).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		GroupViewHolder _GroupViewHolder;
		//�޷��Ż�������why
		//if (convertView == null ) {
			_GroupViewHolder= new GroupViewHolder();
			convertView = View.inflate(mContext, R.layout.category_group, null);
			//_GroupViewHolder.icon = (ImageView) convertView.findViewById(R.id.category_elv_group_icon);
			_GroupViewHolder.name = (TextView) convertView.findViewById(R.id.category_elv_group_name);
			_GroupViewHolder.sumary = (TextView) convertView.findViewById(R.id.category_elv_group_sumary);
		//}else {
			//_GroupViewHolder = (GroupViewHolder) convertView.getTag();
		//}
		Category _Category = (Category)getGroup(groupPosition);
		
		//_GroupViewHolder.icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_big));
		_GroupViewHolder.name.setText(_Category.getName());
		int childCount = getChildrenCount(groupPosition);
		if ( childCount>0) {
			_GroupViewHolder.sumary.setText("" + childCount);
		}else {
			_GroupViewHolder.sumary.setText("");
		}
		
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ChildViewHolder _ChildViewHolder = new ChildViewHolder();
		if (convertView == null ) {
			convertView = View.inflate(mContext, R.layout.category_child, null);
			_ChildViewHolder.name =(TextView) convertView.findViewById(R.id.category_elv_child_name);
			convertView.setTag(_ChildViewHolder);
		}else {
			_ChildViewHolder= (ChildViewHolder)convertView.getTag();
		}
		Category _Category =(Category) getChild(groupPosition, childPosition);
		_ChildViewHolder.name.setText(_Category.getName());
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		
		
		return false;
	}
	
	class GroupViewHolder{
		//ImageView icon;
		TextView name;
		TextView sumary;
	}
	
	class ChildViewHolder{
		TextView name;
	}
	
}
