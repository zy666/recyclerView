package com.danny.playerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

/**
 * Created by danny on 2016/11/7.
 */

public abstract class BaseActivity extends AppCompatActivity implements IView{
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        onCreateActivity(savedInstanceState);
    }

    public <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }
}
