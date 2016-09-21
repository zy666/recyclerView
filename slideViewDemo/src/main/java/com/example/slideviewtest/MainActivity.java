package com.example.slideviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.slideviewtest.SlideViewAdapter.OnRemoveListener;
/**
 * @author blank
 * @time 下午4:16:46
 * @version V1.0
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SlideViewAdapter adapter = new SlideViewAdapter(this);
		ListSlideView list = (ListSlideView) findViewById(R.id.silideList);
		list.setAdapter(adapter);
		adapter.setRemoveListener(new OnRemoveListener() {

			@Override
			public void onRemoveItem(int position) {
				// TODO 删除按钮的回调，注意也可以放在adapter里面处理，具体根据自己项目来
				Toast.makeText(MainActivity.this, "点击了" + position + "项的删除",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
