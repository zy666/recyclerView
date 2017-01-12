package com.danny.playerdemo;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by danny on 2016/11/7.
 */

public interface IView {
    Activity getActivity();

    /**初始化空间，子类不能重写onCreate
     * @param saveInstanceState
     */
    void onCreateActivity(Bundle saveInstanceState);

    /**
     * 视图可见;子类不能重写onResume
     */
    void resume();
}
