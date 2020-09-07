package com.example.realmsecure;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realmsecure.Models.Student;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class MyRVAdapter extends RealmRecyclerViewAdapter<Student, MyRVAdapter.MyViewHolder> {
    OrderedRealmCollection<Student> data;
    public MyRVAdapter(@Nullable List<Student> data) {
        super((OrderedRealmCollection<Student>) data, true);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Student tasks = getItem(position);
        holder.idTV.setText(String.valueOf(tasks.getStd_id()));
        holder.tasknameTV.setText(tasks.getStd_name());
        holder.ageTV.setText(String.valueOf(tasks.getStd_age()));
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView idTV, tasknameTV, ageTV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idTV = itemView.findViewById(R.id.idTV);
            tasknameTV = itemView.findViewById(R.id.tasknameTV);
            ageTV = itemView.findViewById(R.id.ageTV);
        }
    }

}
