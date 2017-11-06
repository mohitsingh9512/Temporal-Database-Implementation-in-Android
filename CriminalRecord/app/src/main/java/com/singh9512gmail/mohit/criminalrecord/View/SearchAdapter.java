package com.singh9512gmail.mohit.criminalrecord.View;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.singh9512gmail.mohit.criminalrecord.Model.Criminal;
import com.singh9512gmail.mohit.criminalrecord.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusharaggarwal on 01/11/17.
 */

public class SearchAdapter extends ArrayAdapter {

    private List<Criminal> list;
    Context context;
    public SearchAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        list = new ArrayList<>();
        this.context = context;
    }

    public int getCount() {
        return list.size();
    }

    @Override
    public void clear() {
        super.clear();
        list.clear();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row ;
        row = convertView;

        final Holder holder ;

        if(convertView == null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.model_search,parent,false);
            holder = new Holder();
            holder.name = (TextView) row.findViewById(R.id.name_tv);
            holder.address = (TextView) row.findViewById(R.id.address_tv);
            holder.valid_from = (TextView) row.findViewById(R.id.valid_from_tv);
            holder.valid_to = (TextView) row.findViewById(R.id.valid_to_tv);
            row.setTag(holder);
        }else {
            holder = (Holder) row.getTag();
        }

        holder.name.setText(list.get(position).get_name());
        holder.address.setText(list.get(position).get_address());
        holder.valid_from.setText(list.get(position).get_valid_from());
        holder.valid_to.setText(list.get(position).get_valid_to());

        return row;
    }

    public void add(Criminal object){
        super.add(object);
        list.add(object);
    }

    private static class Holder{
        TextView name;
        TextView address;
        TextView valid_from;
        TextView valid_to;
    }
}
