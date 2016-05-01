package com.wallacekwan.randomizer;

/**
 * Created by wallacekwan on 2016-04-28.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListItemAdapter extends ArrayAdapter<ListItem> {


    public ListItemAdapter(Context context, ArrayList<ListItem> listItem) {

        super(context, 0, listItem);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get data item for position
        final ListItem listItem = getItem(position);

        // Check if existing view is being reused, otherwise inflate view
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_item, parent, false);

        }

        // Lookup view for data population
        TextView tvPosition = (TextView) convertView.findViewById(R.id.tvPosition);
        TextView tvText = (TextView) convertView.findViewById(R.id.tvText);

        // Populate data into template view using data object
        tvPosition.setText(Integer.toString(listItem.position));
        tvText.setText(listItem.text);



        // Return completed view to render on screen
        return convertView;

    }

}
