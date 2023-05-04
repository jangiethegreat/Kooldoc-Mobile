package com.example.kooldocandroidfinal.CanceledServices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kooldocandroidfinal.EmployeeHistory.Service;
import com.example.kooldocandroidfinal.R;

import java.util.List;

public class CancelledAdapter extends ArrayAdapter<Cancelled> {

    List<Cancelled> arrayCancelledList;
    Context context;

    public CancelledAdapter(@NonNull Context context, List<Cancelled> arrayCancelledList) {
        super(context, R.layout.service_list_item,arrayCancelledList);
        this.context = context;
        this.arrayCancelledList = arrayCancelledList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_list_item,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvserviceType = view.findViewById(R.id.txt_customerFull);

        tvID.setText(arrayCancelledList.get(position).getId());
        tvserviceType.setText(arrayCancelledList.get(position).getServiceType());


        return view;
    }

}
