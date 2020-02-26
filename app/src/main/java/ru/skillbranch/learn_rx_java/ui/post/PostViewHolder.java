package ru.skillbranch.learn_rx_java.ui.post;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.skillbranch.learn_rx_java.R;
import ru.skillbranch.learn_rx_java.model.Post;

public class PostViewHolder extends RecyclerView.ViewHolder {

    private TextView txt_title,txt_content,txt_author;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_author = (TextView)itemView.findViewById(R.id.txt_author);
        txt_content = (TextView)itemView.findViewById(R.id.txt_content);
        txt_title = (TextView)itemView.findViewById(R.id.txt_title);
    }

    public void bind(Post item, PostAdapter.OnItemClickListener onItemClickListener){
        txt_author.setText(String.valueOf(item.userId));
        txt_title.setText(String.valueOf(item.title));
        txt_content.setText(String.valueOf(item.body));

        if (onItemClickListener != null){
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(
                "Just any Toast"
            ));
        }
    }
}
