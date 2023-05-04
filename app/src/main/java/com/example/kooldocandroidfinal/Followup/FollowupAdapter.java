package com.example.kooldocandroidfinal.Followup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kooldocandroidfinal.R;

import java.util.List;

public class FollowupAdapter extends ArrayAdapter<Followup> {

    List<Followup> arrayFollowupList;
    Context context;

    public FollowupAdapter(@NonNull Context context, List<Followup> arrayFollowupList) {
        super(context, R.layout.customer_list_item,arrayFollowupList);
        this.context = context;
        this.arrayFollowupList = arrayFollowupList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_item,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvserviceType = view.findViewById(R.id.txt_serviceType);

        tvID.setText(arrayFollowupList.get(position).getId());
        tvserviceType.setText(arrayFollowupList.get(position).getCustomerLastname());


        return view;
    }



}
