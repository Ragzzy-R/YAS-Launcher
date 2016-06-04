package com.ragzzyr.yaslauncher;

import com.jakkarrl.jakkarrllauncher.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.pull_out_to_left, R.anim.hold);
		setContentView(R.layout.activity_home_screen);
		View decorView = getWindow().getDecorView();
		// Hide both the navigation bar and the status bar.
		// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
		// a general rule, you should design your app to hide the status bar whenever you
		// hide the navigation bar.
		int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
		              | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
		decorView.setSystemUiVisibility(uiOptions);
		
	}
	public void launchAppDrawer(View view) {
		Intent intent = new Intent(this, AppDrawer.class);
		startActivity(intent);
		
	}
	@Override
	public void onBackPressed() {
		//do nothing
	}
	
}
