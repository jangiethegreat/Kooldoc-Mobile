package com.example.kooldocandroidfinal.EmployeeHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kooldocandroidfinal.CustomerHistory.Costumer;
import com.example.kooldocandroidfinal.R;

import java.util.List;

public class ServiceAdapter extends ArrayAdapter<Service> {


    List<Service> arrayServiceList;
    Context context;

    public ServiceAdapter(@NonNull Context context, List<Service> arrayServiceList) {
        super(context, R.layout.service_list_item,arrayServiceList);
        this.context = context;
        this.arrayServiceList = arrayServiceList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_list_item,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvserviceType = view.findViewById(R.id.txt_customerFull);

        tvID.setText(arrayServiceList.get(position).getId());
        tvserviceType.setText(arrayServiceList.get(position).getCustomerFullname());


        return view;
    }


}
