package ru.skillbranch.learn_rx_java.ui.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.skillbranch.learn_rx_java.R;
import ru.skillbranch.learn_rx_java.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private final List<Post> mPost = new ArrayList<>();
    private final OnItemClickListener mOnItemClickListener;

    public PostAdapter(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.post_layout,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = mPost.get(position);
        holder.bind(post, mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }


    public void addData(List<Post> data, boolean isRefreshed) {
        if (isRefreshed) {
            mPost.clear();
        }

        mPost.addAll(data);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(String toast);
    }
}
