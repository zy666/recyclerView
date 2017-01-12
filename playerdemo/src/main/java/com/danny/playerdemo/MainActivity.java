package com.danny.playerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;

public class MainActivity extends BaseActivity {


    @Bind(R.id.main_fragment)
    FrameLayout mainFragment;
    @Bind(R.id.bottom_img_shadow)
    ImageView bottomImgShadow;
    @Bind(R.id.bottom_img_image)
    ImageView bottomImgImage;
    @Bind(R.id.bottom_tv_title)
    TextView bottomTvTitle;
    @Bind(R.id.bottom_tv_artist)
    TextView bottomTvArtist;
    @Bind(R.id.bottom_btn_next)
    Button bottomBtnNext;
    @Bind(R.id.bottom_btn_play)
    Button bottomBtnPlay;
    @Bind(R.id.bottom_btn_previous)
    Button bottomBtnPrevious;
    @Bind(R.id.main_layout_bottombar)
    RelativeLayout mainLayoutBottombar;

    @Override
    public void onCreateActivity(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickView(View view) {
        int id = view.getId();
        if (id == R.id.bottom_btn_previous) {
            Toast.makeText(this, "上一首", Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.bottom_btn_play) {
            Toast.makeText(this, "播放", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.bottom_btn_next) {

            Toast.makeText(this, "下一首", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public Activity getActivity() {
        return null;
    }

    @Override
    public void resume() {

    }
}
