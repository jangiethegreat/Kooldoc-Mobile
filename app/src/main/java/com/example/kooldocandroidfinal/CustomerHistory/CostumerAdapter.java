package com.example.kooldocandroidfinal.CustomerHistory;

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

public class CostumerAdapter extends ArrayAdapter<Costumer> {

    List<Costumer> arrayCostumerList;
    Context context;

    public CostumerAdapter(@NonNull Context context, List<Costumer> arrayCostumerList) {
        super(context, R.layout.customer_list_item,arrayCostumerList);
        this.context = context;
        this.arrayCostumerList = arrayCostumerList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_item,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvserviceType = view.findViewById(R.id.txt_serviceType);

        tvID.setText(arrayCostumerList.get(position).getId());
        tvserviceType.setText(arrayCostumerList.get(position).getServiceType());


        return view;
    }
}
