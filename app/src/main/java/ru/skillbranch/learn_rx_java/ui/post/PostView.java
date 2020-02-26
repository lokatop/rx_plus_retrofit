package ru.skillbranch.learn_rx_java.ui.post;

import androidx.annotation.NonNull;

import java.util.List;

import ru.skillbranch.learn_rx_java.common.BaseView;
import ru.skillbranch.learn_rx_java.model.Post;

public interface PostView extends BaseView {
    void showPosts(@NonNull List<Post> posts);
}
