package ru.skillbranch.learn_rx_java.ui.post;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.skillbranch.learn_rx_java.R;
import ru.skillbranch.learn_rx_java.common.PresenterFragment;
import ru.skillbranch.learn_rx_java.common.RefreshOwner;
import ru.skillbranch.learn_rx_java.common.Refreshable;
import ru.skillbranch.learn_rx_java.model.Post;

public class PostFragment extends PresenterFragment<PostPresenter>
        implements Refreshable, PostView, PostAdapter.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private RefreshOwner mRefreshOwner;
    private View mErrorView;
    private PostAdapter mPostAdapter;
    private PostPresenter mPresenter;

    public static PostFragment newInstance(Bundle args){
        PostFragment fragment = new PostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String POST_KEY = "POST_KEY";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof RefreshOwner){
            mRefreshOwner = ((RefreshOwner)context);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_posts,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler);
        mErrorView = view.findViewById(R.id.errorView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null){
            getActivity().setTitle("Posts");
        }

        mPresenter = new PostPresenter(this);
        mPostAdapter = new PostAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mPostAdapter);

        onRefreshData();
    }

    @Override
    protected PostPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onRefreshData() {
        mPresenter.getPosts();
    }

    @Override
    public void showPosts(@NonNull List<Post> posts) {
        mErrorView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mPostAdapter.addData(posts, true);
    }

    @Override
    public void onDetach() {
        mRefreshOwner = null;
        super.onDetach();
    }

    @Override
    public void onItemClick(String toast) {
        Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
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
}
