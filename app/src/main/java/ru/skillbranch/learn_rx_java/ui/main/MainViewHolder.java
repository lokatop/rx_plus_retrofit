package ru.skillbranch.learn_rx_java.ui.main;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.skillbranch.learn_rx_java.R;

public class MainViewHolder extends RecyclerView.ViewHolder {

    private TextView txt_title_main;

    public MainViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_title_main = (TextView)itemView.findViewById(R.id.txt_title_main);
    }

    public void bind(String topic, MainAdapter.OnItemClickListener onItemClickListener){
        txt_title_main.setText(topic);

        if (onItemClickListener != null){
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(
                    topic
            ));
        }
    }
}
