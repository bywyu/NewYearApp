package org.uDevelop.newyearapp;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondListAdapter extends BaseAdapter {
	private Context mContext;
	private DatabaseAdapter mDbAdapter;
	private LayoutInflater mInflater;
	private int mCategoryId;
	int[] mItemsId; 
	
	static class ViewHolder {
        public TextView LikeCountTextView;
        public int category;
    }
	
	
	public SecondListAdapter(Context context, DatabaseAdapter databaseAdapter, 
					int[] itemsId, int categoryId) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mDbAdapter = databaseAdapter;
		mCategoryId = categoryId;
		mItemsId = itemsId;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		View view = convertView;
		ItemInfo item = mDbAdapter.getContentItem(mCategoryId, mItemsId[position]);
		if (view == null) {
			view = mInflater.inflate(R.layout.list_view_item, null);
            holder = new ViewHolder();
            TextView name = (TextView)view.findViewById(R.id.list_view_item_text);
            name.setText(item.name);
            ImageView icon = (ImageView)view.findViewById(R.id.list_view_item_icon);
            Class res = R.drawable.class;
            int imageId = 0;
            try {
            	imageId= res.getField(item.icon).getInt(null);
            }
            catch (Exception ex) {
            	Log.w("CustomListAdapter", ex.getMessage());
            }
            icon.setImageResource(imageId);
            holder.LikeCountTextView = (TextView)view.findViewById(R.id.list_view_like_num);
            holder.category = mCategoryId;
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
		holder.LikeCountTextView.setText(Integer.toString(item.likeCount));
        return view;
    }
	
	@Override
	  public int getCount() {
		return mItemsId.length;	    
	  }

	 
	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public int getCategory() {
		return mCategoryId;
	}

}
