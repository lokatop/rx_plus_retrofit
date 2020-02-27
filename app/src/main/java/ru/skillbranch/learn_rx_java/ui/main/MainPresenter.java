package ru.skillbranch.learn_rx_java.ui.main;

import java.util.ArrayList;
import java.util.Arrays;

import ru.skillbranch.learn_rx_java.common.BasePresenter;

public class MainPresenter extends BasePresenter {

    private MainView mView;
    private ArrayList<String> topics = new ArrayList<>(Arrays.asList("Posts","data","people","cats"));

    public MainPresenter(MainView view){
        mView = view;
    }

    public void getTopics(){
        if (topics!=null){mView.showTopics(topics);}
    }

    //Сделано в качестве примера, так как в будущем вряд ли будет использоваться именно такой подход
    public void openAnyFragment(String name){
        mView.openAnyFragment(name);
    }

    public void backPressed(){}

}
