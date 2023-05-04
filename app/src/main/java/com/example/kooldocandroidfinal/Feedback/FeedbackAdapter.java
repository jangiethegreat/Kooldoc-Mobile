package com.example.kooldocandroidfinal.Feedback;

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

public class FeedbackAdapter  extends ArrayAdapter<Feedback> {

    List<Feedback> arrayFeedbackList;
    Context context;

    public FeedbackAdapter(@NonNull Context context, List<Feedback> arrayFeedbackList) {
        super(context, R.layout.feedback_list_item,arrayFeedbackList);
        this.context = context;
        this.arrayFeedbackList = arrayFeedbackList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_list_item,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvServicetype = view.findViewById(R.id.txt_feedbackName);

        tvID.setText(arrayFeedbackList.get(position).getId());
        tvServicetype.setText(arrayFeedbackList.get(position).getServiceType());


        return view;
    }



}
