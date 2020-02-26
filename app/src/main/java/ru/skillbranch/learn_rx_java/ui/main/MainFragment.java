package ru.skillbranch.learn_rx_java.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.skillbranch.learn_rx_java.R;
import ru.skillbranch.learn_rx_java.common.PresenterFragment;
import ru.skillbranch.learn_rx_java.common.RefreshOwner;
import ru.skillbranch.learn_rx_java.common.Refreshable;

public class MainFragment extends PresenterFragment<MainPresenter>
        implements Refreshable,MainView,MainAdapter.OnItemClickListener {

    private RefreshOwner mRefreshOwner;
    private View mErrorView;
    private MainPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;

    public static MainFragment newInstance(){return new MainFragment();}

    //Вызывается, когда фрагмент связывается с активностью.
    // С этого момента мы можем получить ссылку на активность через метод getActivity()
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof RefreshOwner){
            mRefreshOwner = ((RefreshOwner)context);
        }
    }

    //Вызывается для создания компонентов внутри фрагмента(тут только создается layout)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_mainc,container,false);
    }

    //Тут можно привязывать логику к view(Это гарантирует что вьюха создана, и никакие null pointer'ы падать не будут.)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler_main);
        mErrorView = view.findViewById(R.id.errorView);
    }

    //Вызывается, когда отработает метод активности onCreate(),
    // а значит фрагмент может обратиться к компонентам активности
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null){
            getActivity().setTitle("Main");
        }
        mPresenter = new MainPresenter(this);
        mMainAdapter = new MainAdapter(this);
        mRecyclerView.setLayoutManager( new GridLayoutManager(getActivity(),2));
        mRecyclerView.setAdapter(mMainAdapter);

        onRefreshData();
    }

    @Override
    public void showRefresh() {
        mRefreshOwner.setRefreshState(true);
    }

    @Override
    public void hideRefresh() {
        mRefreshOwner.setRefreshState(false);
    }

    @Override
    public void showError() {
        mErrorView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    protected MainPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onRefreshData() {
        mPresenter.getTopics();
    }

    @Override
    public void showTopics(@NonNull List<String> topics) {
        mErrorView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mMainAdapter.addData(topics, true);
    }

    //Вызывается, когда фрагмент отвязывается от активности
    @Override
    public void onDetach() {
        mRefreshOwner = null;
        super.onDetach();
    }

    @Override
    public void onItemClick(String toast) {
        Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
    }
}
