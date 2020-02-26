package ru.skillbranch.learn_rx_java.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.skillbranch.learn_rx_java.R;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private final List<String> mTopics = new ArrayList<>();
    private final OnItemClickListener mOnItemClickListener;

    public MainAdapter(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.main_rv,parent,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        String topic = mTopics.get(position);
        holder.bind(topic,mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }

    public interface OnItemClickListener {
        void onItemClick(String toast);
    }

    public void addData(List<String> data, boolean isRefreshed) {
        if (isRefreshed) {
            mTopics.clear();
        }

        mTopics.addAll(data);
        notifyDataSetChanged();
    }
}
