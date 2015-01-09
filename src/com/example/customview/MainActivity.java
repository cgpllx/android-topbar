package com.example.customview;

import com.example.customview.TopBar.TopBarListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TopBar mTopBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTopBar = (TopBar) findViewById(R.id.topbar);
		mTopBar.setTopBarListener(new TopBarListener() {
			
			@Override
			public void setRightButtonListener() {
				Toast.makeText(MainActivity.this, "submit", Toast.LENGTH_LONG).show();
			}
			
			@Override
			public void setLeftButtonListener() {
				Toast.makeText(MainActivity.this, "back", Toast.LENGTH_LONG).show();
			}
		});
//		mTopBar.setLeftButtonVisible(false);
	}

}
