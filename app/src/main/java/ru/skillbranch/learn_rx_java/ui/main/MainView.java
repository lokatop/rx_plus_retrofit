package ru.skillbranch.learn_rx_java.ui.main;

import androidx.annotation.NonNull;

import java.util.List;

import ru.skillbranch.learn_rx_java.common.BaseView;

public interface MainView extends BaseView {
    void showTopics(@NonNull List<String> topics);
    void openAnyFragment(@NonNull String name);
}
