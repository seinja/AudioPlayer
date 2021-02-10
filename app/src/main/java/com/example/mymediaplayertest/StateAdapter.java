package com.example.mymediaplayertest;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Audio> music;

    StateAdapter(Context context, List<Audio> music){
        this.music = music;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateAdapter.ViewHolder holder, int position) {
        Audio audio = music.get(position);
        holder.nameView.setText(audio.getTitle());
        holder.capitalView.setText(audio.getData());
    }

    @Override
    public int getItemCount() {
        return music.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, capitalView;
        ViewHolder(View view){
            super(view);
            nameView = (TextView) view.findViewById(R.id.name);
            capitalView = (TextView) view.findViewById(R.id.capital);
        }
    }
}
