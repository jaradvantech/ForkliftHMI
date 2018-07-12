package com.example.administrator.ForkliftHMI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EditorToolsListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<EditorTool> EditorTools;

    public EditorToolsListAdapter(Context context, ArrayList<EditorTool> EditorTools) {
        this.mContext = context;
        this.EditorTools = EditorTools;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return EditorTools.size();
    }

    @Override
    public Object getItem(int position) {
        return EditorTools.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.editor_list_item, parent, false);
        EditorTool mObject = (EditorTool) getItem(position);

        ImageView thumbnailImageView = (ImageView) rowView.findViewById(R.id.EditorTool_imageView_icon);
        thumbnailImageView.setImageResource(mObject.textureResource);

        TextView titleTextView = (TextView) rowView.findViewById(R.id.EditorTool_textView_caption);
        titleTextView.setText(mObject.name);


        return rowView;
    }

}
