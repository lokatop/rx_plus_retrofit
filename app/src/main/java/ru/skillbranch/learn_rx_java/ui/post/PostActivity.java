package ru.skillbranch.learn_rx_java.ui.post;

import androidx.fragment.app.Fragment;

import ru.skillbranch.learn_rx_java.common.SingleFragmentActivity;

public class PostActivity extends SingleFragmentActivity {


    public static final String USERNAME_KEY = "USERNAME_KEY";

    @Override
    protected Fragment getFragment() {
        if (getIntent() != null){
            return PostFragment.newInstance(getIntent().getBundleExtra(USERNAME_KEY));
        }
        throw new IllegalStateException("getIntent cannot be null");
    }
}
