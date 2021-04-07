package com.example.model;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bai4c7.R;

public class FiboAdapter extends ArrayAdapter<Fibonacci> {
    Activity context;
    int resource;
    public FiboAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = context.getLayoutInflater().inflate(resource,null);
        Button button = customView.findViewById(R.id.button);
        Fibonacci fibonacci = getItem(position);
        button.setText(fibonacci.getRandomNumber()+"");

        return customView;
    }
}
