package com.ycy.storehouse.base;

public interface Presenter<V> {
    void attachView(V mvpView);
    void detachView();
}
