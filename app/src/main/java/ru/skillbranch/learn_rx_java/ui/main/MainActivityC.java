package ru.skillbranch.learn_rx_java.ui.main;

import androidx.fragment.app.Fragment;

import ru.skillbranch.learn_rx_java.common.SingleFragmentActivity;

public class MainActivityC extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return MainFragment.newInstance();
    }
}
