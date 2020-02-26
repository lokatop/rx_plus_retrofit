package ru.skillbranch.learn_rx_java.ui.post;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import ru.skillbranch.learn_rx_java.common.SingleFragmentActivity;

public class PostActivity extends SingleFragmentActivity {


    @Override
    protected Fragment getFragment() {
        return PostFragment.newInstance();
    }
}
