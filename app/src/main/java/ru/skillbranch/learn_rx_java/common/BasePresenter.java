package ru.skillbranch.learn_rx_java.common;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter {

    protected final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void disposeAll() {
        mCompositeDisposable.dispose();
    }
}
