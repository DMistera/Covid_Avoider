package com.example.covidavoider;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidavoider.models.User;
import com.example.covidavoider.models.UserHistoryEntry;
import com.example.covidavoider.singletons.DatabaseService;
import com.example.covidavoider.singletons.HistoryService;
import com.example.covidavoider.singletons.OnLocationChangeCallback;
import com.example.covidavoider.singletons.UserService;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    List<UserHistoryEntry> entries;

    public HistoryAdapter() {
        entries = HistoryService.getInstance().getHistory();
        HistoryService.getInstance().onLocationChange(new OnLocationChangeCallback() {
            @Override
            public void onLocationChange() {
                entries = HistoryService.getInstance().getHistory();
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);
        return new HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.textView.setText(entries.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public HistoryViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }
}
