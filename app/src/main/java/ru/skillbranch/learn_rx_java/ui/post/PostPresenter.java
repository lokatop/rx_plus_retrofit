package ru.skillbranch.learn_rx_java.ui.post;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.skillbranch.learn_rx_java.common.BasePresenter;
import ru.skillbranch.learn_rx_java.retrofit.ApiPost;
import ru.skillbranch.learn_rx_java.utils.PostApiService;

class PostPresenter extends BasePresenter {
    private PostView mView;

    public PostPresenter(PostView view){
        mView = view;
    }

    public void getPosts(){
        mCompositeDisposable.add(PostApiService.getApiService().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showRefresh())
                .doFinally(mView::hideRefresh)
                .subscribe(
                        response -> mView.showPosts(response),
                        throwable -> mView.showError()
                )

        );
    }
}
