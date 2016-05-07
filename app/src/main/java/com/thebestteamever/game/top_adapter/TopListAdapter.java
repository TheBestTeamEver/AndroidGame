package com.thebestteamever.game.top_adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thebestteamever.game.R;
import com.thebestteamever.game.item.ListItem;

import java.util.List;

/**
 * Created by subzero on 05.05.16.
 */
public class TopListAdapter extends BaseAdapter {
    private List<ListItem> list;
    private LayoutInflater layoutInflater;

    public TopListAdapter(Context context, List<ListItem> list) {
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_list, parent, false);
        }

        ListItem number = getNumber(position);

        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(number.getNumber());

        if (position % 2 != 0) {
            view.setBackgroundColor(Color.parseColor("#aaaaaa"));
        } else {
            view.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        return view;
    }

    private ListItem getNumber(int position) {
        return (ListItem) getItem(position);
    }
}
